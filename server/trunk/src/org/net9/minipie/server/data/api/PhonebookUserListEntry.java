/**
 * PhonebookUserListEntry.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;

/**
 * @author Seastar
 *
 */
public class PhonebookUserListEntry {
	private UserEntity entity;
	
	public PhonebookUserListEntry(UserEntity entity){
		this.entity=entity;
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

	public Collection<TagEntry> getTags(){
		return entity.getTags();
	}
	
	public Permission getPermission(){
		return entity.getPermission();
	}
	
	public String getRelationship(){
		return entity.getRelationship();
	}
}
