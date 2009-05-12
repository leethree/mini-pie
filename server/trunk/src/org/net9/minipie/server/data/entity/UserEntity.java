/**
 * UserEntity.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Seastar
 *
 */
public class UserEntity extends CommonEntity{
	private String registeredEmail; // for user
	private String password; // for user
	private String displayname; // for user
	private Permission genderPermission; // for user
	private Permission birthdayPermission; // for user
	private Permission birthyearPermission; // for user
	private boolean isAdmin; // for user
	
	/**
	 * Constructor
	 */
	public UserEntity() {
		genderPermission = Permission.TO_CONTACTS;
		birthdayPermission = Permission.TO_CONTACTS;
		birthyearPermission = Permission.TO_CONTACTS;
		setAdmin(false);
	}
	
	public String getRegisteEmail() {
		return registeredEmail;
	}

	/**
	 * @param registeEmail
	 *            the registeEmail to set
	 */
	public void setRegisteEmail(String registeEmail) {
		this.registeredEmail = registeEmail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the displayname
	 */
	public String getDisplayname() {
		return displayname;
	}

	/**
	 * @param displayname
	 *            the displayname to set
	 */
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	/**
	 * @return the genderPermission
	 */
	public Permission getGenderPermission() {
		return genderPermission;
	}

	/**
	 * @param genderPermission
	 *            the genderPermission to set
	 */
	public void setGenderPermission(Permission genderPermission) {
		this.genderPermission = genderPermission;
	}

	/**
	 * @return the birthdayPermission
	 */
	public Permission getBirthdayPermission() {
		return birthdayPermission;
	}

	/**
	 * @param birthdayPermission
	 *            the birthdayPermission to set
	 */
	public void setBirthdayPermission(Permission birthdayPermission) {
		this.birthdayPermission = birthdayPermission;
	}

	/**
	 * @return the birthyearPermission
	 */
	public Permission getBirthyearPermission() {
		return birthyearPermission;
	}

	/**
	 * @param birthyearPermission
	 *            the birthyearPermission to set
	 */
	public void setBirthyearPermission(Permission birthyearPermission) {
		this.birthyearPermission = birthyearPermission;
	}
	/**
	 * @return the registeredEmail
	 */
	public String getRegisteredEmail() {
		return registeredEmail;
	}

	/**
	 * @param registeredEmail
	 *            the registeredEmail to set
	 */
	public void setRegisteredEmail(String registeredEmail) {
		this.registeredEmail = registeredEmail;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


}


