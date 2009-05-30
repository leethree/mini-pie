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
public class LoginFailedException extends Exception implements Serializable {

	public LoginFailedException() {
		super("Invalid username or password.");
	}
}
