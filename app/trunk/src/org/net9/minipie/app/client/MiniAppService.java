package org.net9.minipie.app.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("services")
public interface MiniAppService extends RemoteService {
	String greetServer(String name);
}
