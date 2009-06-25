/**
 * Group.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.entity.GroupEntity;

/**
 * @author LeeThree
 *
 */
@XmlRootElement(name = "group")
public class Group {
	private GroupEntity entity;
	
	/**
	 * Constructor
	 */
	public Group() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public Group(GroupEntity entity) {
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
	 * @return the description
	 */
	@XmlElement(name = "description")
	public String getDescription() {
		return entity.getDescription();
	}
	
	/**
	 * @return the creatorId
	 */
	@XmlAttribute(name = "creatorid")
	public long getCreatorId() {
		return entity.getCreatorId();
	}
	
	/**
	 * @return the creatorName
	 */
	@XmlElement(name = "creator")
	public String getCreatorName() {
		return entity.getCreatorName();
	}
	
	/**
	 * @return the perm
	 */
	@XmlElement(name = "permission")
	public String getPerm() {
		return entity.getPerm().toString();
	}
}
