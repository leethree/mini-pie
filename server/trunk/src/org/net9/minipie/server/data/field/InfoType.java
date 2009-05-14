/**
 * InfoType.java
 *     in package: * org.net9.minipie.server.data.field
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public enum InfoType {
	ADDRESS("address"), BASIC("basic"), EMAIL("email"), IM("im"), PHONE("phone"), URL(
			"url");
	private String type;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            information type
	 */
	private InfoType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return type;
	}

	public static InfoType value(String type) throws DataFormatException {
		if (type.equalsIgnoreCase(ADDRESS.toString()))
			return ADDRESS;
		else if (type.equalsIgnoreCase(BASIC.toString()))
			return BASIC;
		else if (type.equalsIgnoreCase(EMAIL.toString()))
			return EMAIL;
		else if (type.equalsIgnoreCase(IM.toString()))
			return IM;
		else if (type.equalsIgnoreCase(PHONE.toString()))
			return PHONE;
		else if (type.equalsIgnoreCase(URL.toString()))
			return URL;
		else
			throw new DataFormatException("Invalid information type: " + type
					+ ".");
	}
}
