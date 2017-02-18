/*
 * File Name: SoapAirQualityTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-12-16

 * Modifier: Wuwuhao
 * Modify Date: 2016-12-16
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.filter;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;


import com.cetiti.dsp.filter.soapclient.airquality.AirQuality;
import com.cetiti.dsp.filter.soapclient.airquality.AirQualityService;
import com.sun.xml.internal.ws.api.message.Headers;
import com.sun.xml.internal.ws.developer.WSBindingProvider;
import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-12-16
 * @see       
 * @since     DSPlite V0.2.0
 */

public class SoapAirQualityTest {
	private AirQuality airQuality;
	
	@Before
	public void setUp() throws Exception {
		airQuality = new AirQualityService().getAirQualityPort();
	}
	
	private void setHeader(Map<String, String> paramMap){
		Map<String, String> map = RestMessageFilterTest.setSignature(paramMap);
		String sign = map.get("sign");
		String timestamp = map.get("timestamp");
		WSBindingProvider bp = (WSBindingProvider)airQuality;
		bp.setOutboundHeaders(Headers.create(new QName("appKey"), RestMessageFilterTest.appKey),
				Headers.create(new QName("timestamp"), timestamp),
				Headers.create(new QName("sign"), sign) );
	}
	
	@Test
	public void testSelectLevelsWithParams(){
		HttpTransportPipe.dump = true;
		String stationCode = "testbycp";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("stationCode", stationCode);
		setHeader(paramMap);
		String retn = airQuality.stationAirQuality(stationCode);
		System.out.println(retn);
		assertTrue(RestMessageFilterTest.isSuccess(retn));
	}
}
