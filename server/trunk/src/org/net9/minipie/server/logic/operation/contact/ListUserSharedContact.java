/**
 * ViewUserSharedContact.java
 *     in package: * org.net9.minipie.server.logic.operation.contact
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;
import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.UserRelation;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;

/**
 * @author Seastar
 *
 */
public class ListUserSharedContact extends Command<Collection<ContactListEntry>>{
	private long userId;
	private long targetId;
	
	public ListUserSharedContact(long userId,long targetId){
		this.userId=userId;
		try {
			this.targetId=Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<ContactListEntry> execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		User_UserStorage executor3=getStorageFactory().getUser_UserStorage();
		//GroupStorage executor5=getStorageFactory().getGroupStorage();		
		Group_UserStorage executor4=getStorageFactory().getGroup_UserStorage();
		Vector<ContactListEntry> result=new Vector<ContactListEntry>();
		
		executor2.selectBasicInfo(targetId);
		try{
			executor3.selectPermission(userId, targetId);
		}catch (NotFoundException e){
			
		}
		return null;
	}

}
