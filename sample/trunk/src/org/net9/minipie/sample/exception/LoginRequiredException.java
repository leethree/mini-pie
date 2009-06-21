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
public class LoginRequiredException extends Exception implements Serializable {

	public LoginRequiredException() {
		super("Login is required");
	}
}
