/**
 * ContactListEntry.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.CommonEntity;

/**
 * @author Seastar, LeeThree
 * 
 */
@XmlType(name = "user")
public class UserListEntry {
	private CommonEntity entity;
	private URI uri;

	/**
	 * Constructor
	 */
	public UserListEntry() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public UserListEntry(CommonEntity entity) {
		this.entity = entity;
	}

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
