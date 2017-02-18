/*
 * File Name: ApiTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-27

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-27
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.api;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-27
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ApiTest {

	/**
	 * 〈一句话功能简述〉
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	public boolean isSuccessResponse(String response){
		return response.indexOf("\"resultcode\":\"0\"")>=0;
	}
	
	public void req(String url, Map<String, String> parm, int second) throws Exception{
		int success = 0, fail=0;
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis()-startTime<second*1000){
			String retn = HttpUtil.doGet(url, parm);
			if(isSuccessResponse(retn)){
				success+=1;
			}else{
				fail+=1;
			}
		}
		
		System.out.printf("requese %s in %ds success %d fail %d", url, second, success, fail);
	}
	
	@Test
	public void testSelectLevels() throws Exception{
		req("http://localhost:8080/DSPlite/api/com.cetiti.dsp.Mapper/selectLevels", null, 5);
	}
	
	@Test
	public void testselectLevelsWithParams() throws Exception{
		int success = 0, fail=0, second=100;
		String url = "http://localhost:8080/DSPlite/api/com.cetiti.dsp.Mapper/selectLevelsWithParams";
		long startTime = System.currentTimeMillis();
		Map<String, String> param = new HashMap<String, String>();
		while(System.currentTimeMillis()-startTime<second*1000){
			param.clear();
			int id = (success+fail)%9;
			param.put("levelid", String.valueOf(id));
			param.put("levelname", String.valueOf(System.currentTimeMillis()));
			String retn = HttpUtil.doGet(url, param);
			if(isSuccessResponse(retn)){
				success+=1;
			}else{
				fail+=1;
			}
		}
		
		System.out.printf("requese %s in %ds success %d fail %d", url, second, success, fail);
		//req("http://localhost:8080/DSPlite/api/com.cetiti.dsp.Mapper/selectLevelsWithParams?levelid=4&levelname=name", null, 5);
	}

}
