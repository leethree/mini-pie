/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.model;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public class Category extends BaseTreeModel {

  protected Category() {
  }

  public Category(String name) {
    set("name", name);
  }

  public String getName() {
    return (String) get("name");
  }

  public String toString() {
    return getName();
  }

  public void addEntry(String title, LayoutContainer page) {
    add(new Entry(title, page));
  }

  public void addEntry(String title, LayoutContainer page, boolean fill) {
    add(new Entry(title, page, fill));
  }
  
  public void addEntry(String title, LayoutContainer page, boolean fill, boolean closable, HideMode hideMode) {
    add(new Entry(title, page, fill, closable, hideMode));
  }

}
