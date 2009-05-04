package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.db.entity.enums.Bool;

@Entity
@Table(name = "USER_PHONE_NUMBER")
public class UserPhoneNo {
	@Id
	@GeneratedValue
	@Column(name = "USER_PHONENO_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	@Column(name = "PHONE_NO")
	private String value;
	@Column(name = "PHONE_TYPE")
	private String type;
	@Column(name = "IS_PRIMARY")
	private Bool primary;
	@Column(name = "PERM")
	private Permission perm;
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
		// TODO Auto-generated method stub
		UserPhoneNo o = (UserPhoneNo)obj;
		return this.value.compareToIgnoreCase(o.value)==0;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.value.hashCode();
	}

}
