package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * Something unexpected happened!
 */
@SuppressWarnings("serial")
public class UnknownServerException extends RuntimeException {
	public UnknownServerException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Unexpected Error: " + super.getMessage();
	}
}
