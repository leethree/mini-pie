/**
 * ConfirmContactApply.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.NotificationStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 * 
 */
public class ConfirmContactApply extends Command<Void> {

	private long userId;
	private long notificationId;
	private boolean confirm;

	public ConfirmContactApply(long userId, long notificationId, boolean confirm) {
		setUserId(userId);
		this.confirm = confirm;
		setNotificationId(notificationId);
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @param notificationId
	 *            the notificationId to set
	 */
	public void setNotificationId(long notificationId) {
		try {
			this.notificationId = Formatter.checkId(notificationId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException("not such notification");
		}
	}

	@Override
	public Void execute() {
		User_UserStorage executor = getStorageFactory().getUser_UserStorage();
		NotificationStorage executor1 = getStorageFactory()
				.getNotifacationStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		
		NotificationData noti = executor1.selectNotification(notificationId);
		if (noti.getType() == NotificationType.CONTACT_APPLICATION) {
			if (confirm) {
				if (noti.getReceiverId() == userId) {
					long id=noti.getSenderId();
					executor2.selectBasicInfo(id);
					executor.add(userId, id);
					executor1.del(notificationId);
				} else
					throw new InvalidRequestException(
							"this is not your notification");
			} else {
				if (noti.getReceiverId() == userId) {
					executor1.del(notificationId);
				} else {
					throw new InvalidRequestException(
							"this is not your notification");
				}
			}
		} else if (noti.getType() != NotificationType.RELATIONSHIP) {
			if (this.confirm) {
				if (noti.getReceiverId() != userId)
					throw new PermissionDeniedException(
							"not your notification ");
				long id = noti.getSenderId();
				executor2.selectBasicInfo(id);
				executor.selectRelationship(userId, id);
				executor.edit(userId, id, InfoField.RELATIONSHIP, noti
						.getContent());
				executor1.del(notificationId);
			} else {
				if (noti.getReceiverId() != userId)
					throw new PermissionDeniedException(
							"not your notification");
				else
					executor1.del(notificationId);
			}
		}
		return null;

	}

}
