/**
 * MiniPieLoginModule.java
 *     in package: * org.net9.minipie.server.auth
 * by Mini-Pie Project
 */
package org.net9.minipie.server.auth;

import java.util.*;

import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

import org.net9.minipie.server.logic.UserAuth;

/**
 * @author SoulCircus, LeeThree
 * 
 */
public class MiniPieLoginModule implements LoginModule {
	// initial state
	private Subject subject;
	private CallbackHandler callbackHandler;

	// configurable option
	private boolean debug = false;

	// the authentication status
	private boolean succeeded = false;
	private boolean commitSucceeded = false;

	// username and password
	private String username;
	private char[] password;
	private long id;

	// user's principals
	private MiniPieUser userPrincipal;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	public boolean login() throws LoginException {

		// prompt for a user name and password
		if (callbackHandler == null)
			throw new LoginException("Error: no CallbackHandler available "
					+ "to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("user name: ");
		callbacks[1] = new PasswordCallback("password: ", false);

		try {
			callbackHandler.handle(callbacks);
			username = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1])
					.getPassword();
			if (tmpPassword == null) {
				// treat a NULL password as an empty password
				tmpPassword = new char[0];
			}
			password = new char[tmpPassword.length];
			System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
			((PasswordCallback) callbacks[1]).clearPassword();

		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException("Error: " + uce.getCallback().toString()
					+ " not available to garner authentication information "
					+ "from the user");
		}

		// print debugging information
		if (debug) {
			System.out.println("\t\t[SampleLoginModule] "
					+ "user entered user name: " + username);
			System.out.print("\t\t[SampleLoginModule] "
					+ "user entered password: ");
			for (int i = 0; i < password.length; i++)
				System.out.print(password[i]);
			System.out.println();
		}

		String passStr = new String(password);

		// verify the username/password
		UserAuth auth = new UserAuth(username, passStr);
		id = auth.getUserId();

		if (id <= 0) {
			if (debug)
				System.out.println("\t\t[MiniPieLoginModule] "
						+ "authentication failed");
			clear();
			throw new FailedLoginException("Incorrect user name or password.");
		} else {
			succeeded = true;
			return true;
		}
	}

	public boolean commit() throws LoginException {
		if (succeeded == false) {
			return false;
		} else {
			// add a Principal (authenticated identity)
			// to the Subject

			userPrincipal = new MiniPieUser(username, id);
			if (!subject.getPrincipals().contains(userPrincipal)) {
				subject.getPrincipals().add(userPrincipal);

			}
			if (!subject.getPrincipals().contains(MiniPieRole.MINIPIE_USER)) {
				subject.getPrincipals().add(MiniPieRole.MINIPIE_USER);
			}
			if (debug) {
				System.out.println("\t\t[MiniPieLoginModule] "
						+ "added MiniPieUser to Subject");
			}

			// in any case, clean out state
			clear();
			succeeded = true;
			commitSucceeded = true;
			return true;
		}
	}

	public boolean abort() throws LoginException {
		if (succeeded == false) {
			return false;
		} else if (succeeded == true && commitSucceeded == false) {
			// login succeeded but overall authentication failed
			clear();
		} else {
			// overall authentication succeeded and commit succeeded,
			// but someone else's commit failed
			logout();
		}
		return true;
	}

	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		subject.getPrincipals().remove(MiniPieRole.MINIPIE_USER);
		clear();
		succeeded = commitSucceeded;
		return true;
	}

	private void clear() {
		succeeded = false;
		username = null;
		if (password != null) {
			for (int i = 0; i < password.length; i++)
				password[i] = ' ';
			password = null;
		}
		userPrincipal = null;
	}
}
