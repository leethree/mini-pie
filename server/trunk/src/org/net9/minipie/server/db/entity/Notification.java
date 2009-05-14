package org.net9.minipie.server.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.net9.minipie.server.data.field.NotificationType;

@Entity
@Table(name = "NOTIFICATION")
public class Notification {
	@Id
	@GeneratedValue
	@Column(name = "NOTIFICATION_ID")
	private Long id;
	@Column(name = "NOTIFICATION_TYPE")
	private NotificationType type;
	@Column(name = "NOTIFICATION_CONTENT")
	private String content;
	@ManyToOne
	@JoinColumn(name = "SENDER_ID")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID")
	private User receiver;
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public NotificationType getType() {
		return type;
	}
	public void setType(NotificationType type) {
		this.type = type;
	}
}
