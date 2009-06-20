/**
 * UserEntity.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
public class UserEntity extends CommonEntity {
	private String registeredEmail;
	private String displayname;
	private AddAsContactPermission addAsContactPermission;
	private Permission genderPermission;
	private Permission birthDatePermission;
	private Permission birthYearPermission;
	private Permission permission;
	private boolean isAdmin;
	private Relationships relationship;
	
	/**
	 * Constructor
	 */
	public UserEntity() {
		super();
		addAsContactPermission = AddAsContactPermission.CONFIRMED_ONES;
		genderPermission = Permission.TO_CONTACTS;
		birthDatePermission = Permission.TO_CONTACTS;
		birthYearPermission = Permission.TO_CONTACTS;
		permission=Permission.TO_CONTACTS;
		setAdmin(false);
	}
	
	public UserEntity(CommonEntity ce) throws DataFormatException{
		this();
		setId(ce.getId());
		setName(ce.getName());
		setImage(ce.getImage());
	}
	/**
	 * @return the registeredEmail
	 */
	
	public Permission getPermission(){
		return this.permission;
	}
	
	public void setPermission(Permission perm){
		this.permission=perm;
	}
	
	public String getRegisteredEmail() {
		return registeredEmail;
	}

	/**
	 * @param registeredEmail
	 *            the registeredEmail to set
	 * @throws DataFormatException
	 */
	public void setRegisteredEmail(String registeredEmail)
			throws DataFormatException {
		if (registeredEmail == null)
			throw new ServerErrorException(
					"The registerd email should not be null.");
		this.registeredEmail = Formatter.formatEmail(registeredEmail);
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
		if (displayname == null)
			this.displayname = null;
		else
			this.displayname = Formatter.compact(displayname);
	}

	/**
	 * @return the addAsContactPermission
	 */
	public AddAsContactPermission getAddAsContactPermission() {
		return addAsContactPermission;
	}

	/**
	 * @param addAsContactPermission
	 *            the addAsContactPermission to set
	 */
	public void setAddAsContactPermission(AddAsContactPermission addAsContactPermission) {
		if (addAsContactPermission == null)
			throw new ServerErrorException(
					"Add-as-contact permission not be null.");
		this.addAsContactPermission = addAsContactPermission;
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
		if (genderPermission == null)
			throw new ServerErrorException("Gender permission should not be null.");
		this.genderPermission = genderPermission;
	}

	/**
	 * @return the birthDatePermission
	 */
	public Permission getBirthdatePermission() {
		return birthDatePermission;
	}

	/**
	 * @param birthDatePermission
	 *            the birthDatePermission to set
	 */
	public void setBirthdatePermission(Permission birthDatePermission) {
		if (birthDatePermission == null)
			throw new ServerErrorException("Birth-date permission should not be null.");
		this.birthDatePermission = birthDatePermission;
	}

	/**
	 * @return the birthYearPermission
	 */
	public Permission getBirthyearPermission() {
		return birthYearPermission;
	}

	/**
	 * @param birthYearPermission
	 *            the birthYearPermission to set
	 */
	public void setBirthyearPermission(Permission birthyearPermission) {
		if (birthyearPermission == null)
			throw new ServerErrorException("Birth-year permission should not be null.");
		this.birthYearPermission = birthyearPermission;
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

	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(Relationships relationship) {
		this.relationship = relationship;
	}
	
	/**
	 * @return the relationship
	 */
	public Relationships getRelationship() {
		return relationship;
	}
}
