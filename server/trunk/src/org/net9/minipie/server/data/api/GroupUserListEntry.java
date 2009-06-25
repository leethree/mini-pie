/**
 * GroupUserListEntry.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
@XmlType(name = "user")
public class GroupUserListEntry {
	private UserEntity entity;

	/**
	 * Constructor
	 */
	public GroupUserListEntry() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public GroupUserListEntry(UserEntity entity) {
		super();
		this.entity = entity;
	}

	public GroupUserListEntry(long id, String name, String image,
			boolean isAdmin) throws DataFormatException {
		this.entity = new UserEntity();
		this.entity.setName(name);
		this.entity.setId(id);
		this.entity.setImage(image);
		this.entity.setAdmin(isAdmin);
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

	@XmlAttribute(name = "admin")
	public boolean getIsAdmin() {
		return entity.isAdmin();
	}
}
