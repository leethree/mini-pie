/**
 * RemoveUser.java
 *     in package: * org.net9.minipie.server.logic.operation.group.admin
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group.admin;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Seastar
 * 
 */
public class RemoveUser extends Command<Void> {
	private long groupId;
	private long userId;
	private long targetId;

	public RemoveUser(long userId, long groupId, long targetId) {
		this.userId = userId;
		try {
			this.groupId = Formatter.checkId(groupId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		if(userId==targetId){
			throw new InvalidRequestException("Remove oneself is not allowed");
		}
	} /*
		 * (non-Javadoc) *
		 * 
		 * @see org.net9.minipie.server.logic.operation.Command#execute()
		 */

	@Override
	public Void execute() {
		GroupStorage executor = getStorageFactory().getGroupStorage();
		Group_UserStorage executor2 = getStorageFactory()
				.getGroup_UserStorage();
		executor.selectGroup(groupId);
		try {
			if (executor2.isAdmin(userId, groupId))
				try {
					executor2.isAdmin(targetId, groupId);
					executor2.remove(groupId, targetId);
				} catch (NotFoundException e) {
					throw new InvalidRequestException(
							"The user has not joined such group yet");
				}
			else
				throw new PermissionDeniedException(
						"you are not group administor");
		} catch (NotFoundException e) {
			throw new InvalidRequestException(
					"you have not joined such group yet");
		}
		return null;
	}
}
