/*
 * File Name: DispatcherServlet.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-31

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-31
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-31
 * @see       
 * @since     DSPlite V0.2.0
 */

public abstract class DispatcherServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1369962688398185766L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);

	protected static Method[] getAllDeclaredMethods(Class<?> c) {
        if (c.equals(DispatcherServlet.class)) {
            return null;
        }

        Method[] parentMethods = getAllDeclaredMethods(c.getSuperclass());
        Method[] thisMethods = c.getDeclaredMethods();

        if ((parentMethods != null) && (parentMethods.length > 0)) {
            Method[] allMethods = new Method[parentMethods.length + thisMethods.length];
            System.arraycopy(parentMethods, 0, allMethods, 0, parentMethods.length);
            System.arraycopy(thisMethods, 0, allMethods, parentMethods.length, thisMethods.length);
            thisMethods = allMethods;
        }

        return thisMethods;
    }
	
	@Override
	protected final void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException,IOException{
		Method[] methods = getAllDeclaredMethods(this.getClass());
		String uri = request.getRequestURI();
		String reqMethod = uri.substring(uri.lastIndexOf('/')+1);
		
		Method method = null;
		for (Method m:methods) {
			if(reqMethod.equals(m.getName())){
				method = m;
				break;
			}
		}
		
		if(null==method){
			 response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, reqMethod + " not implemented");
			 return;
		}
		
		try {
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override	    
	protected final void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException{
        doGet(request, response);
    }
	
	public static <T> T getObjFromRequest(Class<T> clazz, HttpServletRequest request) {
		T obj = null;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Field[] fields = clazz.getDeclaredFields();
		Class<?> c = clazz;
		while (!c.equals(Object.class)) {
			c = c.getSuperclass();
			Field[] superFields = c.getDeclaredFields();
			if (superFields != null && superFields.length > 0) {
				Field[] allFileds = new Field[fields.length + superFields.length];
				System.arraycopy(superFields, 0, allFileds, 0, superFields.length);
				System.arraycopy(fields, 0, allFileds, superFields.length, fields.length);
				fields = allFileds;
			}
		}
		for (Field f : fields) {
			String fieldName = f.getName();
			String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Class<?> type = f.getType();
				String typeName = type.getName();
				Method method = clazz.getMethod(methodName, type);
				String v = request.getParameter(f.getName());
				if(null==method||null==v){
					continue;
				}else if (type.equals(String.class)||type.equals(Object.class)) {
					method.invoke(obj, v);
				}else if (type.equals(Integer.class)||typeName.equals("int")) {
					method.invoke(obj, Integer.parseInt(v));
				}else if (type.equals(Long.class)||typeName.equals("long")) {
					method.invoke(obj, Long.parseLong(v));
				}else if (type.equals(Date.class)) {
					method.invoke(obj, new SimpleDateFormat().parse(v));
				}else if (type.equals(BigDecimal.class)) {
					method.invoke(obj, new BigDecimal(v));
				}else {
					try {
						method.invoke(obj, v);
					} catch (Exception e) {
						LOGGER.error("getObjFromRequest, set value error: [{}]", e.getMessage());
					}
				}
			} catch (Exception e) {
				
			}
		}
		return obj;
	}
	
	public static String getBasePath(HttpServletRequest request){
		if(null==request){
			return null;
		}
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+
			   request.getContextPath()+APIServlet.API_PATH;
	}
}
