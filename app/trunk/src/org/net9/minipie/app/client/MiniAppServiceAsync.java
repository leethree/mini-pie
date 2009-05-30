package org.net9.minipie.app.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MiniAppServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback);
}
