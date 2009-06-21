/**
 * 
 */
package org.net9.minipie.sample.exception;

import java.io.Serializable;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public class GenericException extends Exception implements Serializable {

	public GenericException() {
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(Throwable throwable) {
		super(throwable.getMessage());
	}
}
