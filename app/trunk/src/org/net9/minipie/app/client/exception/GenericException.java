/**
 * 
 */
package org.net9.minipie.app.client.exception;

import java.io.Serializable;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public class GenericException extends Exception implements Serializable {

	public GenericException(String message) {
		super(message);
	}

	public GenericException(Throwable throwable) {
		super(throwable.getMessage());
	}
}
