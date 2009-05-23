/**
 * UpdateContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.UpdateHandler;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class UpdateMyContact extends Command<Void> {
	private Update datas;
	private Long contactId;
	private Long userId;
	//private ErrorReport errorReport;
	//private Collection<String> err;
	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public UpdateMyContact(Long userId, Long contactId, Update data) {
		super();
		setData(data);
		setContactId(contactId);
		setUserId(userId);
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setData(Update data) {
		if (data == null) {
			throw new InvalidRequestException("No changed data");
		}
		this.datas = data;
	}

	/**
	 * @param contactid
	 *            the contactId to set
	 */
	public void setContactId(Long contactId) {
		try {
			this.contactId =Formatter.checkId(contactId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
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
		ContactStorage executor = getStorageFactory().getContactStorage();
		ContactEntity contact = executor.selectBasicInfo(contactId).getEntity();
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		} else {
			try {
				new UpdateHandler(datas,executor,contactId).handleUpdate();
			} catch (UpdateException e) {
				throw new InvalidRequestException(e.getMessage());
			} catch (DataFormatException e) {
				throw new InvalidRequestException(e);
			}							
		}
		return null;
	}
	
}
