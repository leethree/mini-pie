package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.net9.minipie.server.db.entity.constant.Bool;

@Embeddable
@Table(name = "CONTACT_IMAGE")
public class ContactIM {
	@org.hibernate.annotations.Parent
	private Contact contact;
	@Column(name = "IM")
	private String value;
	@Column(name = "IM_TYPE")
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
		ContactIM o = (ContactIM) obj;
		return this.value.compareToIgnoreCase(o.value)==0;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.value.hashCode();
	}
}
