/*
 * File Name: SoapMessageFilterTest.java
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

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.filter.soapclient.Mapper;
import com.cetiti.dsp.filter.soapclient.MapperService;
import com.sun.xml.internal.ws.api.message.Headers;
import com.sun.xml.internal.ws.developer.WSBindingProvider;
import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class SoapMessageFilterTest {

	//wsimport -s E:\MyEclipse\workSpace\DSPlite\test -p com.cetiti.dsp.filter.soapclient.airquality -keep http://10.70.7.72:8081/com.cetiti.dsp/AirQuality?wsdl
	//wsimport -s E:\MyEclipse\workSpace\DSPlite\test -p com.cetiti.dsp.filter.soapclient -keep http://10.70.7.72:8081/com.cetiti.dsp/Mapper?wsdl
	private Mapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mapper = new MapperService().getMapperPort();
	}
	
	private void setHeader(Map<String, String> paramMap){
		Map<String, String> map = RestMessageFilterTest.setSignature(paramMap);
		String sign = map.get("sign");
		String timestamp = map.get("timestamp");
		WSBindingProvider bp = (WSBindingProvider)mapper;
		bp.setOutboundHeaders(Headers.create(new QName("appKey"), RestMessageFilterTest.appKey),
				Headers.create(new QName("timestamp"), timestamp),
				Headers.create(new QName("sign"), sign) );
	}
	
	@Ignore
	public void testSelectStrategys() {
		//HttpTransportPipe.dump = true;
		setHeader(null);
		String retn = mapper.selectStrategys();
		assertTrue(RestMessageFilterTest.isSuccess(retn));
		//System.out.println(retn);
	}
	
	@Test
	public void testSelectLevelsWithParams(){
		HttpTransportPipe.dump = true;
		String levelid = "4";
		String levelname = "level5";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("levelid", levelid);
		paramMap.put("levelname", levelname);
		setHeader(paramMap);
		String retn = mapper.selectLevelsWithParams(levelid, levelname);
		System.out.println(retn);
		assertTrue(RestMessageFilterTest.isSuccess(retn));
	}
	
	@Ignore
	public void testSelectTableWithDsql(){
		String sql = "t_dsp_errorcode where errorid < 6";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sql", sql);
		setHeader(paramMap);
		String retn = mapper.selectTableWithDsql(sql);
		//System.out.println(retn);
		assertTrue(RestMessageFilterTest.isSuccess(retn));
	}
	
}
