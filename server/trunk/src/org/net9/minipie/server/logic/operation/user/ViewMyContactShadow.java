/**
 * ViewMyContactShadow.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteContact;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ViewMyContactShadow extends Command<CompleteContact> {
	private long userId;
	private long targetId;
	
	public ViewMyContactShadow(long userId,long targetId){
		this.userId=userId;
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public CompleteContact execute() {
		UserStorage executor=getStorageFactory().getUserStorage();
		User_UserStorage executor2=getStorageFactory().getUser_UserStorage();
		ContactStorage executor3 = getStorageFactory().getContactStorage();
		executor.selectBasicInfo(targetId);
		executor2.selectRelationship(userId, targetId);
		ContactEntity contact=executor3.selectShadowOf(userId, targetId).getEntity();
		long contactId=contact.getId();
		contact.setAddrs(executor3.selectAddr(contactId));
		contact.setEmails(executor3.selectEmail(contactId));
		contact.setIms(executor3.selectIM(contactId));
		contact.setTels(executor3.selectTel(contactId));
		contact.setUrls(executor3.selectURL(contactId));
		return new CompleteContact(contact,userId
				,executor.selectBasicInfo(userId).getEntity().getName());
	}

}
