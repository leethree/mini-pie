/**
 * WebController.java
 *     in package: * org.net9.minipie.server.web
 * by Mini-Pie Project
 */
package org.net9.minipie.server.web;

import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.SignUp;

/**
 * @author LeeThree
 * 
 */
public class WebController {

	public static long signUp(String username, String displayname, String email, String pass)
			throws WebException {
		//System.out.println(username);
		try {
			long id = new Handler<Long>(new SignUp(username, displayname, email, pass))
					.execute();
			if (id > 0)
				return id;
			else
				throw new InvalidRequestException("Duplicated username.");
		} catch (Exception e) {
			//e.printStackTrace();
			throw new WebException(e);
		}
	}
}
