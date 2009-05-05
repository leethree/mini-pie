/**
 * InfoField.java
 *     in package: * org.net9.minipie.server.data.constant
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.constant;

/**
 * @author Seastar
 * 
 */
public enum InfoField {
	NAME("name"), NICKNAME("nickname"), BIRTHDAY("birthday"), GENDER("gender"), NOTE(
			"note"), RELATIONSHIP("relationship"), VALUE("value"), PRIMARY(
			"primary"), ZIPCODE("zipcode"), TYPE("type"),PERMISSION("permission");
	private String field;

	/**
	 * Constructor
	 */
	private InfoField(String field) {
		this.field = field;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return field;
	}
}