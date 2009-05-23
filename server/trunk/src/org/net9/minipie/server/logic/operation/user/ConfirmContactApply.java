/**
 * ConfirmContactApply.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.NotificationStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ConfirmContactApply extends Command<Void> {

	private long userId;
	private long notificationId;
	
	public ConfirmContactApply(long userId,long notificationId){
		
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @param notificationId the notificationId to set
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
		NotificationStorage executor1 = getStorageFactory().getNotifacationStorage();		
		NotificationData noti=executor1.selectNotification(notificationId);
		if(noti.getReceiverId()==userId ||noti.getType()==NotificationType.CONTACT_APPLICATION){
			executor.add(userId, noti.getSenderId());			
		}else
			throw new InvalidRequestException("this is not your notification or not the correct type");
		return null;
	}

}
