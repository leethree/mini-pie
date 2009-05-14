/**
 * HibernateDAOFactory.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.util.HibernateSessionFactory;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.NotifacationStorage;
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
	public NotifacationStorage getNotifacationStorage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.storage.StorageFactory#getTagStorage()
	 */
	public TagStorage getTagStorage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getTag_ContactStorage
	 * ()
	 */
	public Tag_ContactStorage getTag_ContactStorage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getTag_UserStorage()
	 */
	public Tag_UserStorage getTag_UserStorage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.StorageFactory#getUser_UserStorage
	 * ()
	 */
	public User_UserStorage getUser_UserStorage() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}
