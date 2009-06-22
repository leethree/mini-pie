/**
 * ViewGroupInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.group
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group;

import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
/**
 * @author Seastar
 *
 */
public class ViewGroupInfo extends Command<GroupEntry> {
	private long groupId;
	
	public ViewGroupInfo(long groupId){
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
	public GroupEntry execute() {
		GroupStorage executor=getStorageFactory().getGroupStorage();
		return executor.selectGroup(groupId);
	}

}
