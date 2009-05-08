/**
 * AddressData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.entity;

import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Riversand
 * 
 */
public class AddressData implements Info {
	private long id;
	private String value;
	private String type;
	private boolean isPrimary;
	/* the following fields are only referenced by user */
	private String formatted;
	private String zipcode;
	private Permission perm;

	/**
	 * Constructor
	 */
	public AddressData() {
	}

	public AddressData(long id, String value, String type, boolean primary,
			String formatted, String zipcode, Permission perm) {
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setFormatted(formatted);
		setZipcode(zipcode);
		setPerm(perm);
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		if (value == null) {
			return;
		}
		value = value.trim();
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	public void setPrimary(boolean primary) {
		this.isPrimary = primary;
	}

	/**
	 * @return the primary
	 */
	public boolean getPrimary() {
		return isPrimary;
	}

	/**
	 * @param formatted
	 *            the formatted to set
	 */
	public void setFormatted(String formatted) {
		if (formatted == null) {
			this.formatted = null;
			return;
		}
		formatted = formatted.trim();
		this.formatted = formatted;
	}

	/**
	 * @return the formatted
	 */
	public String getFormatted() {
		return formatted;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		if (zipcode == null) {
			this.zipcode = null;
			return;
		}
		zipcode = zipcode.trim();
		zipcode = zipcode.replace('(', '-');
		zipcode = zipcode.replace(')', '-');
		this.zipcode = zipcode;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
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
