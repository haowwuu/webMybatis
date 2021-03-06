/*
 * File Name: InitListener.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-28

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-28
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cetiti.dsp.service.impl.AuditTrailServiceImpl;
import com.cetiti.dsp.soap.ServicePublisher;
import com.cetiti.dsp.util.LoggerConfig;
import com.cetiti.dsp.util.MapperConfigReader;
import com.cetiti.dsp.util.Timer;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class InitListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LoggerConfig.initLog4jConfig();
		MapperConfigReader.InitConn();
		
		Timer.startup();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		AuditTrailServiceImpl.stopLogService();
		ServicePublisher.stop();
		Timer.shutdown();
	}

}
