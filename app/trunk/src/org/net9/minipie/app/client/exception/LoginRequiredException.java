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
public class LoginRequiredException extends Exception implements Serializable {

	public LoginRequiredException() {
		super("Login is required");
	}
}
