/**
 * MinimalContact.java
 *     in package: * org.net9.minipie.server.data2.fordb
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.fordb;

import org.net9.minipie.server.data2.entity.ContactEntity;

/**
 * @author Seastar
 *
 */
public class ContactList {
	private ContactEntity entity;
	
	public ContactList(long id, String name, String image) {
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
