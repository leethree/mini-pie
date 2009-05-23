/**
 * ListMyUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.PhonebookContactListEntry;
import org.net9.minipie.server.data.api.PhonebookUserListEntry;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.UserRelation;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ListMyUserContact extends Command<Collection<PhonebookUserListEntry>>{
	private long userId;
	
	public ListMyUserContact(long userId){
		this.userId=userId;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<PhonebookUserListEntry> execute() {
		UserStorage executor=getStorageFactory().getUserStorage();
		User_UserStorage executor2=getStorageFactory().getUser_UserStorage();
		Collection<UserRelation> list=executor2.selectMyUserContact(userId);
		Collection<PhonebookUserListEntry> result=new Vector<PhonebookUserListEntry>();
		for(UserRelation uu:list){
			PhonebookUserListEntry temp;
			UserEntity user=executor.selectBasicInfo(uu.getId()).getEntity();
			
		}
		return result;
	}

}
