/*
 * File Name: ConsoleServlet.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-9-19

 * Modifier: Wuwuhao
 * Modify Date: 2016-9-19
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cetiti.dsp.util.ApiResult;
import com.cetiti.dsp.util.InstructionParser;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-9-19
 * @see       
 * @since     DSPlite V0.2.0
 */

public class ConsoleServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1163101323491624724L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException,IOException{
		String instruction = request.getParameter("instruction");
		String retn = InstructionParser.parse(instruction);
		if(null==retn){
			StringBuilder help = new StringBuilder();
			help.append("<html><body>");
			help.append("<h3>instruction:</h3>");
			for(String cmd:InstructionParser.instructionSet()){
				help.append("<p>");
				help.append(cmd);
				help.append("-arg0,arg1</p>");
			}
			help.append("<p>example: ~console?instruction=");
			help.append("deleteService-com.cetiti.dsp.Mapper,selectLevelsWithParams");
			help.append("</body></html>");
			ApiResult.writeToResponse(response, help.toString());
		}else{
			ApiResult.writeToResponse(response, retn);
		}
	}
	   
	@Override	    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException{
        doGet(request, response);
    }	
}
