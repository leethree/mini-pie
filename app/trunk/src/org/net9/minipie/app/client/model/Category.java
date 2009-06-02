/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client.model;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;

@SuppressWarnings("serial")
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

	public void addEntry(PersonEntry person) {
		add(person);
	}

	public void addEntrys(List<? extends PersonEntry> people) {
		for (PersonEntry personEntry : people) {
			add(personEntry);
		}
	}

	// public void setChildren(List<ModelData> persons) {
	// setChildren(persons);
	// }
	//
	// public void addEntry(String title, LayoutContainer page, boolean fill) {
	// add(new Entry(title, page, fill));
	// }
	//  
	// public void addEntry(String title, LayoutContainer page, boolean fill,
	// boolean closable, HideMode hideMode) {
	// add(new Entry(title, page, fill, closable, hideMode));
	// }

}
