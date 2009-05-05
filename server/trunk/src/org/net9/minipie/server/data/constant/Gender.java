package org.net9.minipie.server.data.constant;

public enum Gender {
	MALE("male"), FEMALE("female"), UNKNOWN;
	private String gender;

	/**
	 * Constructor
	 */
	private Gender() {
		gender = new String();
	}

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
}
