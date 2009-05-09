/**
 * PhonebookContactListEntry.java
 *     in package: * org.net9.minipie.server.data2.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.api;

import java.util.Collection;

import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data2.entity.ContactEntity;
import org.net9.minipie.server.data2.entity.Tag;

/**
 * @author Seastar, LeeThree
 * 
 */
public class PhonebookContactListEntry {
	private ContactEntity entity;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public PhonebookContactListEntry(ContactEntity entity) {
		this.entity = entity;
	}

	// public PhonebookContactListEntry(long id, String name, String image) {
	// entity.setId(id);
	// entity.setName(name);
	// entity.setImage(image);
	// }

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
		// return entity.isUser() ? entity.getDisplayname() : entity.getName();
		return entity.getName();
	}

	public Collection<Tag> getTags() {
		return entity.getTags();
	}

	public Permission getPermission() {
		return entity.getPermission();
	}
}