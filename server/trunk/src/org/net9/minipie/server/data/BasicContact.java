/**
 * BasicContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Riversand
 * 
 */
public class BasicContact {
	private MinimalContact minimalContact;
	private Permission permission;
	private String nickName;
	private Gender gender;
	private Date birthday;
	private String notes;
	private String relationship;
	private long ownerId;
	private long shadowOf;

	/**
	 * Constructor
	 */
	public BasicContact() {
		minimalContact = new MinimalContact();
	}

	public BasicContact(long id, Permission permission, String name,
			String image, String nickName, Gender gender, String birthday,
			String notes, String relationship, long ownerId, long shadowOf) {
		setPermission(permission);
		setName(name);
		minimalContact = new MinimalContact(id, nickName, image);
		setGender(gender);
		setBirthday(birthday);
		setNotes(notes);
		setRelationship(relationship);
		setOwnerId(ownerId);
		setShadowOf(shadowOf);
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		if (birthday == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		ParsePosition parsePos = new ParsePosition(0);
		this.birthday = dateFormat.parse(birthday, parsePos);
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return minimalContact.getId();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		minimalContact.setId(id);
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return minimalContact.getImage();
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {

		minimalContact.setImage(image);
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @return the permission
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setNickName(String nickName) {
		nickName = nickName.trim();
		this.nickName=nickName;
	}

	/**
	 * @return the name
	 */
	public String getNickName() {
		return this.nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setName(String name) {
		this.minimalContact.setName(name);
	}

	/**
	 * @return the nickName
	 */
	public String getName() {
		return minimalContact.getName();
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		if (notes == null) {
			return;
		}
		notes = notes.trim();
		this.notes = notes;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param relationship
	 *            the relationship to set
	 */
	public void setRelationship(String relationship) {
		if (relationship == null) {
			return;
		}
		relationship = relationship.trim();
		this.relationship = relationship;
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerId
	 */
	public long getOwnerId() {
		return ownerId;
	}

	/**
	 * @param shadowOf the shadowOf to set
	 */
	public void setShadowOf(long shadowOf) {
		this.shadowOf = shadowOf;
	}

	/**
	 * @return the shadowOf
	 */
	public long getShadowOf() {
		return shadowOf;
	}

}
