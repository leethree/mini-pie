/**
 * NotFoundException.java
 *     in package: * org.net9.minipie.server.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * Throw when the requested resource is not found.
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super("Not Found: " + message);
	}
}
