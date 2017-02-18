/*
 * File Name: ApiRequestRepos.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-7-22

 * Modifier: Wuwuhao
 * Modify Date: 2016-7-22
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉
 * 
 * @author Wuwuhao
 * @version WSERP V1.0.0, 2016-7-22
 * @see
 * @since WSERP V1.0.0
 */

public class ApiRequestRepos {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestRepos.class);
	private static final int size = 1024;
	private static Map<String, Long> requestHistory = new LinkedHashMap<String, Long>(size, .75F, true) {
		private static final long serialVersionUID = 4267176411845948333L;
		@Override
		protected boolean removeEldestEntry(Map.Entry<String, Long> eldest) {
			return size() > size;
		}
	};
	
	/**
	 * element life time how long an element can be kept in the repos
	 */
	private static Long eleLifeTimeInSecond = 600 * 1000l;

	public static boolean isValidReq(String appKey, String timestamp) {
		if (null == appKey || null == timestamp) {
			return false;
		}

		Long reqTime;
		try {
			reqTime = Long.parseLong(timestamp);
		} catch (NumberFormatException e) {
			LOGGER.error("timestamp: [{}] NumberFormatException!", timestamp, e.getMessage());
			return false;
		}

		String key = appKey + timestamp;
		synchronized (requestHistory) {
			if (null == requestHistory.get(key)&&System.currentTimeMillis()-reqTime < eleLifeTimeInSecond) {
				requestHistory.put(key, reqTime);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @return the eleLifeTimeInSecond
	 */
	public static Long getLifeTimeInSecond() {
		return eleLifeTimeInSecond;
	}

	/**
	 * @param eleLifeTimeInSecond
	 *            the eleLifeTimeInSecond to set
	 */
	public static void setEleLifeTimeInSecond(Long eleLifeTimeInSecond) {
		eleLifeTimeInSecond = TimeUnit.SECONDS.toMillis(eleLifeTimeInSecond);
	}

	
	public static void refresh() {
		long now = System.currentTimeMillis();
		synchronized (requestHistory) {
			for (Entry<String, Long> entry : requestHistory.entrySet()) {
				if (now - entry.getValue() > eleLifeTimeInSecond) {
					requestHistory.remove(entry.getKey());
				}
			}
		}
	}

}
