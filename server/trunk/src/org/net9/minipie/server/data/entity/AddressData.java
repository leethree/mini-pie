/**
 * AddressData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Riversand
 * 
 */
public class AddressData implements Info {
	private long id;
	private String value;
	private String type;
	private boolean primary;
	private String zipcode;
	/* the following fields are only referenced by user */
	private Permission perm;

	/**
	 * Constructor
	 */
	public AddressData() {
		setPrimary(false);
		setPermission(Permission.TO_SELF);
	}

	public AddressData(long id, String value, String type, boolean primary,
			String formatted, String zipcode, Permission perm)
			throws DataFormatException {
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setZipcode(zipcode);
		setPermission(perm);
	}

	/**
	 * @param id
	 *            the id to set
	 * @throws DataFormatException
	 */
	public void setId(long id) throws DataFormatException {
		this.id = Formatter.checkId(id);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param value
	 *            the value to set (not nullable)
	 * @throws DataFormatException 
	 */
	public void setValue(String value) throws DataFormatException {
		if (value == null)
			throw new DataFormatException("The value should not be null.");
		this.value = value.trim();
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param type
	 *            the type to set (nullable)
	 */
	public void setType(String type) {
		if (type == null)
			this.type = null;
		else
			this.type = Formatter.compact(type);
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
		this.primary = primary;
	}

	/**
	 * @return the primary
	 */
	public boolean isPrimary() {
		return primary;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set (nullable)
	 */
	public void setZipcode(String zipcode) {
		if (zipcode == null)
			this.zipcode = null;
		else
			this.zipcode = Formatter.compact(zipcode).toUpperCase();
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
	public void setPermission(Permission perm) {
		if (perm == null)
			throw new ServerErrorException("The permission should not be null.");
		this.perm = perm;
	}

	/**
	 * @return the perm (not nullable)
	 */
	public Permission getPermission() {
		return perm;
	}
}
