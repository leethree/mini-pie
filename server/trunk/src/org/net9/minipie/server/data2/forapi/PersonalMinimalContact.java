/**
 * PersonalMinimalContact.java
 *     in package: * org.net9.minipie.server.data2.forapi
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.forapi;

import java.util.Collection;

import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data2.Tag;
import org.net9.minipie.server.data2.entity.ContactEntity;

/**
 * @author Seastar
 *
 */
public class PersonalMinimalContact {
private ContactEntity entity;
	
	/**
	 * Constructor
	 * @param entity
	 */
	public PersonalMinimalContact(ContactEntity entity) {
		super();
		this.entity = entity;
	}

	public PersonalMinimalContact(long id, String name, String image) {
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return entity.getId();
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return entity.getImage();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return entity.isUser()? entity.getDisplayname():entity.getName();
	}
	
	public Collection<Tag> getTags(){
		return entity.getTags();
	}
	
	public Permission getPermission(){
		return entity.getPermission();
	}
}
