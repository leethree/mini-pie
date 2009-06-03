/**
 * ViewMyUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteUserInfo;
import org.net9.minipie.server.data.api.PhonebookCompleteUser;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 * 
 */
public class ViewMyUserContact extends Command<PhonebookCompleteUser> {
	private long userId;
	private long targetId;

	public ViewMyUserContact(long userId, long targetId) {
		this.userId = userId;
		try {
			this.targetId = Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		if (userId == targetId) {
			throw new InvalidRequestException("View oneself is not allow in this method.");
		}
	}

	/**
	 * * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public PhonebookCompleteUser execute() {
		UserStorage executor = getStorageFactory().getUserStorage();
		User_UserStorage executor2 = getStorageFactory().getUser_UserStorage();
		Tag_UserStorage executor3 = getStorageFactory().getTag_UserStorage();		
		UserEntity user = executor.selectBasicInfo(targetId).getEntity();
		String rel;
		try {
			rel = executor2.selectRelationship(userId, targetId);
		} catch (NotFoundException e) {
			throw new PermissionDeniedException("User with id " + targetId
					+ " is not your user contact.");
		}
				
		if (user.getGenderPermission() == Permission.TO_SELF)
			user.setGender(null);
		if (user.getBirthyearPermission() == Permission.TO_SELF) {
			user.setBirthday(user.getBirthday().toSimpleDate());
		}
		if (user.getBirthdatePermission() == Permission.TO_SELF)
			user.setBirthday(null);
		
		if (rel != null)
			user.setRelationship(new Relationships(rel));		
		user.setPermission(executor2.selectPermission(userId, targetId));
		
		Collection<AddressData> result=new Vector<AddressData>();
		Collection<AddressData> addrs = executor.selectAddr(targetId);
		for (AddressData t : addrs)
			if (t.getPermission() != Permission.TO_SELF)
				result.add(t);		
		user.setAddrs(result);
		
		Collection<EmailData> eresult=new Vector<EmailData>();
		Collection<EmailData> emails = executor.selectEmail(targetId);
		for (EmailData t : emails)
			if (t.getPermission() != Permission.TO_SELF)
				eresult.add(t);
		user.setEmails(eresult);

		Collection<IMData> iresult=new Vector<IMData>();
		Collection<IMData> ims = executor.selectIM(targetId);
		for (IMData t : ims)
			if (t.getPermission() != Permission.TO_SELF)
				iresult.add(t);
		user.setIms(iresult);

		Collection<PhoneNoData> tresult=new Vector<PhoneNoData>();
		Collection<PhoneNoData> tels = executor.selectTel(targetId);
		for (PhoneNoData t : tels)
			if (t.getPermission() != Permission.TO_SELF)
				tresult.add(t);
		user.setTels(tresult);

		Collection<URLData> uresult=new Vector<URLData>();
		Collection<URLData> urls = executor.selectURL(targetId);
		for (URLData t : urls)
			if (t.getPermission() != Permission.TO_SELF)
				uresult.add(t);
		user.setUrls(uresult);
				
		user.setTags(executor3.selectTagsOfUser(targetId, userId));
		return new PhonebookCompleteUser(user);
	}

}
