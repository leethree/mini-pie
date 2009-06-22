/**
 * EditGroupInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.group.admin
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group.admin;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Seastar
 *
 */
public class EditGroupInfo extends Command<Void> {

	private String value;
	private InfoField field;
	private long groupId;
	private long userId;
	
	public EditGroupInfo(long userId,long groupId,String value,String field){
		this.userId=userId;		
		this.value=value;
		try {
			this.field=InfoField.value(field);
		} catch (DataFormatException e1) {
			throw new InvalidRequestException(e1);
		}
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
	public Void execute() {
		GroupStorage executor=getStorageFactory().getGroupStorage();
		Group_UserStorage executor2=getStorageFactory().getGroup_UserStorage();
		executor.selectGroup(groupId);
		try{
			if(executor2.isAdmin(userId, groupId))
				executor.editGroup(groupId, field, value);			
			else
				throw new PermissionDeniedException(
				"you are not group administor");
		}catch(NotFoundException e){
			throw new InvalidRequestException(
				"you have not joined such group yet");
		}
		
		return null;
	}

}
