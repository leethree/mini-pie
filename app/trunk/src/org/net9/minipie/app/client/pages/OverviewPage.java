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

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.DataViewEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.DataView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;

public class OverviewPage extends LayoutContainer {

  private DataView dataView;

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);

    setScrollMode(Scroll.AUTO);

    ExplorerModel model = (ExplorerModel) Registry.get(Explorer.MODEL);
    ListStore<ModelData> store = new ListStore<ModelData>();
    store.add(model.getEntries());

    StringBuffer sb = new StringBuffer();
    sb.append("<div class='sample-box'>");
    sb.append("<div class='thumbd'><img src='{path}' width=150 height=150></div>");
    sb.append("<div>{name}</div>");
    sb.append("</div>");

    dataView = new DataView();
    dataView.setItemSelector(".sample-box");
    dataView.setOverStyle("sample-over");
    dataView.setSelectStyle("none");
    dataView.setBorders(false);
    dataView.setStore(store);
    dataView.setTemplate(sb.toString());
    dataView.addListener(Events.SelectionChange, new Listener<DataViewEvent>() {
      public void handleEvent(DataViewEvent be) {
        if (dataView.getSelectedItem() != null) {
          ModelData record = dataView.getSelectedItem().getModel();
          Entry entry = (Entry) record;
          Explorer.showPage(entry);
          dataView.getSelectionModel().deselectAll();
        }
      }
    });

    add(dataView);
  }
}
