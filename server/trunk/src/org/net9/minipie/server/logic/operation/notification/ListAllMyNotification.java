/**
 * ListAllMyNotification.java
 *     in package: * org.net9.minipie.server.logic.operation.notification
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.notification;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.api.NotificationXml;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.NotificationStorage;

/**
 * @author Seastar
 *
 */
public class ListAllMyNotification extends Command<Collection<NotificationXml>> {
	private long userId;
	
	public ListAllMyNotification(long userId){
		this.userId=userId;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<NotificationXml> execute() {		
		NotificationStorage executor=getStorageFactory().getNotifacationStorage();
		Collection<NotificationData> list = executor.selectReceiver(userId);
		Collection<NotificationXml> newList = new ArrayList<NotificationXml>();
		for (NotificationData notification : list) {
			newList.add(new NotificationXml(notification));
		}
		return newList;
	}

}
