/**
 * Notifacation.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.constant.NotificationType;

/**
 * @author Seastar
 *
 */
public class Notification {
	private long sendId;
	private long receiverId;
	private String content;
	private NotificationType type;
	/**
	 * Constructor
	 * @param sendId
	 * @param receiverId
	 * @param content
	 * @param type
	 */
	public Notification(long sendId, long receiverId, String content,
			NotificationType type) {
		super();
		this.sendId = sendId;
		this.receiverId = receiverId;
		this.content = content;
		this.type = type;
	}
	/**
	 * @return the sendId
	 */
	public long getSendId() {
		return sendId;
	}
	/**
	 * @param sendId the sendId to set
	 */
	public void setSendId(long sendId) {
		this.sendId = sendId;
	}
	/**
	 * @return the receiverId
	 */
	public long getReceiverId() {
		return receiverId;
	}
	/**
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the type
	 */
	public NotificationType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(NotificationType type) {
		this.type = type;
	}
	
}
