/**
 * ListGroupMember.java
 *     in package: * org.net9.minipie.server.logic.operation.group
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.GroupUserListEntry;
import org.net9.minipie.server.data.entity.GroupEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Seastar
 *
 */
public class ListGroupMember extends Command<Collection<GroupUserListEntry>> {
	
	private long groupId;
	private long userId;
	
	public ListGroupMember(long userId,long groupId){
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
	public Collection<GroupUserListEntry> execute() {
		GroupStorage executor=getStorageFactory().getGroupStorage();
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();
		Vector<GroupUserListEntry> result=new Vector<GroupUserListEntry>();
		GroupEntity g=executor.selectGroup(groupId);
		if(g.getPerm()==Permission.TO_EVERYONE){
			getResult(result,executor2);
		}else{
			try{
				executor2.isAdmin(userId, groupId);
				getResult(result,executor2);
			}catch(NotFoundException e){
				throw new PermissionDeniedException("the group in't public,join first");
			}
		}
		return result;
	}
	
	private void getResult(Vector<GroupUserListEntry> r,Group_UserStorage e){
		Collection<CommonListEntry> temp=e.selectMember(groupId, true);
		for(CommonListEntry ce:temp){
			try {
				r.add(new GroupUserListEntry(ce.getEntity().getId(),ce.getEntity().getName()
						,ce.getEntity().getImage(),true));
			} catch (DataFormatException e1) {
				throw new ServerErrorException("data format error,db failed");
			}
		}
		temp=e.selectMember(groupId, false);
		for(CommonListEntry ce:temp){
			try {
				r.add(new GroupUserListEntry(ce.getEntity().getId(),ce.getEntity().getName()
						,ce.getEntity().getImage(),false));
			} catch (DataFormatException e1) {
				throw new ServerErrorException("data format error,db failed");
			}
		}
	}
	
}
