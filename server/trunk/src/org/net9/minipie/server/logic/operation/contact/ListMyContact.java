/**
 * ListMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.api.PhonebookContactListEntry;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;

/**
 * @author Seastar
 * 
 */
public class ListMyContact extends Command<Collection<PhonebookContactListEntry>> {
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
	public Collection<PhonebookContactListEntry> execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		Collection<PhonebookContactListEntry> collection = new ArrayList<PhonebookContactListEntry>();
		for (CommonListEntry entry : executor.selectOwnerContact(userId)) {
			long contactId=entry.getEntity().getId();
			ContactEntity ce=executor.selectBasicInfo(contactId).getEntity();
			Tag_ContactStorage tagExecutor=getStorageFactory().getTag_ContactStorage();
			ce.setTags(tagExecutor.selectTagsOfContact(contactId));
			collection.add(new PhonebookContactListEntry(ce));
		}
		return collection;
	}
}
