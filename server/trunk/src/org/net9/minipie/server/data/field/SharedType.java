/**
 * SharedType.java
 *     in package: * org.net9.minipie.server.data.field
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public enum SharedType {
	SELF("self"), USER_CONTACT("user_contact"), GROUP_MEMBER("group_member"), PUBLIC_USER(
			"public_user"), CONTACT("contact"), SHARED_CONTACT("shared_contact"), GROUP_CONTACT(
			"group_contact"), SHADOW("shadow");
	private String value;

	private SharedType(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}

	public static SharedType value(String t) throws DataFormatException {
		try {
			return SharedType.valueOf(t.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new DataFormatException("Invalid permission type: " + t + ".");
		}
	}
}
