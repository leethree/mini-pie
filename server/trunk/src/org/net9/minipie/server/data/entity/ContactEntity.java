/**
 * wholeEntity.java
 *     in package: * org.net9.minipie.server.data2
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
public class ContactEntity extends CommonEntity {
	private Relationships relationship;
	private Permission permission;
	private long ownerId;
	private long shadowOf;
	private long groupId;

	/**
	 * Constructor
	 */
	public ContactEntity() {
		super();
		permission = Permission.TO_SELF;
	}
	public ContactEntity(CommonEntity ce) throws DataFormatException{
		this();
		setId(ce.getId());
		setName(ce.getName());
		setImage(ce.getImage());
	}
	/**
	 * @return the relationship
	 */
	public Relationships getRelationship() {
		return relationship;
	}

	/**
	 * @param relationship
	 *            the relationship to set (nullable)
	 */
	public void setRelationship(Relationships relationship) {
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
		if (permission == null)
			throw new ServerErrorException("The permission should not be null.");
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
	 * @throws DataFormatException
	 */
	public void setOwnerId(long ownerId) throws DataFormatException {
		this.ownerId = Formatter.checkNullableId(ownerId);
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
	 * @throws DataFormatException
	 */
	public void setShadowOf(long shadowOf) throws DataFormatException {
		this.shadowOf = Formatter.checkNullableId(shadowOf);
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
	 * @throws DataFormatException
	 */
	public void setGroupId(long groupId) throws DataFormatException {
		this.groupId = Formatter.checkNullableId(groupId);
	}

}