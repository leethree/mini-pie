/**
 * BasicContact.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data.entity.ContactEntity;

/**
 * @author Seastar
 *
 */
public class BasicContact {
	private ContactEntity entity; 
	
	public BasicContact(long id,  String name,String image, String nickName, 
			Gender gender, String birthday,String notes, String relationship,
			long ownerId,long shadowOf,long groupId) {
		entity = new ContactEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
		entity.setNickName(nickName);
		entity.setGender(gender);
		entity.setBirthday(birthday);
		entity.setNotes(notes);
		entity.setRelationship(relationship);
		entity.setOwnerId(ownerId);
		entity.setShadowOf(shadowOf);
		entity.setGroupId(groupId);
		entity.setUser(false);
	}
	
	public BasicContact(long id,  String name,String RegisteredEmail,String password ,String image,
			String nickName, String displayName, Permission gPerm,Permission bdPerm,
			Permission byPerm ,Gender gender, String birthday,String notes) {
		entity = new ContactEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
		entity.setNickName(nickName);
		entity.setGender(gender);
		entity.setBirthday(birthday);
		entity.setNotes(notes);
		entity.setDisplayname(displayName);
		entity.setGenderPermission(gPerm);
		entity.setBirthdayPermission(bdPerm);
		entity.setBirthdayPermission(byPerm);
		entity.setPassword(password);
		entity.setRegisteEmail(RegisteredEmail);
		entity.setUser(true);
		
	}
	/**
	 * @return the entity
	 */
	public ContactEntity getEntity() {
		return entity;
	}
}
