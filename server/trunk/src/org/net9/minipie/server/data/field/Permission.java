package org.net9.minipie.server.data.field;

import org.net9.minipie.server.exception.DataFormatException;

public enum Permission {
	TO_SELF("to_self"), TO_CONTACTS("to_contacts"), TO_EVERYONE("to_everyone");
	private String permission;

	/**
	 * Constructor
	 */
	private Permission(String permission) {
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

	public static Permission value(String permission)
			throws DataFormatException {
		try {
			return valueOf(permission.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new DataFormatException("Invalid permission type: "
					+ permission + ".");
		}
	}
}
