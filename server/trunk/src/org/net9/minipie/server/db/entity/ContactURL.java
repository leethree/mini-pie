package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.net9.minipie.server.db.entity.constant.Bool;
import javax.persistence.Table;

@Embeddable
@Table(name = "CONTACT_URL")
public class ContactURL {
	@org.hibernate.annotations.Parent
	private Contact contact;
	@Column(name = "URL")
	private String value;
	@Column(name = "URL_TYPE")
	private String type;
	@Column(name = "IS_PRIMARY")
	private Bool primary;
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
		// TODO Auto-generated method stub
		ContactURL o = (ContactURL) obj;
		return this.value.compareToIgnoreCase(o.value)==0;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.value.hashCode();
	}

}
