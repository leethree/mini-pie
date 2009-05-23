/**
 * ViewMyUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.PhonebookCompleteUserInfo;
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
		
		Collection<AddressData> addrs=executor.selectAddr(targetId);		
		for(AddressData t:addrs)
			if(t.getPermission()==Permission.TO_SELF)
				addrs.remove(t);		
		user.setAddrs(addrs);
		
		Collection<EmailData> emails=executor.selectEmail(targetId);		
		for(EmailData t:emails)
			if(t.getPermission()==Permission.TO_SELF)
				emails.remove(t);		
		user.setEmails(emails);
		
		Collection<IMData> ims=executor.selectIM(targetId);		
		for(IMData t:ims)
			if(t.getPermission()==Permission.TO_SELF)
				ims.remove(t);	
		user.setIms(ims);
		
		Collection<PhoneNoData> tels=executor.selectTel(targetId);		
		for(PhoneNoData t:tels)
			if(t.getPermission()==Permission.TO_SELF)
				addrs.remove(t);	
		user.setTels(tels);
		
		Collection<URLData> urls=executor.selectURL(targetId);		
		for(URLData t:urls)
			if(t.getPermission()==Permission.TO_SELF)
				addrs.remove(t);	
		user.setUrls(urls);
		
		user.setTags(executor3.selectTagsOfUser(targetId, userId));
		return new PhonebookCompleteUserInfo(user);
	}

}
