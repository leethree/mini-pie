package org.net9.minipie.app.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MiniAppServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback);

	void isLoggedIn(AsyncCallback<Boolean> callback);

	void login(String username, String password, AsyncCallback<Void> callback);

	void logout(AsyncCallback<Void> callback);
}
