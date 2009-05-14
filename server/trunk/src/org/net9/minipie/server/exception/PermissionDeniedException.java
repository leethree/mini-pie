/**
 * PermissionDeniedException.java
 *     in package: * org.net9.minipie.server.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * Throw when permission is denied for this operation
 */
@SuppressWarnings("serial")
public class PermissionDeniedException extends RuntimeException {
	public PermissionDeniedException(String message) {
		super("Permission Denied: " + message);
	}
}
