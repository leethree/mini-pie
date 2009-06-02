/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.mvc;

import org.net9.minipie.app.client.AppEvents;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class NavigationController extends Controller {

	private NavigationView view;

	public NavigationController() {
		registerEventTypes(AppEvents.Init, AppEvents.Login, AppEvents.HidePage,
				AppEvents.TabChange);
	}

	public void initialize() {
		view = new NavigationView(this);
	}

	public void handleEvent(AppEvent event) {
		EventType t = event.getType();
		if (t == AppEvents.Init || t == AppEvents.Login
				|| t == AppEvents.HidePage || t == AppEvents.TabChange) {
			forwardToView(view, event);
		}
	}

}
