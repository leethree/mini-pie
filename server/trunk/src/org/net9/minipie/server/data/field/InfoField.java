/**
 * InfoField.java
 *     in package: * org.net9.minipie.server.data.field
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public enum InfoField {
	NAME("name"), NICKNAME("nickname"), BIRTHDAY("birthday"), GENDER("gender"), NOTE(
			"note"), RELATIONSHIP("relationship"), VALUE("value"), PRIMARY(
			"primary"), ZIPCODE("zipcode"), TYPE("type"), PERMISSION(
			"permission"),BIRTHDAYPERMISSION("birthdayPermission")
			,BIRTHYEARPERMISSION("birthyearPermission"),REGISTEREMAIL("registerEmail")
			,PASSWORD("password"),DISPLAYNAME("displayName")
			,GENDERPERMISSION("genderPermission"), LEFTPERMISSION("leftPermission"),
			RIGHTPERMISSION("rightPermission"),DESCRIPTION("discription"),IMAGE("image");
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

	public static InfoField value(String field) throws DataFormatException {
		try {
			return valueOf(field.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new DataFormatException("Invalid information field: " + field
					+ ".");
		}
	}
}