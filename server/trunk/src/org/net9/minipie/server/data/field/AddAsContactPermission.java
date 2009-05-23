/**
 * AddAsContactPermission.java
 *     in package: * org.net9.minipie.server.data.field
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 *
 */
public enum AddAsContactPermission {
	NO_ONE("no_one"), CONFIRMED_ONES("confirmed_ones"), EVERYONE("everyone");
	private String permission;

	/**
	 * Constructor
	 */
	private AddAsContactPermission(String permission) {
		this.permission = permission;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return permission;
	}

	public static AddAsContactPermission value(String permission)
			throws DataFormatException {
		try {
			return valueOf(permission.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new DataFormatException("Invalid permission type: "
					+ permission + ".");
		}
	}
}
