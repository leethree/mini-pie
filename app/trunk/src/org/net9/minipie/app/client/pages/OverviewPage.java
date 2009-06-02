/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.pages;

import org.net9.minipie.app.client.Explorer;
import org.net9.minipie.app.client.ExplorerModel;
import org.net9.minipie.app.client.model.Entry;
import org.net9.minipie.app.client.model.ProfileEntry;
import org.net9.minipie.app.client.widgets.PersonHeader;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.DataViewEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;

public class OverviewPage extends LayoutContainer {

	private DataView dataView;

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);

		ExplorerModel model = (ExplorerModel) Registry.get(Explorer.MODEL);
		ListStore<ModelData> store = new ListStore<ModelData>();
		store.add(model.getPersonEntries());
		store.setSortField("name");

		StringBuffer sb = new StringBuffer();
		sb.append("<div class='sample-box'>");
		sb
				.append("<div class='thumbd'><img src='{image}' width=96 height=96 /></div>");
		sb.append("<div>{name}</div>");
		sb.append("</div>");

		dataView = new DataView();
		dataView.setItemSelector(".sample-box");
		dataView.setOverStyle("sample-over");
		dataView.setSelectStyle("none");
		dataView.setBorders(false);
		dataView.setStore(store);
		dataView.setTemplate(sb.toString());
		dataView.addListener(Events.SelectionChange,
				new Listener<DataViewEvent>() {
					public void handleEvent(DataViewEvent be) {
						if (dataView.getSelectedItem() != null) {
							ModelData record = dataView.getSelectedItem()
									.getModel();
							Entry entry = (Entry) record;
							Explorer.showPage(entry);
							dataView.getSelectionModel().deselectAll();
						}
					}
				});

		ProfileEntry profile = model.getProfile();
		ContentPanel panel = new ContentPanel();
		panel.setHeading("Welcome " + profile.getName() + " !");
		dataView.setScrollMode(Scroll.AUTOY);
		panel.setBodyBorder(false);
		panel.setLayout(new RowLayout());
		panel.add(PersonHeader.createHeader(profile), new RowData(1, 100));
		panel.add(dataView);
		this.add(panel);
	}
}
