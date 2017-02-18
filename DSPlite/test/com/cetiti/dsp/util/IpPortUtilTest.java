/*
 * File Name: IpPortUtilTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-14

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-14
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.model.AuditTrail;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-14
 * @see       
 * @since     DSPlite V0.2.0
 */

public class IpPortUtilTest {

	/**
	 * 〈一句话功能简述〉
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	public void test() {
		Integer p = IpPortUtil.getAvailableEndpointPort(8080);
		System.out.print(p);
		Endpoint endpoint = null;
		try {
			endpoint = Endpoint.publish("http://"+IpPortUtil.SERVER_IP+":"+p+"/test", new IpPortUtil.Test());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null!=endpoint){
			System.out.println("available");
			endpoint.stop();
		}
		
		System.out.println(IpPortUtil.SERVER_IP);
	}
	
	@Test
	public void testMem(){
		List<AuditTrail> list = new ArrayList<AuditTrail>();
		while(true){
			try {
				list.add(new AuditTrail());
				System.out.println(list.size());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(list.size());
				break;
			}
		}
		
		System.out.print("finished");
	}

}
