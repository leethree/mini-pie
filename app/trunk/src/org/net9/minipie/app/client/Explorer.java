/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client;

import org.net9.minipie.app.client.model.Entry;
import org.net9.minipie.app.client.mvc.AppController;
import org.net9.minipie.app.client.mvc.AppView;
import org.net9.minipie.app.client.mvc.ContentController;
import org.net9.minipie.app.client.mvc.NavigationController;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Window;

//import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class Explorer {

	public static final String MODEL = "model";

	private Dispatcher dispatcher;
	private ExplorerModel model;
	private String hash;

	public Explorer() {
		try {
			GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				public void onUncaughtException(Throwable e) {
					e.printStackTrace();
				}
			});

			model = new ExplorerModel();
			Registry.register(MODEL, model);

			dispatcher = Dispatcher.get();
			dispatcher.addController(new AppController());
			dispatcher.addController(new NavigationController());
			dispatcher.addController(new ContentController());
			dispatcher.dispatch(AppEvents.Init);

			hash = Window.Location.getHash();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onLogin() {

		model.addChangeListener(new ChangeListener() {

			@Override
			public void modelChanged(ChangeEvent event) {
				if (event.getType() == ExplorerModel.READY) {
					dispatcher.dispatch(AppEvents.Login);
					showPage(model.findEntry("overview"));

					Viewport v = Registry.get(AppView.VIEWPORT);
					v.layout(true);

					if (!hash.isEmpty()) {
						hash = hash.substring(1);
						Entry entry = model.findEntry(hash);
						if (entry != null) {
							showPage(entry);
						}
					}
				}
			}

		});
		model.onLogin();

	}

	public static void showPage(Entry entry) {
		AppEvent appEvent = new AppEvent(AppEvents.ShowPage, entry);
		appEvent.setHistoryEvent(true);
		appEvent.setToken(entry.getKey());
		Dispatcher.forwardEvent(appEvent);
	}

}
