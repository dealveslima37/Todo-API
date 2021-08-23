package com.cromws.todoapi.services.exceptions;

public class ExistingEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingEmailException(String msg) {
		super(msg);
	}

}
