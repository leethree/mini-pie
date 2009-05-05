package org.net9.minipie.server.data.constant;

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
}
