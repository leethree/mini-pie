/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.mvc;

import org.net9.minipie.app.client.AppEvents;
import org.net9.minipie.app.client.model.Entry;
import org.net9.minipie.app.client.model.PageEntry;
import org.net9.minipie.app.client.model.PersonEntry;
import org.net9.minipie.app.client.pages.DetailPage;
import org.net9.minipie.app.client.pages.Page;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.History;

public class ContentView extends View {

	private TabPanel tabPanel;

	public ContentView(Controller controller) {
		super(controller);
	}

	public void initialize() {
		tabPanel = new TabPanel();
		tabPanel.setCloseContextMenu(true);
		tabPanel.setBorderStyle(false);
		tabPanel.setBodyBorder(false);
		tabPanel.setTabScroll(true);
		tabPanel.setAnimScroll(true);
		tabPanel.addListener(Events.Remove, new Listener<TabPanelEvent>() {

			public void handleEvent(TabPanelEvent be) {
				TabItem item = be.getItem();
				Entry entry = (Entry) item.getData("entry");
				Dispatcher.forwardEvent(AppEvents.HidePage, entry);
			}

		});
		tabPanel.addListener(Events.Select, new Listener<TabPanelEvent>() {
			public void handleEvent(TabPanelEvent be) {
				String token = History.getToken();
				Entry entry = (Entry) be.getItem().getData("entry");
				if (token != null && (!token.equals(entry.getKey()))) {
					History.newItem(entry.getKey(), false);
				}
				Dispatcher.forwardEvent(AppEvents.TabChange, entry);
			}
		});
		ContentPanel center = (ContentPanel) Registry.get(AppView.CENTER_PANEL);
		center.add(tabPanel);
	}

	private void onLogin() {
		// TODO Auto-generated method stub
	}
	
	public void onShowPage(Entry entry) {
		if (entry instanceof PersonEntry) {
			PersonEntry user = (PersonEntry) entry;
			DetailPage page = user.get("page");
			if (page == null) {
				page = new DetailPage(user);
				user.set("page", page);
			}
			TabItem item = tabPanel.findItem("page__" + page.getId(), false);
			if (item == null) {
				item = new TabItem();
				item.setData("entry", user);
				item.setClosable(true);
				item.setId("page__" + page.getId());
				item.setText(user.getName());
				item.setLayout(new FitLayout());
				item.add(page);
				tabPanel.add(item);
			}
			if (item != tabPanel.getSelectedItem()) {
				tabPanel.setSelection(item);
			}
		}
		if (entry instanceof PageEntry) {
			PageEntry pe = (PageEntry) entry;
			Page page = pe.get("page");
			if (page == null) {
				page = new Page(pe);
				pe.set("page", page);
			}

			TabItem item = tabPanel.findItem("page__" + page.getId(), false);
			if (item == null) {
				item = new TabItem();
				item.setData("entry", pe);
				item.setClosable(pe.isClosable());
				item.setId("page__" + page.getId());
				item.setText(pe.getName());
				item.setLayout(new FitLayout());
				item.add(page);
				tabPanel.add(item);
			}

			if (item != tabPanel.getSelectedItem()) {
				tabPanel.setSelection(item);
			}
		}
	}

	protected void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.Login) {
			onLogin();
		} else if (type == AppEvents.ShowPage) {
			Entry entry = event.getData();
			onShowPage(entry);
		}
	}


}
