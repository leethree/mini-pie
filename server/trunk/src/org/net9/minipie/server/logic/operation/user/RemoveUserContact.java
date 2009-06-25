/**
 * RemoveUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.TagEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class RemoveUserContact extends Command<Void> {
	private long userId;
	private long targetId;
	
	public RemoveUserContact(long userId,long targetId){
		this.userId=userId;
		try {
			this.targetId=Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		UserStorage executor=getStorageFactory().getUserStorage();
		User_UserStorage executor2=getStorageFactory().getUser_UserStorage();
		executor.selectBasicInfo(targetId);
		try{
			executor2.selectRelationship(userId, targetId);
			executor2.del(userId, targetId);
			Tag_UserStorage executor3 = getStorageFactory().getTag_UserStorage();
			ContactStorage executor4=getStorageFactory().getContactStorage();
			Collection<TagEntity> ts=executor3.selectTagsOfUser(targetId, userId);
			for(TagEntity t:ts)
				executor3.del(t.getId(), targetId);
			try{
				executor4.del(executor4.selectShadowOf(userId, targetId).getEntity().getId());
			}catch (NotFoundException e){
				//ignore
			}
		}catch (NotFoundException e){
			throw new InvalidRequestException("the target user is not your user contact");
		}
		
		return null;
	}

}
