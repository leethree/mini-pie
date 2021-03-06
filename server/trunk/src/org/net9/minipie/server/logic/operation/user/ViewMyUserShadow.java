/**
 * ViewMyContactShadow.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteContact;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.field.SharedType;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 * 
 */
public class ViewMyUserShadow extends Command<CompleteContact> {
	private long userId;
	private long targetId;

	public ViewMyUserShadow(long userId, long targetId) {
		this.userId = userId;
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
	public CompleteContact execute() {
		UserStorage executor = getStorageFactory().getUserStorage();
		User_UserStorage executor2 = getStorageFactory().getUser_UserStorage();
		ContactStorage executor3 = getStorageFactory().getContactStorage();
		executor.selectBasicInfo(targetId);
		try {
			executor2.selectRelationship(userId, targetId);
		} catch (NotFoundException e) {
			throw new InvalidRequestException("not your user contact");
		}
		try {
			BasicContact contact = executor3.selectShadowOf(userId, targetId);
			if (contact != null) {
				ContactEntity shadow = contact.getEntity();
				long contactId = shadow.getId();
				shadow.setAddrs(executor3.selectAddr(contactId));
				shadow.setEmails(executor3.selectEmail(contactId));
				shadow.setIms(executor3.selectIM(contactId));
				shadow.setTels(executor3.selectTel(contactId));
				shadow.setUrls(executor3.selectURL(contactId));
				return new CompleteContact(shadow, 0, null,SharedType.SHADOW);
			}
			return new CompleteContact(new ContactEntity(),0,null,SharedType.SHADOW);
		} catch (NotFoundException e) {
			return new CompleteContact(new ContactEntity(),0,null,SharedType.SHADOW);
		}
	}

}
