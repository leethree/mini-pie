/**
 * NotificationXml.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.NotificationData;

/**
 * @author LeeThree
 * 
 */
@XmlType(name = "notification")
public class NotificationXml {
	private NotificationData notification;
	private URI uri;

	/**
	 * Constructor
	 */
	public NotificationXml() {
	}

	/**
	 * Constructor
	 */
	public NotificationXml(NotificationData data) {
		this.notification = data;
	}

	/**
	 * @return the id
	 */
	@XmlAttribute
	public Long getId() {
		return notification.getId();
	}

	/**
	 * @return the sendId
	 */
	@XmlElement(name = "sender")
	public Long getSenderId() {
		return notification.getSenderId();
	}

	/**
	 * @return the receiverId
	 */
	@XmlElement(name = "receiver")
	public Long getReceiverId() {
		return notification.getReceiverId();
	}

	/**
	 * @return the content
	 */
	@XmlElement(name = "content")
	public String getContent() {
		return notification.getContent();
	}

	/**
	 * @return the type
	 */
	@XmlAttribute(name = "type")
	public String getType() {
		return notification.getType().toString();
	}

	/**
	 * @return the uri
	 */
	@XmlAttribute
	public URI getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}
}
