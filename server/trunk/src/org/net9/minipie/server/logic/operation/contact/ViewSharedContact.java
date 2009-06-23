/**
 * ViewSharedContact.java
 *     in package: * org.net9.minipie.server.logic.operation.contact
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.api.CompleteContact;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Seastar
 *
 */
public class ViewSharedContact extends Command<CompleteContact> {
	private long userId;
	private long targetId;
	
	public ViewSharedContact(long userId,long targetId){
		this.userId=userId;
		try {
			this.targetId=Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public CompleteContact execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		UserStorage executor2 = getStorageFactory().getUserStorage();
		User_UserStorage executor3=getStorageFactory().getUser_UserStorage();
		//GroupStorage executor5=getStorageFactory().getGroupStorage();		
		Group_UserStorage executor4=getStorageFactory().getGroup_UserStorage();
		
		ContactEntity contact=executor.selectBasicInfo(targetId).getEntity();
		long id=contact.getOwnerId();
		String name=executor2.selectBasicInfo(userId).getEntity().getDisplayname();
		if(contact.getOwnerId()==userId){
			contact.setEmails(executor.selectEmail(targetId));
			contact.setAddrs(executor.selectAddr(targetId));
			contact.setIms(executor.selectIM(targetId));
			contact.setTels(executor.selectTel(targetId));
			contact.setUrls(executor.selectURL(targetId));
			return new CompleteContact(contact,userId,
					executor2.selectBasicInfo(userId).getEntity().getDisplayname());
		}else{
			if(contact.getPermission()==Permission.TO_SELF)
				throw new PermissionDeniedException("this contact is private,can't be viewed");
			try{				
				executor3.selectPermission(id,userId);
				contact.setEmails(executor.selectEmail(targetId));
				contact.setAddrs(executor.selectAddr(targetId));
				contact.setIms(executor.selectIM(targetId));
				contact.setTels(executor.selectTel(targetId));
				contact.setUrls(executor.selectURL(targetId));
				return new CompleteContact(contact,id,name);								
			}catch(NotFoundException e){
				Long gid=contact.getGroupId();
				if(gid!=null){
				try{
					executor4.isAdmin(userId, gid);					
					contact.setEmails(executor.selectEmail(targetId));
					contact.setAddrs(executor.selectAddr(targetId));
					contact.setIms(executor.selectIM(targetId));
					contact.setTels(executor.selectTel(targetId));
					contact.setUrls(executor.selectURL(targetId));
					return new CompleteContact(contact,id,name);
					
				} catch (NotFoundException e1){
					
				}
				}
				if(contact.getPermission()!=Permission.TO_EVERYONE){
					throw new PermissionDeniedException("you can't view this contact");
				}else{
					contact.setEmails(executor.selectEmail(targetId));
					contact.setAddrs(executor.selectAddr(targetId));
					contact.setIms(executor.selectIM(targetId));
					contact.setTels(executor.selectTel(targetId));
					contact.setUrls(executor.selectURL(targetId));
					return new CompleteContact(contact,id,name);
					
				}
			}
		}
		
		//return null;
	}
}

