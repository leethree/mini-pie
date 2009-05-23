/**
 * URLData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import java.net.URI;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Riversand
 * 
 */
public class URLData implements Info {

	private long id;
	private URI value;
	private String type;
	private boolean primary;
	/* the following field is only referenced by user */
	private Permission perm;

	/**
	 * Constructor
	 */
	public URLData() {
		setPrimary(false);
		setPermission(Permission.TO_SELF);
	}

	public URLData(long id, String value, String type, boolean primary,
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

	public String getValue() throws DataFormatException {
		if (value == null)
			throw new DataFormatException("The value is invalid or null.");
		return value.toASCIIString();
	}

	public void setValue(String value) throws DataFormatException {
		if (value == null)
			throw new DataFormatException("The value should not be null.");
		this.value = Formatter.formatUri(value);
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
	 * @return the perm
	 */
	public Permission getPermission() {
		return perm;
	}

}
