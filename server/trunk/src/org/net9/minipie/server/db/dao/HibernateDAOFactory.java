/**
 * HibernateDAOFactory.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

/**
 * @author Riversand
 *
 */
public class HibernateDAOFactory {
	public ContactAddressDAOHibernate getContactAddressStorage(){
		return new ContactAddressDAOHibernate();
	}
	public ContactDAOHibernate getContactStorage(){
		return new ContactDAOHibernate();
	}
	public ContactEmailDAOHibernate getContactEmailStorage(){
		return new ContactEmailDAOHibernate();
	}
	public ContactIMDAOHibernate getContactIMStorage(){
		return new ContactIMDAOHibernate();
	}
	public ContactPhoneDAOHibernate getContactPhoneStorage(){
		return new ContactPhoneDAOHibernate();
	}
	public ContactURLDAOHibernate getContactURLStorage(){
		return new ContactURLDAOHibernate();
	}

}
