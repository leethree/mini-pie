/**
 * ListGroupContact.java
 *     in package: * org.net9.minipie.server.logic.operation.group
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.entity.GroupEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
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
public class ListGroupContact extends Command<Collection<ContactListEntry>> {

	private long groupId;
	private long userId;
	
	public ListGroupContact(long userId,long groupId){
		this.userId=userId;		
		try {
			this.groupId=Formatter.checkId(groupId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<ContactListEntry> execute() {
		GroupStorage executor=getStorageFactory().getGroupStorage();
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();
		ContactStorage executor3=getStorageFactory().getContactStorage();
		Vector<ContactListEntry> result=new Vector<ContactListEntry>();
		GroupEntity g=executor.selectGroup(groupId);
		if(g.getPerm()==Permission.TO_EVERYONE){
			getResult(result,executor3);
		}else{
			try{
				executor2.isAdmin(userId, groupId);
				getResult(result,executor3);
			}catch(NotFoundException e){
				throw new PermissionDeniedException("the group in't public,join first");
			}
		}
		return result;
	}
	
	private void getResult(Vector<ContactListEntry> r,ContactStorage e){
		Collection<CommonListEntry> temp=e.selectGroupContact(groupId);		
		for(CommonListEntry ce:temp){			
			r.add(new ContactListEntry(ce.getEntity()));			
		}
		
	}
}
