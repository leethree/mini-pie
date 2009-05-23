/**
 * ConfirmRelationship.java
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
public class ConfirmRelationship extends Command<Void> {
	private long userId;
	private long notificationId;
	private boolean confirm;
	
	/**
	 * Constructor
	 * @param userId
	 * @param notificationId
	 * @param confirm
	 */
	public ConfirmRelationship(long userId, long notificationId, boolean confirm) {
		super();
		this.userId = userId;
		try {
			this.notificationId = Formatter.checkId(notificationId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		this.confirm = confirm;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		User_UserStorage executor = getStorageFactory().getUser_UserStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		NotificationStorage executor1 = getStorageFactory().getNotifacationStorage();
		NotificationData noti=executor1.selectNotification(notificationId);
		if(this.confirm){			
			if(noti.getReceiverId()!=userId ||noti.getType()!=NotificationType.RELATIONSHIP)
				throw new PermissionDeniedException("not your notification or not suitable type");
			long id=noti.getSenderId();
			executor2.selectBasicInfo(id);
			executor.selectRelationship(userId, id);
			executor.edit(userId, id, InfoField.RELATIONSHIP, noti.getContent());
			executor1.del(notificationId);
		}else{
			if(noti.getReceiverId()!=userId ||noti.getType()!=NotificationType.RELATIONSHIP)
				throw new PermissionDeniedException("not your notification or not suitable type");
			else
				executor1.del(notificationId);
		}
		return null;
	}

}
