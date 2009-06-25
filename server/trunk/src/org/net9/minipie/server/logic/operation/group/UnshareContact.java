/**
 * UnshareContact.java
 *     in package: * org.net9.minipie.server.logic.operation.group
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Seastar
 * 
 */
public class UnshareContact extends Command<Void> {
	private long groupId;
	private long userId;
	private long targetId;

	public UnshareContact(long userId, long groupId, long targetId) {
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
		Group_UserStorage executor2 = getStorageFactory()
				.getGroup_UserStorage();
		ContactStorage executor3 = getStorageFactory().getContactStorage();
		executor.selectGroup(groupId);
		ContactEntity contact = executor3.selectBasicInfo(targetId).getEntity();
		if (contact.getOwnerId() != userId)
			throw new PermissionDeniedException("not your contact");
		else if (contact.getGroupId() != groupId)
			throw new InvalidRequestException(
					"the contact has not be shared into the group");
		else {
			try {
				executor2.isAdmin(userId, groupId);
				executor3.editBasicInfo(targetId, InfoField.GROUPOWNER, 0);
			} catch (NotFoundException e) {
				throw new InvalidRequestException(
						"you have not joined such group yet");
			}
		}
		return null;
	}

}
