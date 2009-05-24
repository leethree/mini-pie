/**
 * HibernateDAOFactory.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.GroupDAOHibernate;
import org.net9.minipie.server.db.dao.Group_UserDAOHibernate;
import org.net9.minipie.server.db.dao.NotificationDAOHibernate;
import org.net9.minipie.server.db.dao.TagDAOHibernate;
import org.net9.minipie.server.db.dao.Tag_ContactDAOHibernate;
import org.net9.minipie.server.db.dao.Tag_UserDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.dao.User_UserDAOHibernate;
import org.net9.minipie.server.db.util.HibernateSessionFactory;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.GroupStorage;
import org.net9.minipie.server.logic.storage.Group_UserStorage;
import org.net9.minipie.server.logic.storage.NotificationStorage;
import org.net9.minipie.server.logic.storage.StorageFactory;
import org.net9.minipie.server.logic.storage.TagStorage;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Riversand
 * 
 */
public class HibernateDAOFactory implements StorageFactory {
	private ContactDAOHibernate contactDAOHibernate;
	private UserDAOHibernate userDAOHibernate;
	private NotificationDAOHibernate notificationDAOHibernate;
	private TagDAOHibernate tagDAOHibernate;
	private Tag_ContactDAOHibernate tagContactDAOHibernate;
	private Tag_UserDAOHibernate tagUserDAOHibernate;
	private User_UserDAOHibernate user_userDAOHibernate;
	private GroupDAOHibernate groupDAOHibernate;
	private Group_UserDAOHibernate groupUserDAOHibernate;
	
	/**
	 * Constructor which is used for checking whether db connection has been
	 * established.
	 */
	public HibernateDAOFactory() {
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.getTransaction().commit();
			contactDAOHibernate = new ContactDAOHibernate();
			userDAOHibernate = new UserDAOHibernate();
			notificationDAOHibernate = new NotificationDAOHibernate();
			tagDAOHibernate = new TagDAOHibernate();
			tagContactDAOHibernate = new Tag_ContactDAOHibernate();
			tagUserDAOHibernate = new Tag_UserDAOHibernate();
			user_userDAOHibernate = new User_UserDAOHibernate();
			groupDAOHibernate = new GroupDAOHibernate();
			groupUserDAOHibernate = new Group_UserDAOHibernate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServerErrorException(
					"Hibernate factory initializing failed.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(
					"Unexpected database initializing error.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getContactStorage()
	 */
	public ContactStorage getContactStorage() {
		return contactDAOHibernate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getUserStorage()
	 */
	public UserStorage getUserStorage() {
		return userDAOHibernate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getNotifacationStorage
	 * ()
	 */
	public NotificationStorage getNotifacationStorage() {
		return notificationDAOHibernate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.storage.StorageFactory#getTagStorage()
	 */
	public TagStorage getTagStorage() {
		return tagDAOHibernate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getTag_ContactStorage
	 * ()
	 */
	public Tag_ContactStorage getTag_ContactStorage() {
		return tagContactDAOHibernate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getTag_UserStorage()
	 */
	public Tag_UserStorage getTag_UserStorage() {
		return tagUserDAOHibernate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getUser_UserStorage
	 * ()
	 */
	public User_UserStorage getUser_UserStorage() {
		return user_userDAOHibernate;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.StorageFactory#getGroupStorage()
	 */
	public GroupStorage getGroupStorage() {
		return groupDAOHibernate;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.StorageFactory#getGroup_UserStorage()
	 */
	public Group_UserStorage getGroup_UserStorage() {
		return groupUserDAOHibernate;
	}
}
