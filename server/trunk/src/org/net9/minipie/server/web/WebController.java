/**
 * WebController.java
 *     in package: * org.net9.minipie.server.web
 * by Mini-Pie Project
 */
package org.net9.minipie.server.web;

import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.SignUp;

/**
 * @author LeeThree
 * 
 */
public class WebController {

	public static long signUp(String username, String email, String pass)
			throws WebException {
		try {
			return new Handler<Long>(new SignUp(username, email, pass))
					.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebException(e);
		}
	}
}
