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
public class BackendConnectionException extends Exception implements
		Serializable {

	public BackendConnectionException() {
		super("Service connection failed.");
	}
}
