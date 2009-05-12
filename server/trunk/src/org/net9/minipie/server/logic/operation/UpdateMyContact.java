/**
 * UpdateContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.ErrorReport;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.operation.util.UpdateHandler;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class UpdateMyContact extends Command<ErrorReport> {
	private Collection<Update> datas;
	private Long contactId;
	private Long userId;
	private ErrorReport errorReport;
	private Collection<String> err;
	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public UpdateMyContact(Long contactId, Long userId, Collection<Update> data) {
		super();
		setData(data);
		setContactId(contactId);
		setUserId(userId);
		err=new Vector<String>();
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setData(Collection<Update> data) {
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
	public ErrorReport execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		ContactEntity contact = executor.selectBasicInfo(contactId).getEntity();
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		} else {
			for (Update data : datas) {
				try {
					new UpdateHandler(data,executor,contactId).handleUpdate();
				} catch (UpdateException e) {
					err.add(e.getMessage());					
				}
			}
			if(err.size()!=0) errorReport=new ErrorReport(err);
		}
		return errorReport;
	}
	
}
