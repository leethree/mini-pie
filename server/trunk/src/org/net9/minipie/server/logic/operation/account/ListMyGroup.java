/**
 * ListMyGroup.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import java.util.Collection;

import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
/**
 * @author Seastar
 *
 */
public class ListMyGroup extends Command<Collection<GroupEntry>> {

	private long userId;
	
	public ListMyGroup(long userId,long groupId){
		this.userId=userId;				
		
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<GroupEntry> execute() {		
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();		
		return executor2.selectGroup(userId);
	}

}
