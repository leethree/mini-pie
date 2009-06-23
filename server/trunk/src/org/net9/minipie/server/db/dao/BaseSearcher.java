/**
 * BaseSearcher.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.User;

/**
 * @author Riversand
 *
 */
public abstract class BaseSearcher {
	Collection<User> users;
	Criterion criterion;
	Query query;
	Collection<Contact> contacts;
	Criterion contactCriterion;
	Collection<Group> groups;
	
	protected BaseSearcher(Query query){
		users = new ArrayList<User>();
		contacts = new ArrayList<Contact>();
		groups = new ArrayList<Group>();
		this.query = query;
	}
	
	protected abstract void parseQueryToCriterion();
	
	protected abstract void doSearch();
	
	public void setQuery(Query query) {
		this.query = query;
	}

	public Query getQuery() {
		return this.query;
	}

	public Collection<User> getUsers() {
		return users;
	}

	/**
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}
	
	/**
	 * @return the contacts
	 */
	public Collection<Contact> getContacts() {
		return contacts;
	}
	
	/**
	 * @return the groups
	 */
	public Collection<Group> getGroups() {
		return groups;
	}
}
