/**
 * HibernateDAOFactory.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db;

import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.StorageFactory;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Riversand
 * 
 */
public class HibernateDAOFactory implements StorageFactory {

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
	// public ContactAddressDAOHibernate getContactAddressStorage(){
	// return new ContactAddressDAOHibernate();
	// }
	// public ContactEmailDAOHibernate getContactEmailStorage(){
	// return new ContactEmailDAOHibernate();
	// }
	// public ContactIMDAOHibernate getContactIMStorage(){
	// return new ContactIMDAOHibernate();
	// }
	// public ContactPhoneDAOHibernate getContactPhoneStorage(){
	// return new ContactPhoneDAOHibernate();
	// }
	// public ContactURLDAOHibernate getContactURLStorage(){
	// return new ContactURLDAOHibernate();
	// }
}