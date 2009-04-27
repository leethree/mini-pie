package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * Throw when permission is denied for this operation
 */
@SuppressWarnings("serial")
public class PermissionDeniedException extends RuntimeException {
	public PermissionDeniedException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Permission Denied: " + super.getMessage();
	}
}
