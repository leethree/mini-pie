package org.net9.minipie.server.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.net9.minipie.server.db.entity.enums.Bool;

@Entity
@Table(name = "GROUP_USER")
public class Group2User {
	@Embeddable
	public static class Id implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Column(name = "GROUP_ID")
		private Long groupId;
		@Column(name = "MEMBER_ID")
		private Long memberId;
		public Id(){ }
		public Id(Long groupId, Long memberId){
			this.groupId = groupId;
			this.memberId = memberId;
		}
		@Override
		public boolean equals(Object obj) {
			Id o = (Id) obj;
			return this.groupId.equals(o.groupId) && this.memberId.equals(o.memberId);
		}
		@Override
		public int hashCode() {
			return groupId.hashCode()+memberId.hashCode();
		}
	}
	@EmbeddedId
	private Id id = new Id();
	@Column(name = "IS_ADMINISTRATOR")
	private Bool isAdmin;
	@ManyToOne
	@JoinColumn(name = "GROUP_ID",
			insertable = false,
			updatable = false)
	private Group group;
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID",
			insertable = false,
			updatable = false)
	private User member;
	public Group2User() {}
	public Group2User(Group group, User member, Bool isAdmin){
		this.id.groupId = group.getId();
		this.id.memberId = member.getId();
		this.isAdmin = isAdmin;
		this.group = group;
		this.member = member;
		this.member.getGroups().add(this);
		this.group.getMembers().add(this);
	}
	public Bool getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Bool isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public User getMember() {
		return member;
	}
	public void setMember(User member) {
		this.member = member;
	}
}
