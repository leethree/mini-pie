/**
 * BasicUserInfo.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data.entity.UserEntity;

/**
 * @author Seastar
 *
 */
public class BasicUserInfo {
	UserEntity entity;
	
	public BasicUserInfo(long id,  String name,String RegisteredEmail,String password ,String image,
	String nickName, String displayName, Permission gPerm,Permission bdPerm,
	Permission byPerm ,Gender gender, String birthday,String notes) {
		entity = new UserEntity();
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
	}
	/**
	 * @return the entity
	 */
	public UserEntity getEntity() {
		return entity;
	}
}
