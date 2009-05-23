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
	private Long sendId;
	private Long receiverId;
	private String content;
	private NotificationType type;

	/**
	 * Constructor
	 * 
	 * @param sendId
	 * @param receiverId
	 * @param content
	 * @param type
	 * @throws DataFormatException
	 */
	public NotificationData(Long sendId, Long receiverId, String content,
			NotificationType type) throws DataFormatException {
		setSendId(sendId);
		setReceiverId(receiverId);
		setContent(content);
		setType(type);
	}

	/**
	 * @return the sendId
	 */
	public Long getSendId() {
		return sendId;
	}

	/**
	 * @param sendId
	 *            the sendId to set
	 * @throws DataFormatException
	 */
	public void setSendId(long sendId) throws DataFormatException {
		this.sendId = Formatter.checkId(sendId);
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
