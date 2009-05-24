/**
 * ViewMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import org.net9.minipie.server.data.api.PhonebookCompleteContact;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;

/**
 * @author Seastar
 * 
 */
public class ViewMyContact extends Command<PhonebookCompleteContact> {
	private Long contactId;
	private Long userId;

	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public ViewMyContact(Long userId, Long contactId) {
		super();
		setContactId(contactId);
		setUserId(userId);
	}

	/**
	 * @param contactid
	 *            the contactId to set
	 */
	public void setContactId(Long contactId) {
		if (contactId < 0) {
			throw new InvalidRequestException("id is illegal");
		}
		this.contactId = contactId;
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
	public PhonebookCompleteContact execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		ContactEntity contact = executor.selectBasicInfo(contactId).getEntity();
		Tag_ContactStorage executor2 = getStorageFactory().getTag_ContactStorage();
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		} else {
			contact.setEmails(executor.selectEmail(contactId));
			contact.setAddrs(executor.selectAddr(contactId));
			contact.setIms(executor.selectIM(contactId));
			contact.setTels(executor.selectTel(contactId));
			contact.setUrls(executor.selectURL(contactId));
			contact.setTags(executor2.selectTagsOfContact(contactId));
			return new PhonebookCompleteContact(contact);
		}
	}

}
