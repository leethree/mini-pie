package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.db.entity.enums.Bool;

@Entity
@Table(name = "USER_EMAIL")
public class UserEmail {
	@Id
	@GeneratedValue
	@Column(name = "USER_EMAIL_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	@Column(name = "EMAIL", nullable = false)
	private String value;
	@Column(name = "EMAIL_TYPE")
	private String type;
	@Column(name = "IS_PRIMARY")
	private Bool primary;
	@Column(name = "PERM", nullable = false)
	private Permission perm = Permission.TO_CONTACTS;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public boolean equals(Object obj) {
		UserEmail o = (UserEmail)obj;
		return this.value.compareToIgnoreCase(o.value)==0;
	}
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
}
