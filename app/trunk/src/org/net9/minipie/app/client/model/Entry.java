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

public class Entry extends BaseTreeModel {

  private String name;
  private boolean fill;
  private boolean closable = true;
  private HideMode hideMode = HideMode.DISPLAY;

  public Entry(String name, LayoutContainer example) {
    this.name = name;
    set("name", name);
    set("example", example);
    set("path", "samples/images/thumbs/" + getId() + ".gif");
  }

  public Entry(String name, LayoutContainer example, boolean fill) {
    this(name, example);
    this.fill = fill;
  }

  public Entry(String name, LayoutContainer example, boolean fill, boolean closable) {
    this(name, example, fill);
    this.closable = closable;
  }

  public Entry(String name, LayoutContainer example, boolean fill, boolean closable,
      HideMode hideMode) {
    this(name, example, fill, closable);
    this.setHideMode(hideMode);
  }

  protected Entry() {

  }

  public LayoutContainer getExample() {
    return (LayoutContainer) get("example");
  }

  public String getId() {
    if (name.equals("% Columns")) {
      return "percentcolumns";
    }
    return name.replaceAll(" ", "").toLowerCase();
  }

  public HideMode getHideMode() {
    return hideMode;
  }

  public String getName() {
    return (String) get("name");
  }

  public String getSourceUrl() {
    Object o = (Object) get("example");
    String name = o.getClass().getName();

    name = name.substring(name.lastIndexOf("examples.") + 9);
    name = "code/" + name + ".html";
    name = name.replaceFirst("\\.", "/");

//    if (!Examples.isExplorer()) {
//      name = "../../" + name;
//    }

    return name;
  }

  public boolean isClosable() {
    return closable;
  }

  public boolean isFill() {
    return fill;
  }

  public void setFill(boolean fill) {
    this.fill = fill;
  }

  public void setHideMode(HideMode hideMode) {
    this.hideMode = hideMode;
  }

  public String toString() {
    return getName();
  }

}
