package org.net9.minipie.app.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.net9.minipie.app.client.MiniAppService;
import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.exception.BackendConnectionException;
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
					new Session(username, password));
		}
	}

	public void logout() {
		if (isLogged()) {
			getHttpSession().removeAttribute(SESSION_KEY);
			getHttpSession().invalidate();
		}
	}

	public List<PersonBean> listUserContacts() throws LoginRequiredException {
		return getSession().listUserContacts();
	}
	
	public List<PersonBean> listContacts() throws LoginRequiredException {
		return getSession().listContacts();
	}

	public PersonBean viewUserContact(long userId) throws GenericException,
			LoginRequiredException, LoginFailedException {
		return getSession().viewUserContact(userId);
	}

	public PersonBean viewContact(long contactId) throws GenericException,
			LoginRequiredException, LoginFailedException {
		return getSession().viewContact(contactId);
	}

	public PersonBean viewProfile() throws GenericException,
			LoginRequiredException {
		return getSession().getProfile();
	}

	private boolean isLogged() {
		HttpSession session = getHttpSession();
		if (session.isNew())
			return false;
		if (session.getAttribute(SESSION_KEY) == null)
			return false;
		return true;
	}

	private Session getSession() throws LoginRequiredException {
		if (isLogged()) {
			return (Session) getHttpSession().getAttribute(SESSION_KEY);
		}
		throw new LoginRequiredException();
	}

	private HttpSession getHttpSession() {
		return this.getThreadLocalRequest().getSession();
	}

	private static final String SESSION_KEY = "Mini-Pie Session";
}
