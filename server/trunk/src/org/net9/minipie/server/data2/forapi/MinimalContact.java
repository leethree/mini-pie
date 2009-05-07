/**
 * MinimalContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.forapi;

import org.net9.minipie.server.data2.entity.ContactEntity;

/**
 * @author Seastar
 * 
 */
public class MinimalContact {
	private ContactEntity entity;
	
	/**
	 * Constructor
	 * @param entity
	 */
	public MinimalContact(ContactEntity entity) {
		super();
		this.entity = entity;
	}

	public MinimalContact(long id, String name, String image) {
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

}
