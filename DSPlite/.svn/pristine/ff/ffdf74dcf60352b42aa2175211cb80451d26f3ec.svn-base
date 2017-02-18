/*
 * File Name: AppServiceImpl.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-31

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-31
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service.impl;

import java.util.List;

import com.cetiti.dsp.dao.AppMapper;
import com.cetiti.dsp.dao.QuantityMapper;
import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.APPinfo;
import com.cetiti.dsp.service.AppService;
import com.cetiti.dsp.util.Configration;
import com.cetiti.dsp.util.EncryptUtil;
import com.cetiti.dsp.util.SequenceGenerator;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-31
 * @see       
 * @since     DSPlite V0.2.0
 */

public class AppServiceImpl implements AppService{

	AppMapper appMapper = DataSourceFactory.getMapper(AppMapper.class);
	QuantityMapper quantityMapper = DataSourceFactory.getMapper(QuantityMapper.class);
	
	@Override
	public List<APPinfo> queryAll() {
		return appMapper.selectList();
	}

	@Override
	public void add(APPinfo app) {
		if(null==app||null==app.getAppName()){
			return;
		}
		String appName = app.getAppName();
		if(appMapper.countAppName(appName)>0){
			return;
		}
		if(null!=app.getAppAddress()){
			String url = formatUrl(app.getAppAddress());
			app.setAppAddress(url);
		}
		app.setAppKey(createKey(appName));
		app.setAppSecret(createSecret(appName));
		app.setAppId(SequenceGenerator.next());
		appMapper.insert(app);
		
	}

	@Override
	public void update(APPinfo app) {
		if(null==app||null==app.getAppName()){
			return;
		}
		String appName = app.getAppName();
		APPinfo info = appMapper.selectByAppName(appName);
		if(null!=info&&!info.getAppId().equals(app.getAppId())){
			return;
		}
		String url = app.getAppAddress();
		if(null!=url&&url.length()>0){
			url = formatUrl(url);
			app.setAppAddress(url);
		}
		appMapper.update(app);
	}

	@Override
	public void delete(String appid) {
		if(null==appid){
			return;
		}
		APPinfo app = appMapper.selectById(appid);
		appMapper.delete(appid);
		if(null!=app){
			quantityMapper.deleteByAppKey(app.getAppKey());
		}
	}
	
	@Override
	public boolean appCheck(String name, String password, String random){
		if(null==name){
			return false;
		}
		if(Configration.INSTANCE.adminLogin(name, password, random)){
			return true;
		}
		if(null!=appMapper.selectByAppName(name)||null!=appMapper.select(name)){
			return true;
		}
		return false;
	}
	
	public static String createKey(String url){
		return EncryptUtil.md5("appkey"+System.currentTimeMillis()+url+System.nanoTime());
	}
	
	public static String createSecret(String url){
		return EncryptUtil.md5("appSecret"+System.currentTimeMillis()+url+System.nanoTime());
	}

	public static String formatUrl(String url) {
		int pidx = url.indexOf("://");
		if (pidx < 0) {
			url = "http://" + url;
			pidx = url.indexOf("://");
		}

		int slash1 = url.indexOf('/', pidx + 3);
		if (slash1 < 0) {
			url += '/';
			slash1 = url.indexOf('/', pidx + 3);
		}
		int len = url.length();
		if (slash1 + 1 < len) {
			int slash2 = url.indexOf('/', slash1 + 1);
			if (slash2 < 0) {
				url += '/';
			} else {
				url = url.substring(0, slash2 + 1);
			}
		}

		return url;
	}

}
