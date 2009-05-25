/**
 * ViewShareUserContact.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteUserInfo;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.UserRelation;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ViewSharedUserContact extends Command<CompleteUserInfo> {
	private long userId;
	//private long agentId;
	private long targetId;
	private CompleteUserInfo result;
	
	public ViewSharedUserContact(long userId, long targetId) {
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
	//public 
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public CompleteUserInfo execute() {		
		User_UserStorage executor = getStorageFactory().getUser_UserStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		UserEntity user = executor2.selectBasicInfo(targetId).getEntity();
		try{
			executor.selectRelationship(userId, targetId);
			
			if (user.getGenderPermission() == Permission.TO_SELF)
				user.setGender(null);
			if (user.getBirthyearPermission() == Permission.TO_SELF) {
				user.setBirthday(user.getBirthday().toSimpleDate());
			}
			if (user.getBirthdatePermission() == Permission.TO_SELF)
				user.setBirthday(null);
			
			Collection<AddressData> addrs = executor2.selectAddr(targetId);
			for (AddressData t : addrs)
				if (t.getPermission() == Permission.TO_SELF)
					addrs.remove(t);		
			user.setAddrs(addrs);
			
			Collection<EmailData> emails = executor2.selectEmail(targetId);
			for (EmailData t : emails)
				if (t.getPermission() == Permission.TO_SELF)
					emails.remove(t);
			user.setEmails(emails);

			Collection<IMData> ims = executor2.selectIM(targetId);
			for (IMData t : ims)
				if (t.getPermission() == Permission.TO_SELF)
					ims.remove(t);
			user.setIms(ims);

			Collection<PhoneNoData> tels = executor2.selectTel(targetId);
			for (PhoneNoData t : tels)
				if (t.getPermission() == Permission.TO_SELF)
					addrs.remove(t);
			user.setTels(tels);

			Collection<URLData> urls = executor2.selectURL(targetId);
			for (URLData t : urls)
				if (t.getPermission() == Permission.TO_SELF)
					addrs.remove(t);
			user.setUrls(urls);
			
			return new CompleteUserInfo(user,userId
					,executor2.selectBasicInfo(userId).getEntity().getName());
			
		}catch(NotFoundException e){
			//normal state
		}
		
		Collection<Long> relations=executor.selectSameContact(userId, targetId);
		if(searchRelation(executor,user,executor2,relations,false))
			return result;
		
		Collection<UserRelation> rels=executor.selectMyUserContact(userId);
		for(UserRelation r:rels){
			long id=r.getId();			
				Collection<Long> relations2=executor.selectSameContact(id, targetId);
				for (Long i:relations2)
					if(executor.selectPermission(id, i)==Permission.TO_SELF)
						relations2.remove(i);				
				if(searchRelation(executor,user,executor2,relations2,true))
					return result;			
		}
		throw new PermissionDeniedException("You can,t see this user's info");
	}
	/*
	 * @return
	 */
	private boolean searchRelation(User_UserStorage executor,UserEntity user
			,UserStorage executor2,Collection<Long> relations,boolean p) {
		if(relations.size()!=0){
			for(Long id:relations){
				if((executor.selectPermission(id, targetId)!=Permission.TO_SELF && !p)
					|| (executor.selectPermission(id, targetId)==Permission.TO_EVERYONE && p)){					
									
					if (user.getGenderPermission() != Permission.TO_EVERYONE)
						user.setGender(null);
					if (user.getBirthyearPermission() != Permission.TO_EVERYONE) {
						user.setBirthday(user.getBirthday().toSimpleDate());
					}
					if (user.getBirthdatePermission() != Permission.TO_EVERYONE)
						user.setBirthday(null);
					
					Collection<AddressData> addrs = executor2.selectAddr(targetId);
					for (AddressData t : addrs)
						if (t.getPermission() != Permission.TO_EVERYONE)
							addrs.remove(t);		
					user.setAddrs(addrs);
					
					Collection<EmailData> emails = executor2.selectEmail(targetId);
					for (EmailData t : emails)
						if (t.getPermission() != Permission.TO_EVERYONE)
							emails.remove(t);
					user.setEmails(emails);

					Collection<IMData> ims = executor2.selectIM(targetId);
					for (IMData t : ims)
						if (t.getPermission() != Permission.TO_EVERYONE)
							ims.remove(t);
					user.setIms(ims);

					Collection<PhoneNoData> tels = executor2.selectTel(targetId);
					for (PhoneNoData t : tels)
						if (t.getPermission() != Permission.TO_EVERYONE)
							addrs.remove(t);
					user.setTels(tels);

					Collection<URLData> urls = executor2.selectURL(targetId);
					for (URLData t : urls)
						if (t.getPermission() != Permission.TO_EVERYONE)
							addrs.remove(t);
					user.setUrls(urls);
					
					result=new CompleteUserInfo(user,id
							,executor2.selectBasicInfo(id).getEntity().getName());
					return true;
				}
			}
		}
		return false;
	}

}
