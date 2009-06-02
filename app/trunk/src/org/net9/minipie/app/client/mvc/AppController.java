/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.mvc;


import org.net9.minipie.app.client.AppEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class AppController extends Controller {

  private AppView appView;

  public AppController() {
    appView = new AppView(this);

    registerEventTypes(AppEvents.Init);
    registerEventTypes(AppEvents.Login);
  }

  public void handleEvent(AppEvent event) {
    if (event.getType() == AppEvents.Init || event.getType() == AppEvents.Login) {
      forwardToView(appView, event);
    }
  }

}
