/**
 * DeleteUserShadow.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteContact;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class DeleteUserShadow extends Command<Void> {
	private long userId;
	private long targetId;
	
	public DeleteUserShadow(long userId,long targetId){
		this.userId=userId;
		try {
			this.targetId = Formatter.checkId(targetId);
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
		ContactStorage executor3 = getStorageFactory().getContactStorage();
		executor.selectBasicInfo(targetId);
		try{
			executor2.selectRelationship(userId, targetId);
			//executor3.selectShadowOf(userId, targetId);
			try{
				executor3.del(executor3.selectShadowOf(userId, targetId).getEntity().getId());
			}catch (NotFoundException e){
				throw new InvalidRequestException("no such user shadow");
			}
		}catch (NotFoundException e){
			throw new InvalidRequestException("not your user contact");
		}
		
		return null;
	}
}
