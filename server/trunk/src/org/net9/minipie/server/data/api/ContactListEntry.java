/**
 * ContactListEntry.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.ContactEntity;

/**
 * @author Seastar, LeeThree
 * TODO
 */
@XmlType(name = "contact")
public class ContactListEntry {
	private ContactEntity entity;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public ContactListEntry(ContactEntity entity) {
		this.entity = entity;
	}

	// public ContactListEntry(long id, String name, String image) {
	// entity.setId(id);
	// entity.setName(name);
	// entity.setImage(image);
	// }

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getId();
	}

	/**
	 * @return the image
	 */
	@XmlElement
	public String getImage() {
		return entity.getImage();
	}

	/**
	 * @return the name
	 */
	@XmlElement
	public String getName() {
		// return entity.isUser() ? entity.getDisplayname() : entity.getName();
		return entity.getName();
	}

}
