package org.net9.minipie.app.client;

import java.util.List;

import org.net9.minipie.app.client.data.PersonBean;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MiniAppServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback);

	void isLoggedIn(AsyncCallback<Boolean> callback);

	void login(String username, String password, AsyncCallback<Void> callback);

	void logout(AsyncCallback<Void> callback);

	void listUserContacts(AsyncCallback<List<PersonBean>> asyncCallback);
	
	void listContacts(AsyncCallback<List<PersonBean>> asyncCallback);
	
	void viewUserContact(long userId, AsyncCallback<PersonBean> asyncCallback);
	
	void viewContact(long contactId, AsyncCallback<PersonBean> asyncCallback);
	
	void viewProfile(AsyncCallback<PersonBean> asyncCallback);
}
