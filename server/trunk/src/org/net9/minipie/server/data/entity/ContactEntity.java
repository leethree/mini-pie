/**
 * wholeEntity.java
 *     in package: * org.net9.minipie.server.data2
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Seastar
 * 
 */
public class ContactEntity extends CommonEntity{
	
	private String relationship; // for unregister
	private Permission permission; // for unregister
	private long ownerId; // for unregister
	private long shadowOf; // for unregister
	private long groupId; // for unregister
	
	/**
	 * Constructor
	 */
	public ContactEntity() {
		permission = Permission.TO_SELF;
		
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @param relationship
	 *            the relationship to set
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
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @return the ownerId
	 */
	public long getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the shadowOf
	 */
	public long getShadowOf() {
		return shadowOf;
	}

	/**
	 * @param shadowOf
	 *            the shadowOf to set
	 */
	public void setShadowOf(long shadowOf) {
		this.shadowOf = shadowOf;
	}

	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

}