/**
 * ServerErrorException.java
 *     in package: * org.net9.minipie.server.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.exception;

/**
 * @author LeeThree Something unexpected happened!
 */
@SuppressWarnings("serial")
public class ServerErrorException extends RuntimeException {
	public ServerErrorException(String message) {
		super("Unexpected Server Error: " + message);
	}

	public ServerErrorException(Throwable cause) {
		super("Unexpected Server Error: " + cause.getMessage(), cause);
	}
}
