/*
 * File Name: AuditTrailServiceImpl.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-25

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-25
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetiti.dsp.dao.AppMapper;
import com.cetiti.dsp.dao.AuditTrailMapper;
import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.APPinfo;
import com.cetiti.dsp.model.AuditTrail;
import com.cetiti.dsp.model.KeyValue;
import com.cetiti.dsp.service.AuditTrailService;
import com.cetiti.dsp.util.SequenceGenerator;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-25
 * @see       
 * @since     DSPlite V0.2.0
 */

public class AuditTrailServiceImpl implements AuditTrailService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AuditTrailServiceImpl.class);
	private final static int Q_SIZE = 1024;
	private static Queue<AuditTrail> logQ;
	private static ScheduledExecutorService ses;
	private AuditTrailMapper atMapper = DataSourceFactory.getMapper(AuditTrailMapper.class);
	private AppMapper appMapper = DataSourceFactory.getMapper(AppMapper.class);
	
	public AuditTrailServiceImpl() {
		if(null==ses){
			logQ = new LinkedBlockingQueue<AuditTrail>(Q_SIZE);
			ses = Executors.newSingleThreadScheduledExecutor();
			ses.scheduleAtFixedRate(logTask, 30, 30, TimeUnit.SECONDS);
		}
	}
	
	private Runnable logTask = new Runnable() {
		@Override
		public void run() {
			AuditTrail auditTrail = null;
			try {
				while (logQ.size() > 0) {
					auditTrail = logQ.poll();
					auditTrail.setAuditId(SequenceGenerator.next());
					atMapper.insert(auditTrail);
				}
			} catch (Exception e) {
				LOGGER.error("error log:[{}], message:[{}],[{}]",
						auditTrail.toString(), e.getMessage(), e.getCause());
			}
		}
	};
	
	public final static void stopLogService() {
		if(null!=ses){
			ses.shutdown();
			ses = null;
		}
	}
	
	@Override
	public int trail(AuditTrail auditTrail) {
		if(null==auditTrail||null==auditTrail.getApiMethod()){
			return 0;
		}
		
		return logQ.offer(auditTrail)?1:0;
	}
	
	static enum Type{
		API,
		KEY
	}
	
	@Override
	public Map<String, List<Object[]>> statisticByApi(Date begin, Date end, String api, int topN){
		if(null==begin){
			return null;
		}
		if(null==end){
			end = new Date();
		}
		if(begin.after(end)){
			return null;
		}
		Map<String, List<Object[]>> retn = new HashMap<String, List<Object[]>>();
		if(null==api||api.trim().length()<1){
			int n = topN>0? topN:10;
			List<KeyValue<String, Integer>> apiCount = atMapper.topNMethods(begin, end, n);
			for(KeyValue<String, Integer> kv:apiCount){
				retn.put(kv.getKey(), statistic(begin, end, kv.getKey(), Type.API));
			}
		}else{
			retn.put(api, statistic(begin, end, api, Type.API));
		}
		return retn;
	}
	
	@Override
	public Map<String, List<Object[]>> statisticByKey(Date begin, Date end, String key, int topN){
		if(null==begin){
			return null;
		}
		if(null==end){
			end = new Date();
		}
		if(begin.after(end)){
			return null;
		}
		Map<String, List<Object[]>> retn = new HashMap<String, List<Object[]>>();
		if(null==key||key.trim().length()<1){
			int n = topN>0? topN:10;
			List<KeyValue<String, Integer>> apiCount = atMapper.topNKeys(begin, end, n);
			for(KeyValue<String, Integer> kv:apiCount){
				String label = kv.getKey();
				if(!AuditTrail.DEFAULT_NULL_KEY.equals(label)){
					APPinfo app = appMapper.select(label);
					if(null!=app&&null!=app.getAppName()){
						label = app.getAppName();
					}
				}
				retn.put(label, statistic(begin, end, kv.getKey(), Type.KEY));
			}
		}else{
			APPinfo app = appMapper.select(key);
			if(null!=app){
				retn.put(app.getAppName(), statistic(begin, end, key, Type.KEY));
			}
			
		}
		return retn;
	}
	
	private List<Object[]> statistic(Date begin, Date end, String value, Type type){
		List<Object[]> retn = new ArrayList<Object[]>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(end);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		while(cal.getTimeInMillis()>begin.getTime()){
			retn.addAll(statisticByHour(cal.getTime(), end, value, type));
			end = cal.getTime();
			cal.add(Calendar.HOUR, -1);
		}
		retn.addAll(statisticByHour(begin, end, value, type));
		
		return retn;
	}
	
	private List<Object[]> statisticByHour(Date begin, Date end, String value, Type type){
		List<Object[]> retn = new ArrayList<Object[]>();
		List<AuditTrail> atlist = new ArrayList<AuditTrail>();
		if(type==Type.API){
			atlist = atMapper.selectListByTimeAndMethod(begin, end, value);
		}else if(type==Type.KEY){
			atlist = atMapper.selectListByTimeAndKey(begin, end, value);
		}
		if(null==atlist||atlist.size()<1){
			return retn;
		}
		int[] a = new int[60];
		Long start = begin.getTime();
		for(AuditTrail at:atlist){
			int i = (int)(at.getAuditDate().getTime()-start)/60000;
			a[i]++;
		}
		for(int i=0; i<a.length; i++){
			if(a[i]>0){
				Object[] r = new Object[2];
				r[0] = start+i*60000;
				r[1] = a[i];
				retn.add(r);
			}
		}
		return retn;
	}
	
	@Override
	public void cleanOld(){
		String the500000th = atMapper.selectTheNthID(500000);
		if(null!=the500000th){
			atMapper.delete(the500000th);
		}
	}
	
}
