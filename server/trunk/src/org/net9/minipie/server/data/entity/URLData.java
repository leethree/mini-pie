/**
 * URLData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Riversand
 * 
 */
public class URLData implements Info {

	private long id;
	private String value;
	private String type;
	private boolean primary;
	/* the following field is only referenced by user */
	private Permission perm;

	/**
	 * Constructor
	 */
	public URLData() {
		setPrimary(false);
	}

	public URLData(long id, String value, String type, boolean primary,
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

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (value == null) {
			return;
		}
		value = value.trim();
		value = value.toLowerCase();
		if (value.startsWith("http://") == false) {
			// TODO exception handling
		}
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
