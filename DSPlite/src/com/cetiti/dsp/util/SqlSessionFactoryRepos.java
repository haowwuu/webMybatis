/*
 * File Name: SqlSessionFactoryRepos.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-26

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-26
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.ChooseSqlNode;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;
import org.apache.ibatis.scripting.xmltags.TrimSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetiti.dsp.model.APIinfo;
import com.cetiti.dsp.service.ResourcePathService;
import com.cetiti.dsp.service.impl.ResourcePathServiceImpl;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-8-26
 * @see       
 * @since     DSPlite V0.2.0
 */

public class SqlSessionFactoryRepos {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SqlSessionFactoryRepos.class);

	private static Map<String, SqlSessionFactory> sessionFactoryRepos = new ConcurrentHashMap<String, SqlSessionFactory>();
	private static Map<String, APIinfo> apiRepos = new ConcurrentHashMap<String, APIinfo>();
	
	public static boolean hasSqlSessionFactory(SqlSessionFactory ssf){
		return null!=sessionFactoryRepos.get(getDataSourceKey(ssf));
	}
	
	public static SqlSessionFactory getSessionFactory(String key){
		return sessionFactoryRepos.get(key);
	}
	
	public static int size(){
		return sessionFactoryRepos.size();
	}
	
	public static SqlSessionFactory remove(String key){
		if(null==key){
			return null;
		}
		String path = null;
		for(Map.Entry<String, APIinfo> entry: apiRepos.entrySet()){
			if(key.equals(entry.getValue().getDataSource())){
				if(null==path){
					path = entry.getValue().getMetadataFile();
				}
				apiRepos.remove(entry.getKey());
			}
		}
		if(null!=path){
			try {
				ResourcePathService rpService = new ResourcePathServiceImpl();
				rpService.delete(path);
			} catch (Exception e) {
				LOGGER.error("remove[{}], [{}]",key, e.getMessage());
			}
			
		}
		return sessionFactoryRepos.remove(key);
	}
	
	public static void clear(){
		apiRepos.clear();
		try {
			ResourcePathService rpService = new ResourcePathServiceImpl();
			rpService.clear();
		} catch (Exception e) {
			LOGGER.error("clear[{}]", e.getMessage());
		}
		sessionFactoryRepos.clear();
	}
	
	public static APIinfo removeServcice(String nameSpace, String id){
		if(null==nameSpace){
			return null;
		}
		APIinfo info, last = null;
		for(Map.Entry<String, APIinfo> entry: apiRepos.entrySet()){
			if(nameSpace.equals(entry.getValue().getNameSpace())){
				if(null==id){
					info=apiRepos.remove(entry.getKey());
					if(null!=info){
						last=info;
					}
				}else if(id.equals(entry.getValue().getSelectId())){
					info=apiRepos.remove(entry.getKey());
					if(null!=info){
						last=info;
					}
				}
			}
		}
		return last;
	}
	
	private static String getDataSourceKey(SqlSessionFactory ssf){
		String key = null;
		if(null==ssf){
			return key;
		}	
		DataSource ds = ssf.getConfiguration().getEnvironment().getDataSource();
		return getDataSourceKey(ds);
	}
	
	public static String getDataSourceKey(DataSource ds){
		String key=null;
		if(ds instanceof UnpooledDataSource){
			UnpooledDataSource upds = (UnpooledDataSource) ds;
			key = formatDatasourceUrl(upds.getUrl())+formatDatasourceUser(upds.getUsername());	
		}else if(ds instanceof PooledDataSource){
			PooledDataSource pds = (PooledDataSource)ds;
			key = formatDatasourceUrl(pds.getUrl())+formatDatasourceUser(pds.getUsername());
		}else if(null != ds){
			key = String.valueOf(ds.hashCode());
		}
		
		return key;
	}
	
	private static String formatDatasourceUrl(String url){
		if(null==url){
			return null;
		}
		int pindex = url.indexOf("?");
		if(pindex<0){
			return url;
		}else {
			return url.substring(0, pindex);
		}
	}
	
	private static String formatDatasourceUser(String user){
		return "-"+user;
	}
	
	public static SqlSessionFactory addSqlSessionFactory(SqlSessionFactory ssf, String metadataFile){
		if(null == ssf){
			return null;
		}
		String key = getDataSourceKey(ssf);
		if(null==key){
			return null;
		}
		
		addApiInfo(ssf, metadataFile);
		return sessionFactoryRepos.put(key, ssf);
	}
	
	private static void addApiInfo(SqlSessionFactory ssf, String metadataFile){
		Configuration config = ssf.getConfiguration();
		Map<String, APIinfo> apiMap = new HashMap<String, APIinfo>();
		String datasourceKey = getDataSourceKey(ssf);
		for(String name:config.getMappedStatementNames()){
			int idx = name.lastIndexOf(".");
			if(idx>0){
				APIinfo apiInfo = new APIinfo();
				String nameSpace = name.substring(0, idx);
				String selectId = name.substring(idx+1);
				apiInfo.setNameSpace(nameSpace);
				apiInfo.setSelectId(selectId);
				String apiName = generateApiName(selectId, nameSpace);
				if(null!=getApiInfo(apiName)){
					throw new BuilderException("API ["+name+"] already exist.");
				}
				apiInfo.setApiName(apiName);
				apiInfo.setFullQualifiedName(name);
				MappedStatement ms = config.getMappedStatement(name);
				ParamObj paramObj = getParametersFormMappedStatement(ms);
				if(null!=paramObj){
					if(null!=paramObj.params&&paramObj.params.size()>0){
						apiInfo.setSqlType(paramObj.type);
					}else{
						apiInfo.setSqlType("");
					}
					apiInfo.setParameters(paramObj.params);
					apiInfo.setSqlText(paramObj.sql);
				}
				apiInfo.setDataSource(datasourceKey);
				apiInfo.setMetadataFile(metadataFile);
				if(null==apiRepos.get(apiName)){
					apiMap.put(apiName, apiInfo);
				}
			}
		}
		apiRepos.putAll(apiMap);
	}
	
	public static String generateApiName(String selectId, String nameSpace){
		/*return selectId+Math.abs(nameSpace.hashCode());*/
		return nameSpace + "/" +selectId;
	}
	
	public static APIinfo getApiInfo(String apiName){
		return apiRepos.get(apiName);
	}
	
	public static APIinfo getApiInfoClone(String apiName){
		 APIinfo api = apiRepos.get(apiName);
		 if(null!=api){
			 try {
				return api.clone();
			} catch (Exception e) {
			}
		 }
		 return null;
	}
	
	public static Collection<APIinfo> getApiInfos(){
	    List<APIinfo> apiInfos = new ArrayList<APIinfo>();
	    for(APIinfo api:apiRepos.values()){
	        try{
	            apiInfos.add(api.clone());
	        }catch (Exception e) {
                LOGGER.error("getApiInfos()"+e.getMessage());
            }
	    }
		return apiInfos;
	}
	
	private static class ParamObj{
		String type;
		String sql;
		List<String> params;
	}
	
	private static ParamObj getParametersFormMappedStatement(MappedStatement ms){
		ParamObj paramObj = new ParamObj();
		SqlSource ss = ms.getSqlSource();
		
		if(ss instanceof RawSqlSource)
		{
			BoundSql sql = ss.getBoundSql(null);
			List<String> params = new ArrayList<String>();
			for(ParameterMapping pm: sql.getParameterMappings()){
				params.add(pm.getProperty());
			}
			paramObj.params=params;
			paramObj.sql=sql.getSql();
		}
		else if(ss instanceof DynamicSqlSource)
		{
			Object obj = getPrivateField(ss, "rootSqlNode");
			if(null==obj)
				return null;
			
			paramObj.params = getParamFromNode((SqlNode)obj);
			BoundSql sql = ss.getBoundSql(null);
			paramObj.sql = sql.getSql();
		}
		paramObj.type = ss.getClass().getSimpleName();
		
		return paramObj;
	}
	
	private static Object getPrivateField(Object obj, String field){
		Class<?> clazz = obj.getClass();
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return f.get(obj);
		} catch (Exception e) {
			clazz = clazz.getSuperclass();
			try {
				Field f = clazz.getDeclaredField(field);
				f.setAccessible(true);
				return f.get(obj);
			} catch (Exception e2) {
				LOGGER.error(obj.getClass().getCanonicalName()+"-getPrivateField()"+e.getMessage());
			}
		}
		
		return null;
	}
	
	private static List<String> getParamFromSql(String sqlText){
		List<String> paramsList = new ArrayList<String>();
		if(null == sqlText){
			return paramsList;
		}
		int sidx = sqlText.indexOf("${");
		if(sidx<0){
			sidx = sqlText.indexOf("#{");
		}
		if(sidx<0){
			return paramsList;
		}
		int eidx = sqlText.indexOf("}");
		if(eidx<0){
			return paramsList;
		}
		String param = sqlText.substring(sidx+2,eidx);
		paramsList.add(param);
		List<String> param2 = getParamFromSql(sqlText.substring(eidx+1));
		if(param2.size()>0){
			paramsList.addAll(param2);
		}
		
		return paramsList;
	}
	
	private static List<String> getParamFromNode(SqlNode sqlNode){
		List<String> pList = new ArrayList<String>();
		if(sqlNode instanceof MixedSqlNode||sqlNode instanceof IfSqlNode
				||sqlNode instanceof TrimSqlNode||sqlNode instanceof ForEachSqlNode){
			Object contents = getPrivateField(sqlNode, "contents");
			if(null==contents)
				return pList;
			if(contents instanceof List<?>){
				@SuppressWarnings("unchecked")
				List<SqlNode> nodes = (List<SqlNode>)contents;
				for(SqlNode node:nodes){
					pList.addAll(getParamFromNode(node));
				}
			}else if(contents instanceof SqlNode){
				pList.addAll(getParamFromNode((SqlNode)contents));
			}
		}else if(sqlNode instanceof TextSqlNode||sqlNode instanceof StaticTextSqlNode){
			Object sqlText = getPrivateField(sqlNode, "text");
			return getParamFromSql(sqlText.toString());
		}else if(sqlNode instanceof ChooseSqlNode){
			Object defaultSqlNode = getPrivateField(sqlNode, "defaultSqlNode");
			if(null!=defaultSqlNode){
				pList.addAll(getParamFromNode((SqlNode)defaultSqlNode));
			}
			Object ifSqlNodes = getPrivateField(sqlNode, "ifSqlNodes");
			if(null!=ifSqlNodes){
				@SuppressWarnings("unchecked")
				List<SqlNode> nodes = (List<SqlNode>)ifSqlNodes;
				for(SqlNode node:nodes){
					pList.addAll(getParamFromNode(node));
				}
			}
		}
		
		return pList;
	}
	
}
