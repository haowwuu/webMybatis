/*
 * File Name: AppServiceImpl.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-31

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-31
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cetiti.dsp.dao.AliaseMapper;
import com.cetiti.dsp.ds.DataSourceFactory;
import com.cetiti.dsp.model.KeyValue;
import com.cetiti.dsp.service.AliaseService;


/**
 * 〈一句话功能简述〉
 * 
//具体的数据库增改查删操作
 */

public class AliaseServiceImpl implements AliaseService{

    AliaseMapper aliaseMapper = DataSourceFactory.getMapper(AliaseMapper.class);
    private static Map<String, String> aliasRepos = new ConcurrentHashMap<String, String>();

	public AliaseServiceImpl()
    {
		if(aliasRepos.size()<1){
			for(KeyValue<String, String> kv:aliaseMapper.selectList()){
	            aliasRepos.put(kv.getKey(), kv.getValue());
	        }
		}
    }
	
	@Override
    public String getAliase(String serviceName){
		return aliasRepos.get(serviceName);
    }

	@Override
    public int setAliase(KeyValue<String, String> aliaseInfo) {
        if(null==aliaseInfo||null==aliaseInfo.getKey()||null==aliaseInfo.getValue()
        		||aliaseInfo.getValue().trim().equals("")){
            return 0;
        }
          
        if(getAliase(aliaseInfo.getKey())==null){
            aliasRepos.put(aliaseInfo.getKey(), aliaseInfo.getValue());
            return aliaseMapper.insert(aliaseInfo);
        }else {
            aliasRepos.put(aliaseInfo.getKey(), aliaseInfo.getValue());
            return aliaseMapper.update(aliaseInfo); 
        }
    }

}
