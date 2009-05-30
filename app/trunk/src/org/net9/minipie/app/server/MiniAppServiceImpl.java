package org.net9.minipie.app.server;

import javax.servlet.http.HttpSession;

import org.net9.minipie.app.client.MiniAppService;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;
import org.net9.minipie.app.client.exception.LoginRequiredException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MiniAppServiceImpl extends RemoteServiceServlet implements
		MiniAppService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	public boolean isLoggedIn() {
		HttpSession session = getHttpSession();
		if (session.isNew())
			return false;
		if (session.getAttribute(SESSION_KEY) == null)
			return false;
		return true;
	}

	public void login(String username, String password)
			throws LoginFailedException, GenericException {
		if (!isLoggedIn()) {
			getHttpSession().setAttribute(SESSION_KEY,
					new Session(username, password));
		}
	}

	public void logout() {
		if (isLoggedIn()) {
			getHttpSession().removeAttribute(SESSION_KEY);
			getHttpSession().invalidate();
		}
	}

	private Session getSession() throws LoginRequiredException {
		if (isLoggedIn()) {
			return (Session) getHttpSession().getAttribute(SESSION_KEY);
		}
		throw new LoginRequiredException();
	}

	private HttpSession getHttpSession() {
		return this.getThreadLocalRequest().getSession();
	}

	private static final String SESSION_KEY = "Mini-Pie Session";
}
