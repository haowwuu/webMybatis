/*
 * File Name: QuantityServlet.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-2

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-2
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cetiti.dsp.model.APIinfo;
import com.cetiti.dsp.model.QuantityInfo;
import com.cetiti.dsp.service.QuantityService;
import com.cetiti.dsp.service.impl.QuantityServiceImpl;
import com.cetiti.dsp.util.ApiResult;
import com.cetiti.dsp.util.SqlSessionFactoryRepos;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-2
 * @see       
 * @since     DSPlite V0.2.0
 */

public class QuantityServlet extends DispatcherServlet{
	
	private static final long serialVersionUID = 1L;
	private QuantityService quantityService = new QuantityServiceImpl();
	private ApiResult retn = ApiResult.getDefaultActionResult();
	
	public void query(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("data", quantityService.queryAll());
		ApiResult.writeToResponse(response, data);
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response){
		QuantityInfo quantity = getObjFromRequest(QuantityInfo.class, request);
		quantityService.add(quantity);
		retn.writeToResponse(response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response){
		QuantityInfo quantity = getObjFromRequest(QuantityInfo.class, request);
		quantityService.update(quantity);
		retn.writeToResponse(response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response){
		String quantityId = request.getParameter("quantityId");
		quantityService.delete(quantityId);
		retn.writeToResponse(response);
	}
	
	public void methods(HttpServletRequest request, HttpServletResponse response){
		List<String> methods = new ArrayList<String>();
		Collection<APIinfo> apiInfos = SqlSessionFactoryRepos.getApiInfos();
		for(APIinfo api:apiInfos){
			methods.add(api.getApiName());
		}
		ApiResult.writeToResponse(response, methods);
	}
}
