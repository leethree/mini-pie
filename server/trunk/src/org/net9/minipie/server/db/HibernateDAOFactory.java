/**
 * HibernateDAOFactory.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.NotificationDAOHibernate;
import org.net9.minipie.server.db.dao.TagDAOHibernate;
import org.net9.minipie.server.db.dao.Tag_ContactDAOHibernate;
import org.net9.minipie.server.db.dao.Tag_UserDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.dao.User_UserDAOHibernate;
import org.net9.minipie.server.db.util.HibernateSessionFactory;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.ContactStorage;
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
	/**
	 * Constructor which is used for checking whether db connection has been
	 * established.
	 */
	public HibernateDAOFactory() {
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.getTransaction().commit();
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
		return new ContactDAOHibernate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getUserStorage()
	 */
	public UserStorage getUserStorage() {
		return new UserDAOHibernate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getNotifacationStorage
	 * ()
	 */
	public NotificationStorage getNotifacationStorage() {
		return new NotificationDAOHibernate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.storage.StorageFactory#getTagStorage()
	 */
	public TagStorage getTagStorage() {
		return new TagDAOHibernate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getTag_ContactStorage
	 * ()
	 */
	public Tag_ContactStorage getTag_ContactStorage() {
		return new Tag_ContactDAOHibernate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getTag_UserStorage()
	 */
	public Tag_UserStorage getTag_UserStorage() {
		return new Tag_UserDAOHibernate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getUser_UserStorage
	 * ()
	 */
	public User_UserStorage getUser_UserStorage() {
		return new User_UserDAOHibernate();
	}
}
