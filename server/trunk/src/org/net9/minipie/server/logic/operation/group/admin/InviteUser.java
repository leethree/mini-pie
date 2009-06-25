/**
 * InviteUser.java
 *     in package: * org.net9.minipie.server.logic.operation.group.admin
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.group.admin;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.NotificationStorage;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class InviteUser extends Command<Boolean> {
	private String msg;
	private long targetId;
	private long groupId;
	private long userId;

	public InviteUser(long userId, long groupId, long targetId,String msg) {
		this.userId = userId;
		this.msg=msg;
		try {
			this.groupId = Formatter.checkId(groupId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Boolean execute() {
		GroupStorage executor = getStorageFactory().getGroupStorage();
		UserStorage executor3 = getStorageFactory().getUserStorage();
		Group_UserStorage executor2 = getStorageFactory()
				.getGroup_UserStorage();
		executor.selectGroup(groupId);
		UserEntity user;
		try {
			user=executor3.selectBasicInfo(targetId).getEntity();
		} catch (NotFoundException e) {
			throw new InvalidRequestException("This user do not exist");
		}
		try {
			if (executor2.isAdmin(userId, groupId)) {
				try {
					executor2.isAdmin(targetId, groupId); 
					throw new InvalidRequestException(
						"the user have already joined the group");						
				} catch (NotFoundException e) {
					//that's right
				}
				if(user.getAddAsContactPermission()==AddAsContactPermission.NO_ONE)
					throw new PermissionDeniedException("this user don't allow such operation");
				else if(user.getAddAsContactPermission()==AddAsContactPermission.EVERYONE){
					executor2.add(groupId, targetId);
					return true;
				}
				else if(user.getAddAsContactPermission()==AddAsContactPermission.CONFIRMED_ONES){
					NotificationStorage executor4=getStorageFactory().getNotifacationStorage();
					try {
						executor4.add(new NotificationData(1L,0,groupId,targetId,
								"group "+groupId+" want you to be its member, massage:"+msg
								,NotificationType.MEMBERSHIP_INVITATION));
					} catch (DataFormatException e) {
						//won't happen
					}
					return false;
				}
			} else
				throw new PermissionDeniedException(
						"you are not group administor");
		} catch (NotFoundException e) {
			throw new InvalidRequestException(
					"you have not joined such group yet");
		}

		return false;
	}

}
