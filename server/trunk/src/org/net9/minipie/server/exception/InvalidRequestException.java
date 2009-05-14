/**
 * InvalidRequestException.java
 *     in package: * org.net9.minipie.server.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.exception;

/**
 * @author LeeThree Throw when the given request is not valid.
 */
@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		super("Invalid Request: " + message);
	}

	public InvalidRequestException(DataFormatException ex) {
		super("Invalid Request: " + ex.getMessage(), ex);
	}
}
