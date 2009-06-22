/**
 * AppointAdmin.java
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
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class AppointAdmin extends Command<Void> {
	private long targetId;
	private long groupId;
	private long userId;

	public AppointAdmin(long userId, long groupId, long targetId) {
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		GroupStorage executor = getStorageFactory().getGroupStorage();
		UserStorage executor3 = getStorageFactory().getUserStorage();
		Group_UserStorage executor2 = getStorageFactory()
				.getGroup_UserStorage();
		executor.selectGroup(groupId);
		try {
			executor3.selectBasicInfo(targetId);
		} catch (NotFoundException e) {
			throw new InvalidRequestException("This user do not exist");
		}
		try {
			if (executor2.isAdmin(userId, groupId)) {
				try {
					if (!executor2.isAdmin(targetId, groupId)) {
						executor2.setAdmin(groupId, targetId, true);
						executor2.setAdmin(groupId, userId, false);
					}
				} catch (NotFoundException e) {
					throw new InvalidRequestException(
							"the user have not joined such group yet,invite him/her first");
				}
			} else
				throw new PermissionDeniedException(
						"you are not group administor");
		} catch (NotFoundException e) {
			throw new InvalidRequestException(
					"you have not joined such group yet");
		}

		return null;
	}

}
