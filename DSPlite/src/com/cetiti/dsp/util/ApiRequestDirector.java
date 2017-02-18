/*
 * File Name: ApiRequestDirector.java
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

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetiti.dsp.model.APIinfo;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-28
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ApiRequestDirector {
	
	private static final String PARAM_FORMAT = "format";
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestDirector.class);
	
	public static String doRequest(String method, Map<String, String> param){
		ApiResult resultYes = ApiResult.getDefaultActionResult();
		ApiResult resultNo = ApiResult.getDefaultFailResult();
		
		ApiResultFormater formater = ApiResultFormaterFactory.getApiResultFormater(param.get(PARAM_FORMAT));
		param.remove(PARAM_FORMAT);
		
		APIinfo apiInfo = SqlSessionFactoryRepos.getApiInfo(method);
		if(null == apiInfo){
			resultNo.setMsg("apiInfo null");
			LOGGER.debug("apiInfo null method:[{}]", method);
			return formater.format(resultNo.getDataMap());
		}
		
		SqlSessionFactory sqlSessionFactory = apiInfo.getSqlSessionFactory();
		if(null==sqlSessionFactory){
			resultNo.setMsg("sqlSessionFactory null");
			return formater.format(resultNo.getDataMap());
		}
		SqlSession session = sqlSessionFactory.openSession();
		try {
			@SuppressWarnings("rawtypes")
			List<Map> dataList = session.selectList(apiInfo.getFullQualifiedName(), param);
			resultYes.setData(dataList);
			resultYes.setMsg("success");
		} catch (Exception e) {
			LOGGER.error("doRequest: "+e.getMessage());
			resultNo.setMsg(e.getMessage());
			return formater.format(resultNo);
		}finally{
			session.close();
		}
		
		return formater.format(resultYes.getDataMap());
		
	}
}
