/*
 * File Name: APIServlet.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-26

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetiti.dsp.util.ApiRequestDirector;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-26
 * @see       
 * @since     DSPlite V0.2.0
 */

public class APIServlet extends HttpServlet{
	
	private static final long serialVersionUID = -7274620566444374306L;
	private static final Logger LOGGER = LoggerFactory.getLogger(APIServlet.class);
	
	public static final String API_PATH = "/api/";
	public static final int API_PATH_LENGTH = API_PATH.length();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException,IOException{
		
		Map<String, String> parameterMap = getRequestParameterMap(request);
		String uri = request.getRequestURI();
		String method = uri.substring(uri.indexOf(API_PATH)+API_PATH_LENGTH);
		LOGGER.debug("request method:[{}], parameters:[{}]", method, parameterMap.toString());
		String result = ApiRequestDirector.doRequest(method, parameterMap);
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		
	}
		   
	@Override	    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException{
        doGet(request, response);
    }	
	
	public static Map<String, String> getRequestParameterMap(HttpServletRequest request){
		Map<String, String> pMap = new HashMap<String, String>();
		Enumeration<String> enm = request.getParameterNames();
		while (enm.hasMoreElements()) {
			String pName = (String) enm.nextElement();
			String pValue = request.getParameter(pName);
			pMap.put(pName, pValue);
		}
		return pMap;
	}

}
