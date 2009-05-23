/**
 * NotificationList.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author LeeThree
 *
 */
@XmlRootElement(name = "notifications")
public class NotificationList {
	private Collection<NotificationXml> notifications;
	
	/**
	 * Constructor
	 */
	public NotificationList() {
	}
	

	public NotificationList(Collection<NotificationXml> list, URI uri) {
		for (NotificationXml notification : list) {
			notification.setUri(UriBuilder.fromUri(uri).path(notification.getId() + "/")
					.build());
		}
		this.notifications = list;
	}
	
	/**
	 * @return the notifications
	 */
	@XmlElement(name = "notification")
	public Collection<NotificationXml> getNotifications() {
		return notifications;
	}
}
