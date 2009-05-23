/**
 * UpdateRelationship.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.NotificationStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class UpdateRelationship extends Command<Void> {
	private long userId;
	private long targetId;
	private Relationships rel;
	
	public UpdateRelationship(long userId,long targetId,String rel){
		this.userId=userId;
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {			
			throw new InvalidRequestException(e);
		}
		this.rel=new Relationships(rel);
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		User_UserStorage executor = getStorageFactory().getUser_UserStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		NotificationStorage executor1 = getStorageFactory().getNotifacationStorage();
		executor2.selectBasicInfo(targetId);
		try{
			executor.selectRelationship(userId, targetId);
		}catch(NotFoundException e){
			throw new PermissionDeniedException("the target is not your user contact");
		}
		try {
			executor1.add(new NotificationData(0L,userId,targetId,rel.toString(),NotificationType.RELATIONSHIP));
		} catch (DataFormatException e) {
			
		}
		return null;
	}

}
