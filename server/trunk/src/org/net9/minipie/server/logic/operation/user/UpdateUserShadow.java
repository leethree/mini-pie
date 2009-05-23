/**
 * AddInfoToUser.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.UpdateHandler;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class UpdateUserShadow extends Command<Void> {
	private Update datas;
	private Long targetId;
	private Long userId;
	//private ErrorReport errorReport;
	//private Collection<String> err;
	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public UpdateUserShadow(Long userId, Long targetId, Update data) {
		super();
		setData(data);
		setContactId(targetId);
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
	public void setContactId(Long targetId) {		
		try {
			this.targetId =Formatter.checkId(targetId);
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
		User_UserStorage executor1 = getStorageFactory().getUser_UserStorage();
		UserStorage executor3 = getStorageFactory().getUserStorage();
		executor3.selectBasicInfo(targetId);
		executor1.selectRelationship(userId, targetId);
		ContactEntity contact;
		long contactId;
		try{
			contact = executor.selectShadowOf(userId, targetId).getEntity();
			contactId=contact.getId();
		}catch (NotFoundException e){
			contactId=executor.addShadow(userId, targetId);
		}
		
			try {
				new UpdateHandler(datas,executor,contactId).handleUpdate();
			} catch (UpdateException e) {
				throw new InvalidRequestException(e.getMessage());
			} catch (DataFormatException e) {
				throw new InvalidRequestException(e);
			}							
		
		return null;
	}
	
}
