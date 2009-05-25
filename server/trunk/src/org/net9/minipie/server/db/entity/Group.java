package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.net9.minipie.server.data.field.Permission;

@Entity
@Table(name = "GROUPS") //group cannot be assigned to name a table or exception
public class Group {
	@Id
	@GeneratedValue
	@Column(name = "GROUP_ID")
	private Long id;
	@Column(name = "GROUP_NAME", nullable =false)
	private String groupName;
	@Column(name = "CREATOR_NAME")
	private String creatorName;
	@Column(name = "CREATOR_ID")
	private Long creatorId;
	@Column(name = "CREATED_DATE")
	private Date createdDate = new Date();
	@Column(name = "PERMISSION", nullable = false)
	private Permission perm;
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	)
	private Collection<Group2User> members = new ArrayList<Group2User>();
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	private Collection<Contact> groupContacts = new ArrayList<Contact>();
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	private Collection<Notification> groupNotification = new ArrayList<Notification>();
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Permission getPerm() {
		return perm;
	}
	public void setPerm(Permission perm) {
		this.perm = perm;
	}
	public Collection<Group2User> getMembers() {
		return members;
	}
	public void setMembers(Collection<Group2User> members) {
		this.members = members;
	}
	public Collection<Contact> getGroupContacts() {
		return groupContacts;
	}
	public void setGroupContacts(Collection<Contact> groupContacts) {
		this.groupContacts = groupContacts;
	}
	public Collection<Notification> getGroupNotification() {
		return groupNotification;
	}
	public void setGroupNotification(Collection<Notification> groupNotification) {
		this.groupNotification = groupNotification;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * @return the creatorId
	 */
	public Long getCreatorId() {
		return creatorId;
	}
}
