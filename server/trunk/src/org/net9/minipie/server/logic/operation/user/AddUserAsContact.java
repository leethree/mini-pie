/**
 * AddUserAsContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.NotificationStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 * 
 */
public class AddUserAsContact extends Command<Void> {
	private long userId;
	private long targetId;
	// private Relationships relationShip;
	private String message;

	public AddUserAsContact(long userId, long targetId, String message) {
		setUserId(userId);
		setTargetId(targetId);
		setMessage(message);
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param targetId
	 *            the targetId to set
	 */
	public void setTargetId(long targetId) {
		try {
			this.targetId = Formatter.checkId(targetId);
			if (this.targetId == this.userId)
				throw new InvalidRequestException(
						"can't add youself as contact");
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		try {
			this.userId = Formatter.checkId(userId);
			if (this.targetId == this.userId)
				throw new InvalidRequestException(
						"can't add youself as contact");
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		boolean flag = false;
		UserStorage executor = getStorageFactory().getUserStorage();
		UserEntity entity;
		try {
			entity = executor.selectBasicInfo(targetId).getEntity();
		} catch (NotFoundException e) {
			throw new InvalidRequestException("the user you want to add "
					+ "doesn't exist");
		}
		User_UserStorage executor2 = getStorageFactory().getUser_UserStorage();
		try {
			executor2.selectRelationship(userId, targetId);
			// flag=false;
			throw new InvalidRequestException("the user you want to add "
					+ "is already your contact");
		} catch (NotFoundException e) {
			flag = true;
		}
		if (flag) {
			switch (entity.getAddAsContactPermission()) {
			case NO_ONE:
				throw new InvalidRequestException("the user can't be add "
						+ "as contact");
			case CONFIRMED_ONES:
				NotificationStorage executor3 = getStorageFactory()
						.getNotifacationStorage();
				try {
					executor3.add(new NotificationData(null, userId, targetId,
							"user " + userId
									+ " want you to be his/her contact,"
									+ "please confirm\r\n" + "his/her message:"
									+ message,
							NotificationType.CONTACT_APPLICATION));
				} catch (DataFormatException e) {
					throw new ServerErrorException(e.getMessage());
				}
				break;
			case EVERYONE:
				executor2.add(userId, targetId);
				break;
			}
		}
		return null;
	}

}
