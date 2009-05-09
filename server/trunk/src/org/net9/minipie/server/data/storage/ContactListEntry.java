/**
 * ContactListEntry.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.entity.ContactEntity;

/**
 * @author Seastar
 * 
 */
public class ContactListEntry {
	private ContactEntity entity;

	public ContactListEntry(long id, String name, String image) {
		entity = new ContactEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
	}

	/**
	 * @return the entity
	 */
	public ContactEntity getEntity() {
		return entity;
	}

}
