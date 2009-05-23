package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactEmail;

public class ContactEmailDAOHibernate extends GenericHibernateDAO<ContactEmail, Long>
		implements ContactEmailDAO {

	public void clear() {
		super.clear();
	}

	public List<ContactEmail> findAll() {
		return super.findAll();
	}

	public List<ContactEmail> findByExample(ContactEmail exampleInstance, String...excludeProperty) {
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactEmail findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public ContactEmail makePersistent(ContactEmail entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactEmail entity) {
		super.makeTransient(entity);
	}

}
