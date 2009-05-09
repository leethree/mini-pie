/**
 * ListMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data2.api.ContactEntry;
import org.net9.minipie.server.data2.storage.ContactListEntry;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class ListMyContact extends Command<Collection<ContactEntry>> {
	private Long userId;

	/**
	 * Constructor
	 */
	public ListMyContact(Long userId) {
		setUserId(userId);
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#excute()
	 */
	public Collection<ContactEntry> execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		Collection<ContactEntry> collection = new ArrayList<ContactEntry>();
		for (ContactListEntry entry : executor.selectOwnerContact(userId)) {
			collection.add(new ContactEntry(entry.getEntity()));
		}
		return collection;
	}
}
