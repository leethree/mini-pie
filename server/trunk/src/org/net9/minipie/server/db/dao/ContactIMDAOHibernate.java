package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactIM;

public class ContactIMDAOHibernate extends GenericHibernateDAO<ContactIM, Long> implements
		ContactIMDAO {

	public void clear() {
		super.clear();
	}

	public List<ContactIM> findAll() {
		return super.findAll();
	}

	public List<ContactIM> findByExample(ContactIM exampleInstance, String...excludeProperty) {
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactIM findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public ContactIM makePersistent(ContactIM entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactIM entity) {
		super.makeTransient(entity);
	}

}
