package org.net9.minipie.server.db.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TAG_USER")
public class Tag2User {
	@Embeddable
	public static class Id implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Column(name = "TAG_ID")
		private Long tagId;
		@Column(name = "USER_ID")
		private Long userId;
		public Id(){}
		public Id(Long tagId, Long userId){
			this.tagId = tagId;
			this.userId = userId;
		}
		@Override
		public boolean equals(Object obj) {
			Id o = (Id) obj;
			return this.tagId.equals(o.tagId) && this.userId.equals(o.userId);
		}
		@Override
		public int hashCode() {
			return tagId.hashCode()+userId.hashCode();
		}
	}
	@EmbeddedId
	@Column(name = "ID")
	private Id id = new Id();
	@Column(name = "CREATED_DATE")
	private Date date = new Date();
	@ManyToOne
	@JoinColumn(name = "TAG_ID",
			insertable = false,
			updatable = false)
	private Tag tag;
	@ManyToOne
	@JoinColumn(name = "USER_ID",
			insertable = false,
			updatable = false)
	private User user;
	public Tag2User() {}
	public Tag2User(Tag tag, User user){
		this.id.tagId = tag.getId();
		this.id.userId = user.getId();
		this.tag = tag;
		this.user = user;
		this.user.getTags().add(this);
		this.tag.getTaggedUsers().add(this);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
