/**
 * ViewMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;

import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.BasicContact;
import org.net9.minipie.server.data.CompleteContact;
import org.net9.minipie.server.data.EmailData;
import org.net9.minipie.server.data.IMData;
import org.net9.minipie.server.data.PersonalCompleteContact;
import org.net9.minipie.server.data.PhoneNoData;
import org.net9.minipie.server.data.URLData;
import org.net9.minipie.server.db.HibernateDAOFactory;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class ViewMyContact implements Command<PersonalCompleteContact> {
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
	public PersonalCompleteContact execute() {
		CompleteContact compContact;
		ContactStorage executor = new HibernateDAOFactory().getContactStorage();
		BasicContact contact = executor.selectBasicInfo(contactId);
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		} else {
			Collection<EmailData> emails = executor.selectEmail(contactId);
			Collection<AddressData> addrs = executor.selectAddr(contactId);
			Collection<IMData> ims = executor.selectIM(contactId);
			Collection<PhoneNoData> tels = executor.selectTel(contactId);
			Collection<URLData> urls = executor.selectURL(contactId);
			compContact = new CompleteContact(contact, addrs, emails, ims,
					tels, urls);
		}
		return new PersonalCompleteContact(compContact, null);
	}

}
