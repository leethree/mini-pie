/**
 * AttachTagToUser.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.storage.TagData;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 * 
 */
public class AttachTagToUser extends Command<Void> {
	private long tagId;
	private long userId;
	private long targetId;

	/**
	 * Constructor
	 * 
	 * @param tagId
	 * @param userId
	 * @param targetId
	 */
	public AttachTagToUser(long userId, long targetId, long tagId) {
		this.userId = userId;
		try {
			this.tagId = Formatter.checkId(tagId);
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
		UserStorage executor = getStorageFactory().getUserStorage();
		User_UserStorage executor2 = getStorageFactory().getUser_UserStorage();
		TagStorage executor3 = getStorageFactory().getTagStorage();
		Tag_UserStorage executor4 = getStorageFactory().getTag_UserStorage();
		executor.selectBasicInfo(targetId);
		TagData t = executor3.selectTag(tagId);
		if (t.getOwnerId() == userId) {
			try {
				executor2.selectRelationship(userId, targetId);
			} catch (NotFoundException e) {
				throw new PermissionDeniedException(
						"Target is not your user contact");
			}
			executor4.add(tagId, targetId);
		} else {
			throw new PermissionDeniedException("this is not your tag");
		}
		return null;
	}

}
