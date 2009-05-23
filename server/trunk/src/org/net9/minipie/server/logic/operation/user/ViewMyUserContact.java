/**
 * ViewMyUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.PhonebookCompleteUserInfo;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ViewMyUserContact extends Command<PhonebookCompleteUserInfo>{
	private long userId;
	private long targetId;
	
	public ViewMyUserContact(long userId,long targetId){
		this.userId=userId;
		try {
			this.targetId=Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/** * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public PhonebookCompleteUserInfo execute() {
		UserStorage executor=getStorageFactory().getUserStorage();
		User_UserStorage executor2=getStorageFactory().getUser_UserStorage();
		Tag_UserStorage executor3=getStorageFactory().getTag_UserStorage();
		UserEntity user=executor.selectBasicInfo(targetId).getEntity();
		String rel=executor2.selectRelationship(userId, targetId);
		executor.selectBasicInfo(targetId);
		if(user.getGenderPermission()==Permission.TO_SELF)
			user.setGender(null);
		if(user.getBirthyearPermission()==Permission.TO_SELF){			
			user.setBirthday(user.getBirthday().toSimpleDate());
		}
		if(user.getBirthdatePermission()==Permission.TO_SELF)
			user.setBirthday(null);
		user.setRelationship(new Relationships(rel));
		user.setAddrs(executor.selectAddr(targetId));
		user.setEmails(executor.selectEmail(targetId));
		user.setIms(executor.selectIM(targetId));
		user.setTels(executor.selectTel(targetId));
		user.setUrls(executor.selectURL(targetId));
		user.setTags(executor3.selectTagsOfUser(targetId, userId));
		return new PhonebookCompleteUserInfo(user);
	}

}
