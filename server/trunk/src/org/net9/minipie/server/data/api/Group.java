/**
 * Group.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.GroupEntity;

/**
 * @author LeeThree
 *
 */
@XmlType(name = "group")
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
	 * @return the createrId
	 */
	@XmlAttribute(name = "createrid")
	public long getCreaterId() {
		return entity.getCreaterId();
	}
	
	/**
	 * @return the createrName
	 */
	@XmlElement(name = "creater")
	public String getCreaterName() {
		return entity.getCreaterName();
	}
	
	/**
	 * @return the perm
	 */
	@XmlElement(name = "permission")
	public String getPerm() {
		return entity.getPerm().toString();
	}
}
