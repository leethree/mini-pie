package org.net9.minipie.server.db.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.net9.minipie.server.db.entity.constant.Permission;

@Entity
@Table(name = "Groups")
public class Group {
	@Id
	@GeneratedValue
	@Column(name = "GROUP_ID")
	private Long id;
	private String groupName;
	private String creatorName;
	private Date createdDate = new Date();
	private Permission perm;
	@OneToMany(mappedBy = "group")
	private Collection<Group2User> members = new ArrayList<Group2User>();
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
}
