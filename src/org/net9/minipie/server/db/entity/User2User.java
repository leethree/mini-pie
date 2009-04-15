package org.net9.minipie.server.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.net9.minipie.server.db.entity.constant.Permission;

@Entity
public class User2User {
	@Embeddable
	public static class Id implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Column(name = "USER1_ID")
		private Long user1Id;
		@Column(name = "USER2_ID")
		private Long user2Id;
		public Id() {}
		public Id(Long user1Id, Long user2Id){
			this.user1Id = user1Id;
			this.user2Id = user2Id;
		}
		@Override
		public boolean equals(Object o) {
			// TODO Auto-generated method stub
			Id obj = (Id) o;
			return ((this.user1Id.equals(obj.user1Id) && this.user2Id.equals(obj.user2Id))
					||(this.user1Id.equals(obj.user2Id) && this.user2Id.equals(obj.user1Id))) ;
		}
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return user1Id.hashCode()+user2Id.hashCode();
		}
	}
	@EmbeddedId
	private Id id = new Id();
	@Column(name = "LEFT_PERM")
	private Permission left;
	@Column(name = "RIGHT_PERM")
	private Permission right;
	@ManyToOne
	@JoinColumn(name = "user1_ID",
			insertable = false,
			updatable = false)
	private User user1;
	@ManyToOne
	@JoinColumn(name = "user2_ID",
			insertable = false,
			updatable = false)
	private User user2;
	public User2User() {}
	public User2User(User user1, User user2, Permission left, Permission right){
		this.id.user1Id = user1.getId();
		this.id.user2Id = user2.getId();
		this.left = left;
		this.right = right;
		this.user1 = user1;
		this.user2 = user2;
		this.user1.getUsers2().add(this);
		this.user2.getUsers1().add(this);
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getuser2() {
		return user2;
	}
	public void setuser2(User user2) {
		this.user2 = user2;
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public void setLeft(Permission left) {
		this.left = left;
	}
	public void setRight(Permission right) {
		this.right = right;
	}
	public Permission getLeft(){
		return this.left;
	}
	public Permission getRight(){
		return this.right;
	}

}