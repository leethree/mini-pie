/**
 * EditTag.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;

/**
 * @author Seastar
 *
 */
public class EditTag extends Command<Void> {
	private long userId;
	private long tagId;
	private String newName;
	
	public EditTag(long userId,long tagId,String newName){
		this.userId=userId;
		try {
			this.tagId=Formatter.checkId(tagId);
			this.newName=Formatter.compact(newName);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		TagStorage executor=getStorageFactory().getTagStorage();
		if(executor.selectTag(tagId).getOwnerId()!=userId)
			throw new PermissionDeniedException("this is not your tag");
		executor.editTag(tagId, newName);
		return null;
	}

}
