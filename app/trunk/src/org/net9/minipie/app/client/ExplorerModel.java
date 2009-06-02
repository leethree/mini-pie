/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package org.net9.minipie.app.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.model.Category;
import org.net9.minipie.app.client.model.ContactEntry;
import org.net9.minipie.app.client.model.Entry;
import org.net9.minipie.app.client.model.PageEntry;
import org.net9.minipie.app.client.model.PersonEntry;
import org.net9.minipie.app.client.model.ProfileEntry;
import org.net9.minipie.app.client.model.UserEntry;
import org.net9.minipie.app.client.pages.OverviewPage;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

@SuppressWarnings("serial")
public class ExplorerModel extends BaseModel {
	public static final int READY = 1564; // this is a magic number

	// protected List<Entry> entries = new ArrayList<Entry>();
	private Map<String, Entry> entryMap = new HashMap<String, Entry>();
	private List<UserEntry> userContactEntries = new ArrayList<UserEntry>();
	private List<ContactEntry> contactEntries = new ArrayList<ContactEntry>();
	private ProfileEntry profile;

	public ExplorerModel() {
	}

	public Entry findEntry(String name) {
		Entry entry = entryMap.get(name);
		return entry;
	}

	public List<PersonEntry> getPersonEntries() {
		// TODO change navigator
		List<PersonEntry> entries = new ArrayList<PersonEntry>();
		entries.addAll(contactEntries);
		entries.addAll(userContactEntries);
		return entries;
	}

	public TreeModel getTreeModel() {
		Category users = new Category("Users");
		users.addEntrys(userContactEntries);
		Category contacts = new Category("Contacts");
		contacts.addEntrys(contactEntries);

		TreeModel tree = new BaseTreeModel();
		tree.add(users);
		tree.add(contacts);

		// for (PersonEntry entry : userContactEntries) {
		// tree.add(entry);
		// }
		return tree;
	}

	public ProfileEntry getProfile() {
		return profile;
	}

	public void onLogin() {
		final MiniAppServiceAsync service = Registry.get(MiniApp.SERVICE);
		entryMap.put("overview", new PageEntry("Overview", new OverviewPage(),
				true, false));
		service.viewProfile(new AsyncCallback<PersonBean>() {

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Unexpected error", caught.getMessage());
			}

			@Override
			public void onSuccess(PersonBean result) {
				ExplorerModel.this.profile = new ProfileEntry(result);

				service.listUserContacts(new AsyncCallback<List<PersonBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Unexpected error", caught.getMessage());
					}

					@Override
					public void onSuccess(List<PersonBean> result) {
						for (PersonBean personBean : result) {
							UserEntry entry = new UserEntry(personBean);
							userContactEntries.add(entry);
							entryMap.put(entry.getKey(), entry);
						}
						service
								.listContacts(new AsyncCallback<List<PersonBean>>() {

									@Override
									public void onFailure(Throwable caught) {
										Info.display("Unexpected error", caught
												.getMessage());
									}

									@Override
									public void onSuccess(
											List<PersonBean> result) {
										for (PersonBean personBean : result) {
											ContactEntry entry = new ContactEntry(
													personBean);
											contactEntries.add(entry);
											entryMap.put(entry.getKey(), entry);
										}
										fireEvent(READY);
									}

								});
					}

				});
			}

		});

	}

}
