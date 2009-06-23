/**
 * ViewShareUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteUser;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.SharedType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 * 
 */
public class ViewSharedUserContact extends Command<CompleteUser> {
	private long userId;
	// private long agentId;
	private long targetId;

	// private CompleteUserInfo result;

	public ViewSharedUserContact(long userId, long targetId) {
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
	public CompleteUser execute() {
		// boolean flag;
		User_UserStorage executor = getStorageFactory().getUser_UserStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		Group_UserStorage executor3 = getStorageFactory()
				.getGroup_UserStorage();

		UserEntity user = executor2.selectBasicInfo(targetId).getEntity();
		if (userId == targetId) {
			user.setAddrs(executor2.selectAddr(userId));
			user.setEmails(executor2.selectEmail(userId));
			user.setIms(executor2.selectIM(userId));
			user.setTels(executor2.selectTel(userId));
			user.setUrls(executor2.selectURL(userId));
			return new CompleteUser(user, SharedType.SELF);
		}
		// UserEntity result;
		try {
			executor.selectRelationship(userId, targetId);
			return getContactResult(user, executor2, true);
		} catch (NotFoundException e) {
			Collection<GroupEntry> grs = executor3.selectGroup(userId);
			for (GroupEntry ge : grs) {
				try {
					executor3.isAdmin(targetId, ge.getGroupId());
					return getContactResult(user, executor2, false);
				} catch (NotFoundException e1) {

				}
			}
			return getEveryResult(user, executor2);
			// return null;
		}
	}

	private CompleteUser getEveryResult(UserEntity user, UserStorage executor2) {
		if (user.getGenderPermission() != Permission.TO_EVERYONE)
			user.setGender(null);
		if (user.getBirthday() != null) {
			if (user.getBirthyearPermission() != Permission.TO_EVERYONE) {
				user.setBirthday(user.getBirthday().toSimpleDate());
			}
			if (user.getBirthdatePermission() != Permission.TO_EVERYONE)					
				user.setBirthday(null);
		}
		user.setNotes(null);
		Collection<AddressData> result = new Vector<AddressData>();
		Collection<AddressData> addrs = executor2.selectAddr(targetId);
		for (AddressData t : addrs)
			if (t.getPermission() == Permission.TO_EVERYONE)
				result.add(t);
		user.setAddrs(result);

		Collection<EmailData> eresult = new Vector<EmailData>();
		Collection<EmailData> emails = executor2.selectEmail(targetId);
		for (EmailData t : emails)
			if (t.getPermission() == Permission.TO_EVERYONE)
				eresult.add(t);
		user.setEmails(eresult);

		Collection<IMData> iresult = new Vector<IMData>();
		Collection<IMData> ims = executor2.selectIM(targetId);
		for (IMData t : ims)
			if (t.getPermission() == Permission.TO_EVERYONE)
				iresult.add(t);
		user.setIms(iresult);

		Collection<PhoneNoData> tresult = new Vector<PhoneNoData>();
		Collection<PhoneNoData> tels = executor2.selectTel(targetId);
		for (PhoneNoData t : tels)
			if (t.getPermission() == Permission.TO_EVERYONE)
				tresult.add(t);
		user.setTels(tresult);

		Collection<URLData> uresult = new Vector<URLData>();
		Collection<URLData> urls = executor2.selectURL(targetId);
		for (URLData t : urls)
			if (t.getPermission() == Permission.TO_EVERYONE)
				uresult.add(t);
		user.setUrls(uresult);
		return new CompleteUser(user, SharedType.PUBLIC_USER);
	}

	private CompleteUser getContactResult(UserEntity user,
			UserStorage executor2, boolean userOrGroup) {
		if (user.getGenderPermission() == Permission.TO_SELF)
			user.setGender(null);
		if (user.getBirthday() != null) {
			if (user.getBirthyearPermission() == Permission.TO_SELF) {
				user.setBirthday(user.getBirthday().toSimpleDate());
			}
			if (user.getBirthdatePermission() == Permission.TO_SELF)
				user.setBirthday(null);
		}
		Collection<AddressData> result = new Vector<AddressData>();
		Collection<AddressData> addrs = executor2.selectAddr(targetId);
		for (AddressData t : addrs)
			if (t.getPermission() != Permission.TO_SELF)
				result.add(t);
		user.setAddrs(result);

		Collection<EmailData> eresult = new Vector<EmailData>();
		Collection<EmailData> emails = executor2.selectEmail(targetId);
		for (EmailData t : emails)
			if (t.getPermission() != Permission.TO_SELF)
				eresult.add(t);
		user.setEmails(eresult);

		Collection<IMData> iresult = new Vector<IMData>();
		Collection<IMData> ims = executor2.selectIM(targetId);
		for (IMData t : ims)
			if (t.getPermission() != Permission.TO_SELF)
				iresult.add(t);
		user.setIms(iresult);

		Collection<PhoneNoData> tresult = new Vector<PhoneNoData>();
		Collection<PhoneNoData> tels = executor2.selectTel(targetId);
		for (PhoneNoData t : tels)
			if (t.getPermission() != Permission.TO_SELF)
				tresult.add(t);
		user.setTels(tresult);

		Collection<URLData> uresult = new Vector<URLData>();
		Collection<URLData> urls = executor2.selectURL(targetId);
		for (URLData t : urls)
			if (t.getPermission() != Permission.TO_SELF)
				uresult.add(t);
		user.setUrls(uresult);
		return new CompleteUser(user, userOrGroup ? SharedType.USER_CONTACT
				: SharedType.GROUP_MEMBER);
	}
}
