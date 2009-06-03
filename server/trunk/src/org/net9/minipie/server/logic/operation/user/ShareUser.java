/**
 * ShareUser.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;
import org.net9.minipie.server.data.field.InfoField;;

/**
 * @author Seastar
 *
 */
public class ShareUser extends Command<Void> {
	private long userId;
	private long targetId;
	private Permission perm;
	
	public ShareUser(long userId, long targetId,Permission permission) {
		this.userId = userId;
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		if (userId == targetId) {
			throw new InvalidRequestException("Share oneself is not allow in this method.");
		}
		this.perm=permission;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		User_UserStorage executor = getStorageFactory().getUser_UserStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		executor2.selectBasicInfo(targetId);
		try{
			executor.selectRelationship(userId, targetId);
			executor.edit(userId, targetId, InfoField.PERMISSION, perm);
		}catch (NotFoundException e){
			throw new PermissionDeniedException("this is not your user contact");
		}
		return null;
	}

}
