/**
 * NotificationDAO.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;

import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.db.entity.Notification;

/**
 * @author Riversand
 *
 */
public interface NotificationDAO extends GenericDAO<Notification, Long> {
	public Long add(NotificationData notification);
	public void del(Long notifacationId);
	public Collection<NotificationData> selectReceiver(Long receiverId);
	public NotificationData selectNotification(Long notificationId);
}
