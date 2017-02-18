/*
 * File Name: LoggerConfig.java
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

import java.io.File;
import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class LoggerConfig {
	
	private static final String LOG4J_CONFIG_FILENAME = "log4j.xml";
	
	public static void initLog4jConfig(){
		File configfile = null;
		try {
			configfile = Resources.getResourceAsFile(LOG4J_CONFIG_FILENAME);
		} catch (IOException e) {
			System.err.println("log4j initialize exception, can't find file " +
					"["+LOG4J_CONFIG_FILENAME+"]. message:"+e.getMessage());
			return;
		}
		
		String path = configfile.getAbsolutePath();
		DOMConfigurator.configureAndWatch(path);
	}
	
}
