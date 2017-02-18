/*
 * File Name: ConfigrationTest.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-12-21

 * Modifier: Wuwuhao
 * Modify Date: 2016-12-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-12-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ConfigrationTest {

	/**
	 * 〈一句话功能简述〉
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		LoggerConfig.initLog4jConfig();
	}

	@Test
	public void test() {
		
		//LOGGER.error("12123");
	
		System.out.println("soapAddress:"+Configration.INSTANCE.getServerAddress());
		System.out.println("soapPort:"+Configration.INSTANCE.getSoapPort());
	}


}
