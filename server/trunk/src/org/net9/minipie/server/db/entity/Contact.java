package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;

@Entity
@Table(name = "UNREGISTERED_CONTACT")
public class Contact {
	@Id
	@GeneratedValue
	@Column(name = "CONTACT_ID")
	private Long id;
	@Column(name = "PERMISSION")
	private Permission permission;
	@Column(name = "NAME")
	private String name;
	@Column(name = "IMAGE")
	private String image;
	@Column(name = "NICKNAME")
	private String nickName;
	@Column(name = "GENDER")
	private Gender gender;
	@Column(name = "BIRTHDAY")
	private Date birthday;
	@Column(name = "NOTES")
	private String notes;
	@Column(name = "RELATIONSHIP")
	private String relationship;
	@OneToMany(mappedBy = "contact", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Tag2Contact> ownTags = new ArrayList<Tag2Contact>();
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "contact")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<ContactAddress> address = new ArrayList<ContactAddress>();
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "contact")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<ContactEmail> emails = new ArrayList<ContactEmail>();
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "contact")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<ContactIM> ims = new ArrayList<ContactIM>();
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "contact")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<ContactPhoneNo> phones = new ArrayList<ContactPhoneNo>();
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "contact")
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<ContactURL> urls = new ArrayList<ContactURL>();
	
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User owner;
	
	@ManyToOne
	@JoinColumn(name = "SHADOW_ID")
	private User shadowOf;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public Collection<ContactAddress> getAddress() {
		return address;
	}
	public void setAddress(Collection<ContactAddress> address) {
		this.address = address;
	}
	public Collection<ContactEmail> getEmails() {
		return emails;
	}
	public void setEmails(Collection<ContactEmail> emails) {
		this.emails = emails;
	}
	public Collection<ContactIM> getIms() {
		return ims;
	}
	public void setIms(Collection<ContactIM> ims) {
		this.ims = ims;
	}
	public Collection<ContactPhoneNo> getPhones() {
		return phones;
	}
	public void setPhones(Collection<ContactPhoneNo> phones) {
		this.phones = phones;
	}
	public Collection<ContactURL> getUrls() {
		return urls;
	}
	public void setUrls(Collection<ContactURL> urls) {
		this.urls = urls;
	}
	public Collection<Tag2Contact> getOwnTags() {
		return ownTags;
	}
	public void setOwnTags(Collection<Tag2Contact> ownTags) {
		this.ownTags = ownTags;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getShadowOf() {
		return shadowOf;
	}
	public void setShadowOf(User shadowOf) {
		this.shadowOf = shadowOf;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
