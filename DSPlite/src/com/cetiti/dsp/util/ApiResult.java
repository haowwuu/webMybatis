package com.cetiti.dsp.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


/**
 * 〈一句话功能简述〉
 * 
 * @author    Qiuzipeng
 * @version   SSO V0.1.0, 2016-5-11
 * @see       
 * @since     SSO V1.0.0
 */
public class ApiResult
{
    public enum CODE
    {
        RET_OK("0"),
        RET_NO("1"),
        RET_NO_PRIVILEGE("2");
        
        private String value;
        private CODE(String value)
        {
            this.value = value;
        }
        
        public String toString()
        {
            return value;
        }  
    }
    
    Map<String, Object> map = new HashMap<String, Object>();
    
    public static ApiResult getDefaultActionResult()
    {
    	ApiResult actionResult = new ApiResult();
    	actionResult.setResultcode(CODE.RET_OK);
    	actionResult.setMsg("");
    	return actionResult;
    }
    
    public static ApiResult getDefaultFailResult()
    {
        ApiResult actionResult = new ApiResult();
        actionResult.setResultcode(CODE.RET_NO);
        actionResult.setMsg("");
        return actionResult;
    }
    
    public  Map<String, Object> getDataMap()
	{
		return map;
	}
    
    public void setResultcode(CODE result)
    {
    	 map.put("resultcode", result.toString());
    }
    
    public void setInfo(String key,Object obj)
    {
    	 map.put(key, obj);
    }
    
    public void setData(Object data)
    {
    	 map.put("data", data);
    }
    
    public void setMsg(Object msg)
    {
         map.put("message", msg);
    }
    
    public Object getMsg()
    {
        return map.get("message");
    }
    
    public boolean isOk()
    {
        return CODE.RET_OK.toString().equals(map.get("resultcode"));
    }
    
    public void writeToResponse(HttpServletResponse response) {
    	writeToResponse(response, this.getDataMap());
    }
    
    public static void writeToResponse(HttpServletResponse response, Object result) {
    	writeToResponse(response, result, null);
    }
    
    public static void writeToResponse(HttpServletResponse response, Object result, String type) {
    	if(null==type){
    		response.setContentType("text/html;charset=UTF-8");
    	}else {
			response.setContentType(type);	
		}
    	if(!(result instanceof String)){
    		ApiResultFormater formater = ApiResultFormaterFactory.getApiResultFormater("json");
    		result = formater.format(result);
    	}
    	PrintWriter out = null;
		try {
			 out = response.getWriter();
		} catch (Exception e) {
			
		}
		out.print(result);
		out.flush();
		out.close();
    }
}
