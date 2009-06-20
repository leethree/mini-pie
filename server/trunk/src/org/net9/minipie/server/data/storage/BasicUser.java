/**
 * BasicUser.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.Birthdate;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public class BasicUser {
	UserEntity entity;

	public BasicUser(long id, String name, String RegisteredEmail,
			String password, String image, String nickName, String displayName,
			Permission gPerm, Permission bdPerm, Permission byPerm,
			Gender gender, String birthday, String notes,AddAsContactPermission contactPerm)
			throws DataFormatException {
		entity = new UserEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
		entity.setNickName(nickName);
		entity.setGender(gender);
		if (birthday != null) {
			entity.setBirthday(new Birthdate(birthday));
		}
		entity.setNotes(notes);
		entity.setDisplayname(displayName);
		entity.setGenderPermission(gPerm);
		entity.setBirthdatePermission(bdPerm);
		entity.setBirthdatePermission(byPerm);
		entity.setRegisteredEmail(RegisteredEmail);
		if(contactPerm!=null)
			entity.setAddAsContactPermission(contactPerm);
	}

	/**
	 * @return the entity
	 */
	public UserEntity getEntity() {
		return entity;
	}
}
