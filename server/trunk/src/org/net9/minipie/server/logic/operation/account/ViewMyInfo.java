/**
 * ViewMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.PersonalProfile;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class ViewMyInfo extends Command<PersonalProfile> {
	private long userId;
	public ViewMyInfo(long userId){
		try {
			this.userId = Formatter.checkId(userId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#excute()
	 */
	public PersonalProfile execute() {
		UserStorage executor=getStorageFactory().getUserStorage();
		UserEntity entity=executor.selectBasicInfo(userId).getEntity();
		entity.setAddrs(executor.selectAddr(userId));
		entity.setEmails(executor.selectEmail(userId));
		entity.setIms(executor.selectIM(userId));
		entity.setTels(executor.selectTel(userId));
		entity.setUrls(executor.selectURL(userId));
		return new PersonalProfile(entity);
	}
}
