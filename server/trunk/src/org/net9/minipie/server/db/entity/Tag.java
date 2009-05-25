package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TAG")
public class Tag {
	@Id
	@GeneratedValue
	@Column(name = "TAG_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User owner;
	
	@Column(name = "TAG_NAME", nullable = false)
	private String tagName;
	
	@OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Tag2Contact> taggedContacts = new ArrayList<Tag2Contact>();
	
	@OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Tag2User> taggedUsers = new ArrayList<Tag2User>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
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
