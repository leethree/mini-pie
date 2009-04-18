package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Table(name = "TAG")
public class Tag {
	@Id
	@GeneratedValue
	@Column(name = "TAG_ID")
	private Long id;
	@Column(name = "OWNER_ID")
	private Long ownerId;
	@Column(name = "TAG_NAME")
	private String tagName;
	@OneToMany(mappedBy = "tag")
	private Collection<Tag2Contact> taggedContacts = new ArrayList<Tag2Contact>();
	@OneToMany(mappedBy = "tag")
	private Collection<Tag2User> taggedUsers = new ArrayList<Tag2User>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Collection<Tag2Contact> getTaggedContacts() {
		return taggedContacts;
	}
	public void setTaggedContacts(Collection<Tag2Contact> taggedContacts) {
		this.taggedContacts = taggedContacts;
	}
	public Collection<Tag2User> getTaggedUsers() {
		return taggedUsers;
	}
	public void setTaggedUsers(Collection<Tag2User> taggedUsers) {
		this.taggedUsers = taggedUsers;
	}
}
