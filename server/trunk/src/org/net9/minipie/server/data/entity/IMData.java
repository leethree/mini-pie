/**
 * IMData.java
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
		setPrimary(false);
		setPermission(Permission.TO_SELF);
	}

	public IMData(long id, String value, String type, boolean primary,
			Permission perm) throws DataFormatException {
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setPermission(perm);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) throws DataFormatException {
		this.id = Formatter.checkId(id);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null)
			this.type = null;
		else
			this.type = Formatter.compact(type);
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

	public void setValue(String value) throws DataFormatException {
		if (value == null)
			throw new DataFormatException("The value should not be null.");
		this.value = Formatter.removeSpace(value);
	}

	/**
	 * @param perm
	 *            the perm to set
	 */
	public void setPermission(Permission perm) {
		if (perm == null)
			throw new ServerErrorException(
					"The permission should not be null.");
		this.perm = perm;
	}

	/**
	 * @return the perm (not nullable)
	 */
	public Permission getPermission() {
		return perm;
	}
}
