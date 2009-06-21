/**
 * CreateGroup.java
 *     in package: * org.net9.minipie.server.logic.operation.group
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Seastar
 *
 */
public class CreateGroup extends Command<Long> {
	private String groupName;
	private long userId;
	
	public CreateGroup(long userId,String groupName){
		this.userId=userId;
		if(groupName.equals(""))
			throw new InvalidRequestException("Group name can't be empty");
		this.groupName=Formatter.compact(groupName);
	}
	
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Long execute() {
		GroupStorage executor=getStorageFactory().getGroupStorage();
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();
		long id=executor.createGroup(groupName, userId);
		executor2.add(id, userId);
		executor2.setAdmin(id, userId, true);
		return id;
	}

}
