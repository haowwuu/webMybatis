/*
 * File Name: MapperConfigReader.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-28

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-28
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;

import com.cetiti.dsp.soap.ServicePublisher;
import com.cetiti.dsp.builder.SqlSessionFactoryBuilder;
import com.cetiti.dsp.service.ResourcePathService;
import com.cetiti.dsp.service.impl.ResourcePathServiceImpl;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class MapperConfigReader {
 
	public static void InitConn(){
		ResourcePathService rpService = new ResourcePathServiceImpl();
		for(String resource:rpService.load()){
			if(null==resource||resource.indexOf("#")==0||resource.indexOf(".xml")<0){
				continue;
			}
			try {
				Reader configReader = Resources.getUrlAsReader(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configReader);
				SqlSessionFactoryRepos.addSqlSessionFactory(sqlSessionFactory, resource);
				configReader.close();
			} catch (Exception e) {
				System.err.println(MapperConfigReader.class.getCanonicalName()+
						"-InitConn(): load resource:"+resource +e.getMessage());
			}
		}
		// publish soap ws
		ServicePublisher.publish(SqlSessionFactoryRepos.getApiInfos());
	}
}
