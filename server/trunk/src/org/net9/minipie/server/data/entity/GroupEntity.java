/**
 * GroupEntity.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 *
 */
public class GroupEntity {
	private String groupName;
	private String description;
	private long creatorId;
	private String creatorName;
	private Permission perm;
	private long groupId;
	/**
	 * Constructor
	 * @param groupName
	 * @param description
	 * @param creatorId
	 * @param creatorName
	 * @param perm
	 * @throws DataFormatException 
	 */
	public GroupEntity(long groupId,String groupName, String description, long creatorId,
			String creatorName, Permission perm) throws DataFormatException {
		super();
		setGroupId(groupId);
		setGroupName(groupName);
		setDescription(description);
		setCreatorId(creatorId);
		setCreatorName(creatorName);
		setPermission(perm);
	}
	/**
	 * @param groupId the groupId to set
	 * @throws DataFormatException 
	 */
	public void setGroupId(long groupId) throws DataFormatException {
		this.groupId = Formatter.checkId(groupId);;
	}
	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = Formatter.compact(groupName);
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the creatorId
	 */
	public long getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 * @throws DataFormatException 
	 */
	public void setCreatorId(long creatorId) throws DataFormatException {
		this.creatorId = Formatter.checkId(creatorId);
	}
	/**
	 * @return the creatorName
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * @param creatorName the creatorName to set
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	/**
	 * @return the perm
	 */
	public Permission getPerm() {
		return perm;
	}
	/**
	 * @param perm the perm to set
	 */
	public void setPermission(Permission perm) {
		this.perm = perm;
	}
	
}
