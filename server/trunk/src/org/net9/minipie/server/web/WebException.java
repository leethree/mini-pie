/**
 * WebException.java
 *     in package: * org.net9.minipie.server.web
 * by Mini-Pie Project
 */
package org.net9.minipie.server.web;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public class WebException extends Exception {
	/**
	 * Constructor
	 */
	public WebException() {

	}

	public WebException(Throwable t) {
		super(t);
	}
}
