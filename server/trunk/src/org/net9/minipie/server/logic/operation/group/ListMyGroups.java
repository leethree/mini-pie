/**
 * ListMyGroups.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.api.GroupListEntry;
import org.net9.minipie.server.data.entity.GroupEntity;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Seastar
 * 
 */
public class ListMyGroups extends Command<Collection<GroupListEntry>> {

	private long userId;

	public ListMyGroups(long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<GroupListEntry> execute() {
		Group_UserStorage executor2 = getStorageFactory()
				.getGroup_UserStorage();
		Collection<GroupEntity> groups = executor2.selectGroup(userId);
		Collection<GroupListEntry> grouplist = new ArrayList<GroupListEntry>();
		for (GroupEntity groupEntity : groups) {
			grouplist.add(new GroupListEntry(groupEntity));
		}
		return grouplist;
	}

}
