/**
 * JionGroup.java
 *     in package: * org.net9.minipie.server.logic.operation.group
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.NotificationStorage;

/**
 * @author Seastar
 *
 */
public class JionGroup extends Command<Void> {
	private String message;
	private long groupId;
	private long userId;
	
	public JionGroup(long userId,long groupId,String msg){
		this.userId=userId;		
		this.message=msg;
		try {
			this.groupId=Formatter.checkId(groupId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		GroupStorage executor=getStorageFactory().getGroupStorage();
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();
		GroupEntry g=executor.selectGroup(groupId);
		try{
			executor2.isAdmin(userId, groupId);
			throw new InvalidRequestException("you have already joined such group");
		}catch(NotFoundException e){
			//that's right
		}
		if(g.getPerm()==Permission.TO_EVERYONE){
			executor2.add(groupId, userId);
		}else{
			NotificationStorage executor3=getStorageFactory().getNotifacationStorage();
			try {
				executor3.add(new NotificationData(1L,userId,groupId,"user:"+userId+"want to join group," +
						"\r\nhis/her message:"+message,NotificationType.MEMBERSHIP_APPLICATION));
			} catch (DataFormatException e) {
				//won't appear
			}
		}
		return null;
	}

}
