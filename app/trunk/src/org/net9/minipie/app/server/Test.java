/**
 * 
 */
package org.net9.minipie.app.server;

import java.util.UUID;

import org.net9.minipie.app.client.data.GenericBean;
import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.data.TagBean;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;

/**
 * @author LeeThree
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Session("parkh", "qwerrwszds");
		} catch (GenericException e) {
			e.printStackTrace();
		} catch (LoginFailedException e) {
			e.printStackTrace();
		}
	}

}
