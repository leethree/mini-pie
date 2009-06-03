/**
 * RemoveTagFromUser.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.storage.TagData;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.TagStorage;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;

/**
 * @author Seastar
 *
 */
public class RemoveTagFromContact extends Command<Void>{

	private long tagId;
	private long userId;
	private long targetId;
	
	/**
	 * Constructor
	 * @param tagId
	 * @param userId
	 * @param targetId
	 */
	public RemoveTagFromContact(long userId,  long targetId, long tagId) {
		this.userId = userId;
		try {
			this.tagId = Formatter.checkId(tagId);
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
		ContactStorage executor=getStorageFactory().getContactStorage();
		TagStorage executor3=getStorageFactory().getTagStorage();
		Tag_ContactStorage executor4=getStorageFactory().getTag_ContactStorage();
		ContactEntity contact=executor.selectBasicInfo(targetId).getEntity();
		if(contact.getOwnerId()!=userId || contact.getShadowOf()!=0)
			throw new PermissionDeniedException("Target is not your contact");
		TagData t=executor3.selectTag(tagId);
		if(t.getOwnerId()==userId){
			//TODO:find out how database will handle if not such relation between contact and tag
			executor4.del(tagId, targetId);
		}
		else
			throw new PermissionDeniedException("this is not your tag");		
		return null;
	}

}
