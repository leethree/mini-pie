package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.net9.minipie.server.db.entity.constant.Gender;
import org.net9.minipie.server.db.entity.constant.Permission;

@Entity
public class User{
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	private String userName;
	private String password;
	private String registerEmail;
	private Permission perm;
	private String displayName;
	private String nickName;
	private String imageURL;
	private Gender gender;
	private Permission genderPermission;
	private Date birthday;
	private Permission birthyearPermission;
	private Permission birthdayPermission;
	private String notes;
	
	@OneToMany(mappedBy = "user")
	private Collection<Tag2User> ownTags = new ArrayList<Tag2User>();
	@OneToMany(mappedBy = "member")
	private Collection<Group2User> groups = new ArrayList<Group2User>();
	
	@OneToMany(mappedBy = "user1")
	private Collection<User2User> users1 = new ArrayList<User2User>();
	@OneToMany(mappedBy = "user2")
	private Collection<User2User> users2 = new ArrayList<User2User>();
	
	@org.hibernate.annotations.CollectionOfElements(
			targetElement = org.net9.minipie.server.db.entity.UserAddress.class)
	@JoinTable(
			name = "USER_ADDRESS",
			joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<UserAddress> addresses = new ArrayList<UserAddress>();
	@org.hibernate.annotations.CollectionOfElements(
			targetElement = org.net9.minipie.server.db.entity.UserEmail.class)
	@JoinTable(
			name = "USER_EMAIL",
			joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<UserEmail> emails = new ArrayList<UserEmail>();
	@org.hibernate.annotations.CollectionOfElements(
			targetElement = org.net9.minipie.server.db.entity.UserIM.class)
	@JoinTable(
			name = "USER_IM",
			joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<UserIM> ims = new ArrayList<UserIM>();
	@org.hibernate.annotations.CollectionOfElements(
			targetElement = org.net9.minipie.server.db.entity.UserPhoneNo.class)
	@JoinTable(
			name = "USER_PHONENO",
			joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<UserPhoneNo> phono = new ArrayList<UserPhoneNo>();
	@org.hibernate.annotations.CollectionOfElements(
			targetElement = org.net9.minipie.server.db.entity.UserURL.class)
	@JoinTable(
			name = "USER_URL",
			joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<UserURL> url = new ArrayList<UserURL>();
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
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
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Permission getPerm() {
		return perm;
	}
	public void setPerm(Permission perm) {
		this.perm = perm;
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
	public Collection<Tag2User> getOwnTags() {
		return ownTags;
	}
	public void setOwnTags(Collection<Tag2User> ownTags) {
		this.ownTags = ownTags;
	}
}
