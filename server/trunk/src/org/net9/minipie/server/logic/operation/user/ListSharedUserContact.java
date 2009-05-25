/**
 * ViewSharedUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */

public class ListSharedUserContact extends Command<Collection<UserListEntry>> {
	private long userId;
	private long targetId;

	public ListSharedUserContact(long userId, long targetId) {
		this.userId = userId;
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		if (userId == targetId) {
			throw new InvalidRequestException("View oneself is not allow in this method.");
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	
	@Override
	public Collection<UserListEntry> execute() {
		boolean relationFlag;
		Collection<UserListEntry> result=new ArrayList<UserListEntry>();
		User_UserStorage executor2 = getStorageFactory().getUser_UserStorage();
		try{
			executor2.selectPermission(targetId,userId);
			relationFlag=true;			
		}catch (NotFoundException e){			
			relationFlag=false;
		}
		Collection<CommonListEntry> temp;
		if(relationFlag){			
			temp=executor2.selectSharedUser(targetId, Permission.TO_CONTACTS);
			for(CommonListEntry ce:temp){				
				result.add(new UserListEntry(ce.getEntity()));				
			}
			temp=executor2.selectSharedUser(targetId, Permission.TO_EVERYONE);
			for(CommonListEntry ce:temp){				
				result.add(new UserListEntry(ce.getEntity()));				
			}
		}else{
			temp=executor2.selectSharedUser(targetId, Permission.TO_EVERYONE);
			for(CommonListEntry ce:temp){				
				result.add(new UserListEntry(ce.getEntity()));				
			}
		}
		return result;
	}

}
