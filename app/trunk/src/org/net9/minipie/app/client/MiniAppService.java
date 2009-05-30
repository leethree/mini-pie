package org.net9.minipie.app.client;

import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("services")
public interface MiniAppService extends RemoteService {
	String greetServer(String name);
	boolean isLoggedIn();
	void login(String username, String password) throws LoginFailedException, GenericException;
	void logout();
	
}
