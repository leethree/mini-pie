/**
 * User_User.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.field.Permission;

/**
 * @author Seastar
 *
 */
public class UserRelation {
	private long id;
	private String relationship;
	private Permission permission;
	/**
	 * Constructor
	 * @param id
	 * @param relationship
	 * @param permission
	 */
	public UserRelation(long id, String relationship, Permission permission) {
		super();
		this.id = id;
		this.relationship = relationship;
		this.permission = permission;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	/**
	 * @return the permission
	 */
	public Permission getPermission() {
		return permission;
	}
	/**
	 * @param permission the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
}
