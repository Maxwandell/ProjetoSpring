package com.max.projeto.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();

	public ValidateError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors(){
		return list;
	}
	
	public void addError(String name, String message) {
		list.add(new FieldMessage(name, message));
	}
	

}
