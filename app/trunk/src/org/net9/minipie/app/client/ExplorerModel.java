/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client;

import java.util.ArrayList;
import java.util.List;

import org.net9.minipie.app.client.model.Category;
import org.net9.minipie.app.client.model.Entry;
import org.net9.minipie.app.client.pages.OverviewPage;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;

public class ExplorerModel extends BaseTreeModel {
	  protected List<Entry> entries = new ArrayList<Entry>();

  public ExplorerModel() {
    set("overview", new Entry("Overview", new OverviewPage(), true, false));
  }
  public Entry findEntry(String name) {
	    if (get(name) != null) {
	      return (Entry) get(name);
	    }
	    for (Entry entry : getEntries()) {
	      if (name.equals(entry.getId())) {
	        return entry;
	      }
	    }
	    return null;
	  }

	  public List<Entry> getEntries() {
	    return entries;

	  }

	  private void loadEntries(TreeModel model) {
	    for (ModelData child : model.getChildren()) {
	      if (child instanceof Entry) {
	        entries.add((Entry) child);
	      } else if (child instanceof Category) {
	        loadEntries((Category)child);
	      }
	    }
	  }
}
