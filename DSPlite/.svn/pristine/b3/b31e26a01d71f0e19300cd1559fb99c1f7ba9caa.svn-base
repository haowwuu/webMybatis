/*
 * File Name: InstructionExecutor.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-19

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-19
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import com.cetiti.dsp.dao.ResourceMapper;
import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.APIinfo;
import com.cetiti.dsp.soap.ServicePublisher;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-19
 * @see       
 * @since     DSPlite V0.2.0
 */

public class InstructionExecutor {
	
	private static ResourceMapper mapper = DataSourceFactory.getMapper(ResourceMapper.class);
	
	public static void clearRepos(String[] args){
		if(null==args){
			return;
		}
		boolean change = false;
		for(String key:args){
			if("all".equalsIgnoreCase(key)){
				SqlSessionFactoryRepos.clear();
				change = true;
				mapper.deleteAll();
				break;
			}
			if(null!=SqlSessionFactoryRepos.remove(key)){
				change=true;
			}
		}
		if(change){
			ServicePublisher.publish(SqlSessionFactoryRepos.getApiInfos());
		}
	}
	
	public static void deleteService(String[] args){
		if(null==args){
			return;
		}
		int len = args.length;
		boolean change = false;
		if(len==1){
			APIinfo info = SqlSessionFactoryRepos.removeServcice(args[0], null);
			if(null!=info){
				change=true;
				mapper.delete(info.getMetadataFile());
			}
		}else if(len==2){
			if(null!=SqlSessionFactoryRepos.removeServcice(args[0], args[1])){
				change=true;
			}
		}
		if(change){
			ServicePublisher.publish(SqlSessionFactoryRepos.getApiInfos());
		}
	}
	
	public static String createDatabase(String[] args){
		if(null==args||args.length<1){
			return null;
		}
		if("sure".equalsIgnoreCase(args[0])){
			DataSourceFactory.createSchemaAndLoadData();
			return "database initiated";
		}
		return null;
	}
}
