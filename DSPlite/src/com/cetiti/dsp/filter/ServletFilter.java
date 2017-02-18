/*
 * File Name: ServletFilter.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-12-20

 * Modifier: Wuwuhao
 * Modify Date: 2016-12-20
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-12-20
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ServletFilter implements Filter{
	
	private Set<String> bypassServletSet = new HashSet<String>();
	
	@Override
	public  void init(FilterConfig filterconfig) throws ServletException{
		String bypassServlets = filterconfig.getInitParameter("bypass-servlet");
		String[] arr = bypassServlets.split(",");
		if(null!=arr&&arr.length>0){
			for(String s:arr){
				bypassServletSet.add(s.trim());
			}
		}
	}
	
	@Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse,
    		FilterChain filterchain) throws IOException, ServletException{
    	HttpServletRequest request = (HttpServletRequest)servletrequest;
    	String uri = request.getRequestURI();
    	String context = request.getContextPath();
    	String servletPath = uri.substring(uri.indexOf(context)+context.length());
    	
    	if(bypassServletSet.contains(servletPath)||PageFilter.hasPrivilege(request)){
    		filterchain.doFilter(servletrequest, servletresponse);
    		return;
    	}
    	
    	HttpServletResponse response = (HttpServletResponse)servletresponse;
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    	return;
    }
    
    public void destroy(){
    	
    }
}
