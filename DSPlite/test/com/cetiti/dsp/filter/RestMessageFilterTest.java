/*
 * File Name: RestMessageFilterTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-28

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-28
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.filter;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.util.EncryptUtil;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class RestMessageFilterTest {

	static String appKey = "fa62f05d3e6b7c33ee794740737d17b4";
	static String appSecret = "1d9f9f5cee0df83e1999ea2df11c42b4";
	
	private String baseUrl = "http://10.0.30.52:8889/dsplite/api/";
	
	/*static String appKey = "09814c14ff881cec1ae0a89d09eaf85a";
	static String appSecret = "459fb3b22f8a11d96a5188ca797723a2";
		
	private String baseUrl = "http://10.0.10.78:8180/dsplite/api/";*/
	
	static Map<String, String> setSignature(Map<String, String> map){
		if (null == map) {
			map = new HashMap<String, String>();
		}
		map.put("appKey", appKey);
		map.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		Map<String, String> sortedParams = new TreeMap<String, String>(map);
		Set<Entry<String, String>> entrys = sortedParams.entrySet();

		StringBuilder sb = new StringBuilder();

		sb.append(appSecret);
		for (Entry<String, String> param : entrys) {
			if (param.getValue() != null) {
				sb.append(param.getKey()).append(param.getValue().trim());
			}
		}
		sb.append(appSecret);
		String sign = EncryptUtil.md5(sb.toString());
		//System.out.println("clientSign:" + sign + "  sb:" + sb.toString());
		map.put("sign", sign);

		return map;
	}
	
	static boolean isSuccess(String result){
		if(null==result){
			return false;
		}
		
		return result.indexOf("\"resultcode\":\"0\"")>=0;
	}
	
	@Ignore
	public void testSelectStrategys() throws Exception {
		String url = baseUrl + "com.cetiti.dsp.Mapper/selectStrategys";
		String retn = HttpUtil.doPost(url, setSignature(null));
		
		assertTrue(isSuccess(retn));
	}
	
	@Ignore
	public void testSelectLevelsWithParams() throws Exception{
		String url = baseUrl + "com.cetiti.dsp.Mapper/selectLevelsWithParams";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("levelid", "3");
		paramMap.put("levelname", "level5");
		String retn = HttpUtil.doGet(url, setSignature(paramMap));
		System.out.println(retn);
		assertTrue(isSuccess(retn));
	}
	
	@Ignore
	public void testAirQuality() throws Exception{
		String url = baseUrl + "com.cetiti.dsp.AirQuality/stationAirQuality";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("stationCode", "testbycp");
		String retn = HttpUtil.doGet(url, setSignature(paramMap));
		System.out.println(retn);
		assertTrue(isSuccess(retn));
	}
	
	@Ignore
	public void testSelectTableWithDsql() throws Exception{
		String url = baseUrl + "com.cetiti.dsp.Mapper/selectTableWithDsql";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sql", "t_dsp_errorcode where errorid<3");
		String retn = HttpUtil.doPost(url, setSignature(paramMap));
		
		//System.out.println(retn);
		assertTrue(isSuccess(retn));
	}
	
	@Test
	public void testSelectEnterpriseWithDsql() throws Exception{
		String url = baseUrl + "com.cetiti.data.epdc/selectEnterprise";
		Map<String, String> paramMap = new HashMap<String, String>();
		String date = String.valueOf(System.currentTimeMillis());
		String lastUpdateTime = "to_date('2016-12-09 14:56:33','yyyy-mm-dd hh24:mi:ss')";
		String filter = "UPDATE_TIME>" + lastUpdateTime;

		paramMap.put("filter", filter);
		String retn = HttpUtil.doPost(url, setSignature(paramMap));
		
		System.out.println(retn);
		assertTrue(isSuccess(retn));
	}
	
	
	/*public static void main(String[] args) throws Exception{
		RestMessageFilterTest rest = new RestMessageFilterTest();
		long start = System.currentTimeMillis();
		while (true) {
			try {
				long now = System.currentTimeMillis();
				if(now-start>10*1000){
					start = now;
					System.out.println("running"+now);
					rest.testSelectLevelsWithParams();
					if(now%2==0){
						rest.testSelectStrategys();
					}
					if(now%4==0){
						rest.testSelectTableWithDsql();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}*/

}
