package org.net9.minipie.server.db.entity;

import javax.persistence.Embeddable;
import javax.persistence.Column;

import org.net9.minipie.server.db.entity.constant.Bool;
import org.net9.minipie.server.db.entity.constant.Permission;
import javax.persistence.Table;

@Embeddable
@Table(name = "USER_IMAGE")
public class UserIM {
	@org.hibernate.annotations.Parent
	private User user;
	@Column(name = "IM_VALUE")
	private String value;
	@Column(name = "IM_TYPE")
	private String type;
	@Column(name = "IS_PRIMARY")
	private Bool primary;
	@Column(name = "PERM")
	private Permission perm;
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
		UserIM o = (UserIM)obj;
		return this.value.compareToIgnoreCase(o.value)==0;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.value.hashCode();
	}
}
