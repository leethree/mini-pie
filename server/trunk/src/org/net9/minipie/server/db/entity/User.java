package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.Permission;

@Entity
@Table(name = "REGISTERED_USER")
public class User{
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String userName;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "REG_EMAIL", nullable = false)
	private String registerEmail;
	@Column(name = "ADD_AS_CONTACT_PERMISSION", nullable = false)
	private AddAsContactPermission perm = AddAsContactPermission.CONFIRMED_ONES;
	@Column(name = "NAME_TO_DISPLAY", nullable = false)
	private String displayName;
	@Column(name = "NICKNAME")
	private String nickname;
	@Column(name = "IMAGE_URL")
	private String imageURL;
	@Column(name = "GENDER")
	private Gender gender;
	@Column(name = "VIEW_GENDER_PERMISSION", nullable = false)
	private Permission genderPermission = Permission.TO_CONTACTS;
	@Column(name = "BIRTHDAY")
	private String birthday;
	@Column(name = "VIEW_BIRTHYEAR_PERMISSION", nullable = false)
	private Permission birthyearPermission = Permission.TO_CONTACTS;
	@Column(name = "VIEW_BIRTHDAYDAY_PERMISSION", nullable = false)
	private Permission birthdayPermission = Permission.TO_CONTACTS;
	@Column(name = "NOTES")
	private String note;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Tag2User> tags = new ArrayList<Tag2User>();
	
	@OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Tag> ownedTags = new ArrayList<Tag>();
	
	@OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Group2User> groups = new ArrayList<Group2User>();
	
	@OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Contact> contacts = new ArrayList<Contact>();
	
	@OneToMany(mappedBy = "shadowOf", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Contact> shadows = new ArrayList<Contact>();
	
	@OneToMany(mappedBy = "user1", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<User2User> users1 = new ArrayList<User2User>();
	
	@OneToMany(mappedBy = "user2", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<User2User> users2 = new ArrayList<User2User>();
	
	@OneToMany(mappedBy = "sender", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Notification> sentNotification = new ArrayList<Notification>();
	
	@OneToMany(mappedBy = "receiver", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Notification> receivedNotification = new ArrayList<Notification>();
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<UserAddress> addresses = new ArrayList<UserAddress>();

	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<UserEmail> emails = new ArrayList<UserEmail>();

	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<UserIM> ims = new ArrayList<UserIM>();

	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<UserPhoneNo> phono = new ArrayList<UserPhoneNo>();

	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<UserURL> url = new ArrayList<UserURL>();
	
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Permission getBirthdayPermission() {
		return birthdayPermission;
	}
	public void setBirthdayPermission(Permission birthdayPermission) {
		this.birthdayPermission = birthdayPermission;
	}
	public Permission getBirthyearPermission() {
		return birthyearPermission;
	}
	public void setBirthyearPermission(Permission birthyearPermission) {
		this.birthyearPermission = birthyearPermission;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Permission getGenderPermission() {
		return genderPermission;
	}
	public void setGenderPermission(Permission genderPermission) {
		this.genderPermission = genderPermission;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getNickName() {
		return nickname;
	}
	public void setNickName(String nickName) {
		this.nickname = nickName;
	}
	public String getNotes() {
		return note;
	}
	public void setNotes(String notes) {
		this.note = notes;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterEmail() {
		return registerEmail;
	}
	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Collection<UserAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(Collection<UserAddress> addresses) {
		this.addresses = addresses;
	}
	public Collection<UserEmail> getEmails() {
		return emails;
	}
	public void setEmails(Collection<UserEmail> emails) {
		this.emails = emails;
	}
	public Collection<UserIM> getIms() {
		return ims;
	}
	public void setIms(Collection<UserIM> ims) {
		this.ims = ims;
	}
	public Collection<UserPhoneNo> getPhono() {
		return phono;
	}
	public void setPhono(Collection<UserPhoneNo> phono) {
		this.phono = phono;
	}
	public Collection<UserURL> getUrl() {
		return url;
	}
	public void setUrl(Collection<UserURL> url) {
		this.url = url;
	}
	public Collection<User2User> getUsers1() {
		return users1;
	}
	public void setUsers1(Collection<User2User> users1) {
		this.users1 = users1;
	}
	public Collection<User2User> getUsers2() {
		return users2;
	}
	public void setUsers2(Collection<User2User> users2) {
		this.users2 = users2;
	}
	public Collection<Group2User> getGroups() {
		return groups;
	}
	public void setGroups(Collection<Group2User> groups) {
		this.groups = groups;
	}
	public Collection<Tag2User> getTags() {
		return tags;
	}
	public void setTags(Collection<Tag2User> tags) {
		this.tags = tags;
	}
	public Collection<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
	public Collection<Contact> getShadows() {
		return shadows;
	}
	public void setShadows(Collection<Contact> shadows) {
		this.shadows = shadows;
	}
	public Collection<Notification> getReceivedNotification() {
		return receivedNotification;
	}
	public void setReceivedNotification(
			Collection<Notification> receivedNotification) {
		this.receivedNotification = receivedNotification;
	}
	public Collection<Notification> getSentNotification() {
		return sentNotification;
	}
	public void setSentNotification(Collection<Notification> sentNotification) {
		this.sentNotification = sentNotification;
	}
	public Collection<Tag> getOwnedTags() {
		return ownedTags;
	}
	public void setOwnedTags(Collection<Tag> ownedTags) {
		this.ownedTags = ownedTags;
	}
	/**
	 * @param perm the perm to set
	 */
	public void setPerm(AddAsContactPermission perm) {
		this.perm = perm;
	}
	/**
	 * @return the perm
	 */
	public AddAsContactPermission getPerm() {
		return perm;
	}
}
