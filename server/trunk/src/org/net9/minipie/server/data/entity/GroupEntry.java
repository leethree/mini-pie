/**
 * GroupEntry.java
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
public class GroupEntry {
	private String groupName;
	private String description;
	private long createrId;
	private String createrName;
	private Permission perm;
	/**
	 * Constructor
	 * @param groupName
	 * @param description
	 * @param createrId
	 * @param createrName
	 * @param perm
	 * @throws DataFormatException 
	 */
	public GroupEntry(String groupName, String description, long createrId,
			String createrName, Permission perm) throws DataFormatException {
		super();
		setGroupName(groupName);
		setDescription(description);
		setCreaterId(createrId);
		setCreaterName(createrName);
		setPermission(perm);
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
	 * @return the createrId
	 */
	public long getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 * @throws DataFormatException 
	 */
	public void setCreaterId(long createrId) throws DataFormatException {
		this.createrId = Formatter.checkId(createrId);
	}
	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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
