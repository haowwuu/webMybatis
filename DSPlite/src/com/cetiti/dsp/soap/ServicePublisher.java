/*
 * File Name: ServicePublisher.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-9

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-9
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.soap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetiti.dsp.soap.compiler.JavaStringCompiler;
import com.cetiti.dsp.model.APIinfo;
import com.cetiti.dsp.util.IpPortUtil;
import com.sun.xml.internal.ws.transport.http.HttpAdapter;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-9
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ServicePublisher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServicePublisher.class);
	
	private static Set<Endpoint> endpointSet = new HashSet<Endpoint>();
	
	public static void publish(Collection<APIinfo> apiInfos){
		stop();
		JavaStringCompiler compiler = new JavaStringCompiler();
		Map<String, String> source = CodeGenerator.generateSource(apiInfos);
		try {	
			for(Entry<String, String> entry:source.entrySet()){
				Map<String, byte[]> results = compiler.compile(
						CodeGenerator.getSimpleClassName(entry.getKey())+".java", entry.getValue());
				//System.out.println(entry.getKey());
				Class<?> clazz = compiler.loadClass(entry.getKey(), results);
				Object obj = clazz.newInstance();
				if(LOGGER.isDebugEnabled()){
					HttpAdapter.dump=true;  //print soap xml
				}
				endpointSet.add(Endpoint.publish(IpPortUtil.createSoapServer()+CodeGenerator.getPackage(
						entry.getKey())+"/"+CodeGenerator.getSimpleClassName(entry.getKey()), obj));
			}	
		} catch (Exception e) {
			LOGGER.error("publish soap webservice exception! "+e.getMessage());
		}
	}
	
	public static void stop(){
		for(Endpoint endpoint: endpointSet){
			endpoint.stop();
		}
		endpointSet.clear();
	}
	
}
