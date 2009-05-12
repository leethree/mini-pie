/**
 * ListMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.api.CommonEntry;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class ListMyContact extends Command<Collection<CommonEntry>> {
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
	public Collection<CommonEntry> execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		Collection<CommonEntry> collection = new ArrayList<CommonEntry>();
		for (CommonListEntry entry : executor.selectOwnerContact(userId)) {
			collection.add(new CommonEntry(entry.getEntity()));
		}
		return collection;
	}
}
