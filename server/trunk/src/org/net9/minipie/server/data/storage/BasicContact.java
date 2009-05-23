/**
 * BasicContact.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.field.Birthdate;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public class BasicContact {
	private ContactEntity entity;

	public BasicContact(long id, String name, String image, String nickName,
			Gender gender, String birthday, String notes, String relationship,
			long ownerId, long shadowOf, long groupId, Permission perm
			)
			throws DataFormatException {
		entity = new ContactEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
		entity.setNickName(nickName);
		entity.setGender(gender);
		if (birthday != null) {
			entity.setBirthday(new Birthdate(birthday));
		}
		entity.setNotes(notes);
		if (relationship != null) {
			entity.setRelationship(new Relationships(relationship));
		}
		entity.setOwnerId(ownerId);
		entity.setShadowOf(shadowOf);
		entity.setGroupId(groupId);
		if (perm!=null)
			entity.setPermission(perm);
		
	}

	/**
	 * @return the entity
	 */
	public ContactEntity getEntity() {
		return entity;
	}
}
