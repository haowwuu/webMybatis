/*
 * File Name: StatisticServlet.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-17

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-17
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.servlet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cetiti.dsp.service.AuditTrailService;
import com.cetiti.dsp.service.impl.AuditTrailServiceImpl;
import com.cetiti.dsp.util.ApiResult;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-17
 * @see       
 * @since     DSPlite V0.2.0
 */

public class StatisticServlet extends DispatcherServlet{
	
	private static final long serialVersionUID = 1L;
	AuditTrailService atService = new AuditTrailServiceImpl();
	
	class StatParam{
		
		private Date begin;
		private Date end;
		private int topN;
		private String param;

		public StatParam(String sbegin, String send, String n, String param) {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			if(null!=send){
				try {
					this.end = df.parse(send);
				} catch (ParseException e) { }
			}
			if(null==this.end){
				this.end = new Date();
			}
			if(null!=sbegin){
				try {
					this.begin = df.parse(sbegin);
				} catch (ParseException e) { }	
			}
			if(null==this.begin){
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.end);
				cal.add(Calendar.HOUR, -1);
				this.begin = cal.getTime();
			}
			
			if(null!=n){
				try {
					this.topN = Integer.parseInt(n);
				} catch (NumberFormatException e) {	}
			}
			
			this.param = param;
		}

		public Date getBegin() {
			return begin;
		}
		
		public Date getEnd() {
			return end;
		}
		
		public int getTopN() {
			return topN;
		}
		
		public String getParam() {
			return param;
		}
		
	}
	
	public void statisticByApi(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sbegin = request.getParameter("begin");
		String send = request.getParameter("end");
		String n = request.getParameter("n");
		String api = request.getParameter("api");
		
		StatParam sp = new StatParam(sbegin, send, n, api);
		
		Map<String, List<Object[]>> data = atService.statisticByApi
				(sp.getBegin(), sp.getEnd(), sp.getParam(), sp.getTopN());
		
		ApiResult.writeToResponse(response, data);
	}
	
	public void statisticByKey(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sbegin = request.getParameter("begin");
		String send = request.getParameter("end");
		String n = request.getParameter("n");
		String key = request.getParameter("key");
		
		StatParam sp = new StatParam(sbegin, send, n, key);
		
		Map<String, List<Object[]>> data = atService.statisticByKey
				(sp.getBegin(), sp.getEnd(), sp.getParam(), sp.getTopN());
		
		ApiResult.writeToResponse(response, data);
	}
	
}
