package com.ashish.fileinputoutput;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseResponse {
	
	public static String DATA = "data";
	public static String INPUT_ERROR = "inputerror";
	
	public static final String MESSAGE = "message";
	public static final String FAILED="Wrong Authentication..";
    public static final String SUCCESS="Login Successfully..";
    public static final String SAVE="Record Save Successfully..";
    public static final String UPDATE="Record Update Successfully..";
    public static final String EMAIL="Already Exit ..";
    public static final String LOGOUT="Logout Successfully ..";
    public static final String REGISTRATION=" Your Registration Successfully ..";
 
	private boolean success = false;
    private Map<String, Object> result = new HashMap<String, Object>();

	public BaseResponse() {
	}

	
	
	public BaseResponse(boolean success) {
		this.success = success;
	}

	public BaseResponse(boolean success, String message) {
		this.success = success;
		addMessage(message);
	}

	public BaseResponse(boolean success, String message, Object value) {
		this.success = success;
		addMessage(message);
		addData(value);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public void addResult(String key, Object value) {
		result.put(key, value);
	}

	public void addData(Object value) {
		result.put(DATA, value);
	}

	public void addInputErrors(Object value) {
		result.put(INPUT_ERROR, value);
	}

	public void addMessage(Object value) {
		result.put(MESSAGE, value);
	}
	
	
	public void redirect(String name) throws IOException
	{
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
		pageRedirect(response,name);
		
	}
	
	 public static void  pageRedirect(HttpServletResponse response,String name) throws IOException
	  {
		  response.sendRedirect(name);
	   }

	 
	


}
