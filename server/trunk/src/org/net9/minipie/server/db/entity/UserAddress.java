package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.net9.minipie.server.db.entity.constant.Bool;
import org.net9.minipie.server.db.entity.constant.Permission;

@Entity
@Table(name = "USER_ADDRESS")
public class UserAddress {
	@Id
	@GeneratedValue
	@Column(name = "USER_ADDRESS_ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	@Column(name = "ADDRESS_TYPE")
	private String type;
	@Column(name = "FORMATTED")
	private String formatted;
	@Column(name = "ZIP")
	private String zipcode;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFormatted() {
		return formatted;
	}
	public void setFormatted(String formatted) {
		this.formatted = formatted;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Bool getPrimary() {
		return primary;
	}
	public void setPrimary(Bool primary) {
		this.primary = primary;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UserAddress o = (UserAddress)obj;
		return this.formatted.compareToIgnoreCase(o.formatted)==0;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.zipcode.hashCode();
	}
}
