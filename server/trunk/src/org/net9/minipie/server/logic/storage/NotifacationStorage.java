/**
 * NotifacationStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.Notification;

/**
 * @author Seastar
 *
 */
public interface NotifacationStorage {
	public long add(Notification notification);
	public void del(Long notifacationId);
	public Collection<Notification> selectReceiver(Long receiverId);
	public Notification selectNotification(long notificationId);
}
