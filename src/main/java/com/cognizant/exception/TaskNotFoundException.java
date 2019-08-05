package com.cognizant.exception;

public class TaskNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
