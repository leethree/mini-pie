/**
 * ListSharedContact.java
 *     in package: * org.net9.minipie.server.logic.operation.contact
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.entity.GroupEntity;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.UserRelation;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;
/**
 * @author Seastar
 *
 */
public class ListAllSharedContact extends Command<Collection<ContactListEntry>> {
	private long userId;
	
	public ListAllSharedContact(long userId){
		this.userId=userId;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<ContactListEntry> execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		//UserStorage executor2 = getStorageFactory().getUserStorage();
		User_UserStorage executor3=getStorageFactory().getUser_UserStorage();
		//GroupStorage executor5=getStorageFactory().getGroupStorage();		
		Group_UserStorage executor4=getStorageFactory().getGroup_UserStorage();
		
		Collection<GroupEntity> grs=null;
		Collection<UserRelation> urs=null;
		try{
			grs=executor4.selectGroup(userId);
			urs=executor3.selectMyUserContact(userId);
		}catch (NotFoundException e){
			
		}
		Vector<ContactListEntry> result=new Vector<ContactListEntry>();
		if(urs!=null){
			for(UserRelation ur:urs){				
				Collection<CommonListEntry> ces=executor.selectOwnerContact(ur.getId());
				for(CommonListEntry ce:ces){
					if(executor.selectBasicInfo(
						ce.getEntity().getId()).getEntity()
						.getPermission()!=Permission.TO_SELF)
						result.add(new ContactListEntry(ce.getEntity()));
				}
			}
		}
		if(grs!=null){
			for(GroupEntity ge:grs){
				Collection<CommonListEntry> ces=executor.selectGroupContact(ge.getGroupId());
				for(CommonListEntry ce:ces){
					if(executor.selectBasicInfo(
						ce.getEntity().getId()).getEntity()
						.getPermission()!=Permission.TO_SELF)
						result.add(new ContactListEntry(ce.getEntity()));
				}
			}
		}
		return result;
	}

}
