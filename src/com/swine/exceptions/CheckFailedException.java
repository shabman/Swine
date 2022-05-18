package com.swine.exceptions;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class CheckFailedException extends Exception {

	private static final long serialVersionUID = -4515306377785981322L;

	public CheckFailedException(String message) {
		super(message);
	}
	
	public CheckFailedException(String message, Throwable t) {
		super(message, t);
	}
}
