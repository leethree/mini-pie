package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * Throw when the given request is not valid.
 */
@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Invalid Request: " + super.getMessage();
	}
}
