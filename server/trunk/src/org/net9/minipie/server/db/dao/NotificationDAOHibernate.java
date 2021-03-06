/**
 * NotificationDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.Notification;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.NotificationStorage;

/**
 * @author Riversand
 * 
 */
public class NotificationDAOHibernate extends
		GenericHibernateDAO<Notification, Long> implements NotificationDAO,
		NotificationStorage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.NotificationDAO#add(org.net9.minipie.server
	 * .db.entity.Notification)
	 */
	public Long add(NotificationData notificationData) {
		Notification notification = new Notification();
		notification.setType(notificationData.getType());
		UserDAOHibernate udh = new UserDAOHibernate();
		User sender = null;
		try {
			sender = udh.findById(notificationData.getSenderId());
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no sender with userId: "
					+ notificationData.getSenderId());
		}
		User receiver = null;
		try {
			receiver = udh.findById(notificationData.getReceiverId());
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no receiver with userId: "
					+ notificationData.getReceiverId());
		}
		System.out.println("add notification");
		notification.setSender(sender);
		notification.setReceiver(receiver);
		notification.setContent(notificationData.getContent());
		begin();
		makePersistent(notification);
		commit();
		Long id = notification.getId();
		sender.getSentNotification().add(notification);
		udh.begin();
		udh.makePersistent(sender);
		udh.commit();
		receiver.getReceivedNotification().add(notification);
		udh.begin();
		udh.makePersistent(receiver);
		udh.commit();
		if (notificationData.getGroupId() != 0L) {
			GroupDAOHibernate gdh = new GroupDAOHibernate();
			try {
				Group group = gdh.findById(new Long(notificationData
						.getGroupId()));
				System.out.println(notificationData.getGroupId());
				notification.setGroup(group);
			} catch (ObjectNotFoundException e) {
				throw new NotFoundException("there is no group with id"
						+ notificationData.getGroupId());
			}
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.NotificationDAO#del(java.lang.Long)
	 */
	public void del(Long notificationId) {
		Notification notification = null;
		try {
			notification = findById(notificationId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no notification with id: "
					+ notificationId);
		}
		begin();
		makeTransient(notification);
		commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.NotificationDAO#selectNotification(java
	 * .lang.Long)
	 */
	public NotificationData selectNotification(Long notificationId) {
		Notification notification = null;
		try {
			notification = findById(notificationId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no notification with id: "
					+ notificationId);
		}
		try {
			NotificationData notificationData = new NotificationData(
					notificationId, notification.getSender().getId(),
					(notification.getReceiver() != null) ? notification
							.getReceiver().getId() : 0L, (notification
							.getGroup() != null) ? notification.getGroup()
							.getId() : 0L, notification.getContent(),
					notification.getType());
			return notificationData;
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.NotificationDAO#selectReceiver(java.lang
	 * .Long)
	 */
	public Collection<NotificationData> selectReceiver(Long receiverId) {
		Criterion criterion = Restrictions.eq("receiver.id", receiverId);
		List<Notification> notifications = null;
		try {
			notifications = findByCriteria(criterion);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(
					"there is no notification with receiverid: " + receiverId);
		}
		if (notifications.isEmpty()) {
			throw new NotFoundException(
					"there is no notification with receiverid: " + receiverId);
		}
		List<NotificationData> result = new ArrayList<NotificationData>();
		Iterator<Notification> iter = notifications.iterator();
		while (iter.hasNext()) {
			Notification notification = iter.next();
			try {
				result.add(new NotificationData(notification.getId(),
						notification.getSender().getId(), (notification
								.getReceiver() != null) ? notification
								.getReceiver().getId() : 0L, (notification
								.getGroup() != null) ? notification.getGroup()
								.getId() : 0L, notification.getContent(),
						notification.getType()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Notification findById(Long id) {
		return super.findById(id, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.net9.minipie.server.logic.storage.NotificationStorage#
	 * selectAdminNotification(java.lang.Long)
	 */
	public Collection<NotificationData> selectAdminNotification(Long groupId) {
		// TODO Auto-generated method stub
		return null;
	}
}
