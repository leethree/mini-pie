package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * Throw when the requested resource is not found.
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Not Found: " + super.getMessage();
	}
}
