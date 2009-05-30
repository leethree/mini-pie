package org.net9.minipie.app.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class MiniApp implements EntryPoint {

	public static final String SERVICE = "miniService";

	private final MiniAppServiceAsync service = GWT
			.create(MiniAppService.class);

	public void onModuleLoad() {
		Registry.register(SERVICE, service);
		final LoginForm loginForm = new LoginForm();
		new Explorer().onModuleLoad();
		loginForm.addListener(LoginForm.LOGGED_IN, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (be.getType() == LoginForm.LOGGED_IN) {
					loginForm.close();
				}
			}
		});
		loginForm.show();
	}
}
