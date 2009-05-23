/**
 * ListAllMyNotification.java
 *     in package: * org.net9.minipie.server.logic.operation.notification
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.notification;

import java.util.Collection;

import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.NotificationStorage;

/**
 * @author Seastar
 *
 */
public class ListAllMyNotification extends Command<Collection<NotificationData>> {
	private long userId;
	
	public ListAllMyNotification(long userId){
		this.userId=userId;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<NotificationData> execute() {		
		NotificationStorage executor=getStorageFactory().getNotifacationStorage();		
		return executor.selectReceiver(userId);
	}

}
