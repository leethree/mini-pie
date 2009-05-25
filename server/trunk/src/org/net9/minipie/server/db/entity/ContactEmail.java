package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.net9.minipie.server.db.entity.enums.Bool;

@Entity
@Table(name = "CONTACT_EMAIL")
public class ContactEmail {
	@Id
	@GeneratedValue
	@Column(name = "CONTACT_EMAIL_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	private Contact contact;
	@Column(name = "EMAIL", nullable =false)
	private String value;
	@Column(name = "EMAIL_TYPE")
	private String type;
	@Column(name = "IS_PRIMARY")
	private Bool primary;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public Bool getPrimary() {
		return primary;
	}
    public void setPrimary(Bool primary) {
		this.primary = primary;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	@Override
	public boolean equals(Object obj) {
		ContactEmail o = (ContactEmail) obj;
		return this.value.equals(o.value);
	}
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
}