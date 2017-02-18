/*
 * File Name: AuditTrailServiceImplTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-17

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-17
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cetiti.dsp.service.AuditTrailService;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-17
 * @see       
 * @since     DSPlite V0.2.0
 */

public class AuditTrailServiceImplTest {
	
	AuditTrailService atService = new AuditTrailServiceImpl();
	
	/**
	 * 〈一句话功能简述〉
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	
	@Ignore
	public void testStopLogService() {
		fail("Not yet implemented");
	}

	
	@Ignore
	public void testTrail() {
		fail("Not yet implemented");
	}

	@Test
	public void testStatisticByApi() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -7);
		System.out.println(atService.statisticByApi(cal.getTime(), null, null, 5));
	}

	@Ignore
	public void testStatisticByKey() {
		fail("Not yet implemented");
	}

}
