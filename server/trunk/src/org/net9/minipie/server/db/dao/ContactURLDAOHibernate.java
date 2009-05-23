package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactURL;

public class ContactURLDAOHibernate extends GenericHibernateDAO<ContactURL, Long>
		implements ContactURLDAO {

	public void clear() {
		super.clear();
	}

	public List<ContactURL> findAll() {
		return super.findAll();
	}

	public List<ContactURL> findByExample(ContactURL exampleInstance, String...excludeProperty) {
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactURL findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();

	}

	public ContactURL makePersistent(ContactURL entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactURL entity) {
		super.makeTransient(entity);
	}

}
