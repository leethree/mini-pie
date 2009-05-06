/**
 * DeleteContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import org.net9.minipie.server.data.BasicContact;
import org.net9.minipie.server.db.HibernateDAOFactory;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class DeleteMyContact implements Command<Void> {
	private Long contactId;
	private Long userId;

	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public DeleteMyContact(Long userId, Long contactId) {
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
	public Void execute() {
		ContactStorage executor = new HibernateDAOFactory().getContactStorage();
		BasicContact contact = executor.selectBasicInfo(contactId);
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		} else {
			executor.del(contactId);
		}
		return null;
	}

}
