/**
 * ContactEntry.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.ContactEntity;

/**
 * @author Seastar
 * 
 */
@XmlType(name = "contact")
public class ContactEntry {
	private ContactEntity entity;
	private URI uri;

	/**
	 * Constructor
	 */
	public ContactEntry() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public ContactEntry(ContactEntity entity) {
		this.entity = entity;
	}

	// public ContactEntry(long id, String name, String image) {
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
		// return entity.isUser()? entity.getDisplayname():entity.getName();
		return entity.getName();
	}

	/**
	 * @return the uri
	 */
	@XmlAttribute
	public URI getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}
}
