/*
 * File Name: AuditTrailMapper.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-21

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cetiti.dsp.model.AuditTrail;
import com.cetiti.dsp.model.KeyValue;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-21
 * @see       
 * @since     DSPlite V0.2.0
 */

public interface AuditTrailMapper {
	
	@Insert("insert into audit_trail (audit_id, app_key, api_method, api_type, parameters, audit_date) " +
			"values (#{auditId}, #{appKey}, #{apiMethod}, #{apiType}, #{parameters}, #{auditDate})")
	int insert(AuditTrail auditTrail);
	
	@Select("select * from audit_trail")
	@Results({ 
		@Result(property = "auditId", column = "audit_id"), 
	    @Result(property = "appKey", column = "app_key"), 
	    @Result(property = "apiMethod", column = "api_method"),
		@Result(property = "apiType", column = "api_type"),
	    @Result(property = "parameters", column = "parameters"),
		@Result(property = "auditDate", column = "audit_date")
	})
	List<AuditTrail> selectList();
	
	@Select("select * from audit_trail " +
			"where audit_date between #{0} and #{1} ")
	@Results({ 
		@Result(property = "auditId", column = "audit_id"), 
	    @Result(property = "appKey", column = "app_key"), 
	    @Result(property = "apiMethod", column = "api_method"),
		@Result(property = "apiType", column = "api_type"),
	    @Result(property = "parameters", column = "parameters"),
		@Result(property = "auditDate", column = "audit_date")
	})
	List<AuditTrail> selectListByTime(Date begin, Date end);
	
	@Select("select * from audit_trail " +
			"where audit_date between #{0} and #{1} and app_key = #{2} ")
	@Results({ 
		@Result(property = "auditId", column = "audit_id"), 
	    @Result(property = "appKey", column = "app_key"), 
	    @Result(property = "apiMethod", column = "api_method"),
		@Result(property = "apiType", column = "api_type"),
	    @Result(property = "parameters", column = "parameters"),
		@Result(property = "auditDate", column = "audit_date")
	})
	List<AuditTrail> selectListByTimeAndKey(Date begin, Date end, String appKey);
	
	@Select("select * from audit_trail where audit_date " +
			"between #{0} and #{1} and api_method = #{2}")
	@Results({ 
		@Result(property = "auditId", column = "audit_id"), 
	    @Result(property = "appKey", column = "app_key"), 
	    @Result(property = "apiMethod", column = "api_method"),
		@Result(property = "apiType", column = "api_type"),
	    @Result(property = "parameters", column = "parameters"),
		@Result(property = "auditDate", column = "audit_date")
	})
	List<AuditTrail> selectListByTimeAndMethod(Date begin, Date end, String apiMethod);
	
	@Select("select app_key, count(*) as count from audit_trail " +
			"where audit_date between #{0} and #{1} " +
			"group by app_key order by count desc limit #{2}")
	@Results({ 
	    @Result(property = "key", column = "app_key"), 
		@Result(property = "value", column = "count")
	})
	List<KeyValue<String, Integer>> topNKeys(Date begin, Date end, int n);
	
	@Select("select api_method, count(*) as count from audit_trail " +
			"where audit_date between #{0} and #{1} " +
			"group by api_method order by count desc limit #{2}")
	@Results({ 
	    @Result(property = "key", column = "api_method"),
		@Result(property = "value", column = "count")
	})
	List<KeyValue<String, Integer>> topNMethods(Date begin, Date end, int n);
	
	@Select("select audit_id from audit_trail order by audit_id desc limit #{n}, 1")
	String selectTheNthID(int n);
	
	@Delete("delete from audit_trail where audit_id < #{audit_id}")
	int delete(String audit_id);
}
