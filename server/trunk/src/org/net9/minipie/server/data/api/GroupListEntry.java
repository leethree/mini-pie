/**
 * GroupListEntry.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.GroupEntity;

/**
 * @author LeeThree
 * 
 */
@XmlType(name = "group")
public class GroupListEntry {
	private GroupEntity entity;
	private URI uri;

	/**
	 * Constructor
	 */
	public GroupListEntry() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public GroupListEntry(GroupEntity entity) {
		this.entity = entity;
	}

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getGroupId();
	}

	/**
	 * @return the groupName
	 */
	@XmlElement(name = "name")
	public String getGroupName() {
		return entity.getGroupName();
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
