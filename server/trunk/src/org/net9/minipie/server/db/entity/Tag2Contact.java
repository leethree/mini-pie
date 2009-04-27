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
@Table(name = "TAG_CONTACT")
public class Tag2Contact {
	@Embeddable
	public static class Id implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Column(name = "TAG_ID")
		private Long tagId;
		@Column(name = "CONTACT_ID")
		private Long contactId;
		public Id(){}
		public Id(Long tagId, Long contactId){
			this.tagId = tagId;
			this.contactId = contactId;
		}
		@Override
		public boolean equals(Object obj) {
			Id o = (Id) obj;
			return this.tagId.equals(o.tagId) && this.contactId.equals(o.contactId);
		}
		@Override
		public int hashCode() {
			return tagId.hashCode()+contactId.hashCode();
		}
	}
	@EmbeddedId
	private Id id = new Id();
	@Column(name = "CREATED_DATE")
	private Date date = new Date();
	@ManyToOne
	@JoinColumn(name = "TAG_ID",
			insertable = false,
			updatable = false)
	private Tag tag;
	@ManyToOne
	@JoinColumn(name = "CONTACT_ID",
			insertable = false,
			updatable = false)
	private Contact contact;
	public Tag2Contact() {}
	public Tag2Contact(Tag tag, Contact contact){
		this.id.tagId = tag.getId();
		this.id.contactId = contact.getId();
		this.tag = tag;
		this.contact = contact;
		this.contact.getOwnTags().add(this);
		this.tag.getTaggedContacts().add(this);
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
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
