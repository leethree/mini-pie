/**
 * PhonebookUserListEntry.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.entity.UserEntity;

/**
 * @author Seastar
 * 
 */
@XmlType(name = "user")
public class PhonebookUserListEntry {
	private UserEntity entity;
	private URI uri;

	/**
	 * Constructor
	 */
	public PhonebookUserListEntry() {
	}

	public PhonebookUserListEntry(UserEntity entity) {
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
		return entity.getDisplayname();
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement(name = "tag")
	public Collection<TagXml> getTags() {
		Collection<TagXml> tags = new ArrayList<TagXml>();
		for (TagEntry tagEntry : entity.getTags()) {
			tags.add(new TagXml(tagEntry));
		}
		return tags;
	}

	/**
	 * @return the permission
	 */
	@XmlAttribute
	public String getPermission() {
		if (entity.getPermission() == null)
			return null;
		else
			return entity.getPermission().toString();
	}

	public String getRelationship() {
		return entity.getRelationship().toString();
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
