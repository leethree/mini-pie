/**
 * IMData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2;

import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Riversand
 * 
 */
public class IMData implements Info {
	private long id;
	private String value;
	private String type;
	private boolean primary;
	/* the following field(s) is referenced only by user */
	private Permission perm;

	/**
	 * Constructor
	 */
	public IMData() {
	}

	public IMData(long id, String value, String type, boolean primary,
			Permission perm) {
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setPerm(perm);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param perm
	 *            the perm to set
	 */
	public void setPerm(Permission perm) {
		this.perm = perm;
	}

	/**
	 * @return the perm
	 */
	public Permission getPerm() {
		return perm;
	}

}
