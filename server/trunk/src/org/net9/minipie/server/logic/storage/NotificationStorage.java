/**
 * NotifacationStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.NotificationData;

/**
 * @author Seastar
 *
 */
public interface NotificationStorage {
	public Long add(NotificationData notification);
	public void del(Long notifacationId);
	public Collection<NotificationData> selectReceiver(Long receiverId);
	public NotificationData selectNotification(Long notificationId);
}
