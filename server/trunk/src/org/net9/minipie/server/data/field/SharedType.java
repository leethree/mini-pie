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
	SELF("self"), USER_CONTACT("user contact"), GROUP_MEMBER("group member"), PUBLIC_USER(
			"public user"),CONTACT("contact"),SHARED_CONTACT("shared contact")
			,GROUP_CONTACT("group contact"),SHADOW("shadow");
	private String value;

	private SharedType(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}

	public static SharedType value(String t)
			throws DataFormatException {
		try {
			return SharedType.valueOf(t.toLowerCase());
		} catch (IllegalArgumentException e) {
			throw new DataFormatException("Invalid permission type: "
					+ t + ".");
		}
	}
}
