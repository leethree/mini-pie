/**
 * wholeEntity.java
 *     in package: * org.net9.minipie.server.data2
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Seastar
 * 
 */
public class ContactEntity {
	private long id;
	private String name;
	private String image;
	private String nickName;
	private String registeredEmail; // for user
	private String password; // for user
	private String displayname; // for user
	private Gender gender;
	private Permission genderPermission; // for user
	private Date birthday;
	private Permission birthdayPermission; // for user
	private Permission birthyearPermission; // for user
	private String notes;
	private String relationship; // for unregister
	private Permission permission; // for unregister
	private long ownerId; // for unregister
	private long shadowOf; // for unregister
	private long groupId; // for unregister
	private Collection<AddressData> addrs;
	private Collection<EmailData> emails;
	private Collection<IMData> ims;
	private Collection<PhoneNoData> tels;
	private Collection<URLData> urls;
	private Collection<Tag> tags;
	private boolean isAdmin; // for user
	private boolean isUser; // for system

	/**
	 * Constructor
	 */
	public ContactEntity() {
		genderPermission = Permission.TO_CONTACTS;
		birthdayPermission = Permission.TO_CONTACTS;
		birthyearPermission = Permission.TO_CONTACTS;
		permission = Permission.TO_SELF;
		setAdmin(false);
		setUser(false);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {			// 为什么这里是String? -- LeeThree
		if (birthday == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		ParsePosition parsePos = new ParsePosition(0);
		this.birthday = dateFormat.parse(birthday, parsePos);
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @param relationship
	 *            the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/**
	 * @return the permission
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @return the ownerId
	 */
	public long getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the shadowOf
	 */
	public long getShadowOf() {
		return shadowOf;
	}

	/**
	 * @param shadowOf
	 *            the shadowOf to set
	 */
	public void setShadowOf(long shadowOf) {
		this.shadowOf = shadowOf;
	}

	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the addrs
	 */
	public Collection<AddressData> getAddrs() {
		return addrs;
	}

	/**
	 * @param addrs
	 *            the addrs to set
	 */
	public void setAddrs(Collection<AddressData> addrs) {
		this.addrs = addrs;
	}

	/**
	 * @return the emails
	 */
	public Collection<EmailData> getEmails() {
		return emails;
	}

	/**
	 * @param emails
	 *            the emails to set
	 */
	public void setEmails(Collection<EmailData> emails) {
		this.emails = emails;
	}

	/**
	 * @return the ims
	 */
	public Collection<IMData> getIms() {
		return ims;
	}

	/**
	 * @param ims
	 *            the ims to set
	 */
	public void setIms(Collection<IMData> ims) {
		this.ims = ims;
	}

	/**
	 * @return the tels
	 */
	public Collection<PhoneNoData> getTels() {
		return tels;
	}

	/**
	 * @param tels
	 *            the tels to set
	 */
	public void setTels(Collection<PhoneNoData> tels) {
		this.tels = tels;
	}

	/**
	 * @return the urls
	 */
	public Collection<URLData> getUrls() {
		return urls;
	}

	/**
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(Collection<URLData> urls) {
		this.urls = urls;
	}

	/**
	 * @return the tags
	 */
	public Collection<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the registeEmail
	 */
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
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	/**
	 * @return the isUser
	 */
	public boolean isUser() {
		return isUser;
	}

	/**
	 * @param isUser
	 *            the isUser to set
	 */
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

}
