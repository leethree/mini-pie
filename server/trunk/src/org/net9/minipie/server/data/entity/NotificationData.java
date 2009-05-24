/**
 * Notification.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
public class NotificationData {
	private Long id;
	private Long senderId;
	private Long receiverId;
	private String content;
	private NotificationType type;

	/**
	 * Constructor
	 * 
	 * @param senderId
	 * @param receiverId
	 * @param content
	 * @param type
	 * @throws DataFormatException
	 */
	public NotificationData(Long id,Long senderId, Long receiverId, String content,
			NotificationType type) throws DataFormatException {
		setId(id);
		setSenderId(senderId);
		setReceiverId(receiverId);
		setContent(content);
		setType(type);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 * @throws DataFormatException 
	 */
	public void setId(Long id) throws DataFormatException {
		this.id = Formatter.checkId(id);
	}
	/**
	 * @return the senderId
	 */
	public Long getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId
	 *            the senderId to set
	 * @throws DataFormatException
	 */
	public void setSenderId(long sendId) throws DataFormatException {
		this.senderId = Formatter.checkId(sendId);
	}

	/**
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	/**
	 * @param receiverId
	 *            the receiverId to set
	 * @throws DataFormatException
	 */
	public void setReceiverId(Long receiverId) throws DataFormatException {
		this.receiverId = Formatter.checkId(receiverId);
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set (nullable)
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
	 * @param type
	 *            the type to set
	 */
	public void setType(NotificationType type) {
		if (type == null)
			throw new ServerErrorException("Notification type should not be null.");
		this.type = type;
	}

}
