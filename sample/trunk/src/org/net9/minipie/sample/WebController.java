/**
 * 
 */
package org.net9.minipie.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.net9.minipie.sample.exception.BackendConnectionException;
import org.net9.minipie.sample.exception.GenericException;
import org.net9.minipie.sample.exception.LoginFailedException;
import org.net9.minipie.sample.exception.LoginRequiredException;

/**
 * @author LeeThree
 *
 */
public class WebController {
	
	private HttpServletRequest request;
	
	public WebController(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isLoggedIn() throws GenericException,
			BackendConnectionException {
		if (isLogged()) {
			Backend.testConnection();
			return true;
		} else
			return false;
	}

	public void login(String username, String password)
			throws GenericException, LoginFailedException {
		if (!isLogged()) {
			getHttpSession().setAttribute(SESSION_KEY,
					new Backend(username, password));
		}
	}

	public void logout() {
		if (isLogged()) {
			getHttpSession().removeAttribute(SESSION_KEY);
			getHttpSession().invalidate();
		}
	}

	public Backend getSession() throws LoginRequiredException {
		if (isLogged()) {
			return (Backend) getHttpSession().getAttribute(SESSION_KEY);
		}
		throw new LoginRequiredException();
	}
	
	private boolean isLogged() {
		HttpSession session = getHttpSession();
		if (session.isNew())
			return false;
		if (session.getAttribute(SESSION_KEY) == null)
			return false;
		return true;
	}


	private HttpSession getHttpSession() {
		return request.getSession();
	}

	private static final String SESSION_KEY = "Mini-Pie Sample Session";
}
