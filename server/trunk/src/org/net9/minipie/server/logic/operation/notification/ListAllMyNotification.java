/**
 * ListAllMyNotification.java
 *     in package: * org.net9.minipie.server.logic.operation.notification
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.notification;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.api.NotificationXml;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
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
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();		
		
		Collection<GroupEntry> ges=executor2.selectGroup(userId);
		Collection<NotificationData> list = executor.selectReceiver(userId);
		
		Collection<NotificationXml> newList = new ArrayList<NotificationXml>();
		
		for (NotificationData notification : list) {
			newList.add(new NotificationXml(notification));
		}
		for(GroupEntry g:ges){
			long id=g.getGroupId();
			if(executor2.isAdmin(userId, id)){
				Collection<NotificationData> l = executor.selectReceiver(id);
				for(NotificationData n:l){
					newList.add(new NotificationXml(n));
				}
			}
		}
		return newList;
	}

}
