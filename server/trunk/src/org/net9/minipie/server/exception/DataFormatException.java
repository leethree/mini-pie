/**
 * DataFormatException.java
 *     in package: * org.net9.minipie.server.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.exception;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public class DataFormatException extends Exception {

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public DataFormatException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "Data Format Error: " + super.getMessage();
	}
}
