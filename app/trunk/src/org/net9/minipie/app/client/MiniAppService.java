package org.net9.minipie.app.client;

import java.util.List;

import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.exception.BackendConnectionException;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;
import org.net9.minipie.app.client.exception.LoginRequiredException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("services")
public interface MiniAppService extends RemoteService {
	String greetServer(String name);

	boolean isLoggedIn() throws GenericException, BackendConnectionException;

	void login(String username, String password) throws LoginFailedException,
			GenericException, BackendConnectionException;

	void logout() throws GenericException, BackendConnectionException;

	List<PersonBean> listUserContacts() throws LoginRequiredException;
	
	List<PersonBean> listContacts() throws LoginRequiredException;
	
	PersonBean viewUserContact(long userId) throws GenericException, LoginRequiredException, LoginFailedException;
	
	PersonBean viewContact(long contactId) throws GenericException, LoginRequiredException, LoginFailedException;
	
	PersonBean viewProfile() throws GenericException, LoginRequiredException;
}
