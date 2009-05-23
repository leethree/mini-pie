/**
 * ListTaggedUser.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.PhonebookUserListEntry;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ListTaggedUser extends Command<Collection<PhonebookUserListEntry>> {

	private long userId;
	private long tagId;
	
	public ListTaggedUser(long userId,long tagId){
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
	public Collection<PhonebookUserListEntry> execute() {
		TagStorage executor=getStorageFactory().getTagStorage();
		if(executor.selectTag(tagId).getOwnerId()!=userId)
			throw new PermissionDeniedException("this is not your tag");
		Tag_UserStorage executor2=getStorageFactory().getTag_UserStorage();
		User_UserStorage executor3=getStorageFactory().getUser_UserStorage();
		Collection<BasicUser> users=executor2.selectTaggedUser(tagId);
		Collection<PhonebookUserListEntry> result=new Vector<PhonebookUserListEntry>();
		for(BasicUser entry:users){
			UserEntity entity=entry.getEntity();
			long id=entity.getId();
			entity.setTags(executor2.selectTagsOfUser(id,userId));
			String rel = executor3.selectRelationship(userId, id);
			if (rel != null)
				entity.setRelationship(new Relationships(rel));
			entity.setPermission(executor3.selectPermission(userId, id));
			result.add(new PhonebookUserListEntry(entity));
		}
		return result;
	}

}
