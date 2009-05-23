/**
 * ListTaggedUser.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.PhonebookContactListEntry;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;

/**
 * @author Seastar
 *
 */
public class ListTaggedContact extends Command<Collection<PhonebookContactListEntry>> {
	private long userId;
	private long tagId;
	
	public ListTaggedContact(long userId,long tagId){
		this.userId=userId;
		try {
			this.tagId=Formatter.checkId(tagId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<PhonebookContactListEntry> execute() {
		TagStorage executor=getStorageFactory().getTagStorage();
		if(executor.selectTag(tagId).getOwnerId()!=userId)
			throw new PermissionDeniedException("this is not your tag");
		Tag_ContactStorage executor2=getStorageFactory().getTag_ContactStorage();
		Collection<BasicContact> contacts=executor2.selectTaggedContact(tagId);
		Collection<PhonebookContactListEntry> result=new Vector<PhonebookContactListEntry>();
		for(BasicContact entry:contacts){
			ContactEntity entity=entry.getEntity();
			entity.setTags(executor2.selectTagsOfContact(entity.getId()));
			result.add(new PhonebookContactListEntry(entity));
		}
		return result;
	}

}
