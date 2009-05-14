package org.net9.minipie.server.data.field;

import org.net9.minipie.server.exception.DataFormatException;

public enum Gender {
	MALE("male"), FEMALE("female"), NEUTRAL("neutral");
	private String gender;

	/**
	 * Constructor
	 */
	private Gender(String gender) {
		this.gender = gender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return gender;
	}

	public static Gender value(String gender) throws DataFormatException {
		try {
			return valueOf(gender.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new DataFormatException("Invalid gender: " + gender + ".");
		}
	}
}
