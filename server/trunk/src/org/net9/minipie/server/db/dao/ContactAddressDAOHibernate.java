package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactAddress;

public class ContactAddressDAOHibernate extends GenericHibernateDAO<ContactAddress, Long>
		implements ContactAddressDAO {

	public void clear() {
		super.clear();
	}

	public List<ContactAddress> findAll() {
		return super.findAll();
	}

	public List<ContactAddress> findByExample(ContactAddress exampleInstance, String...excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public ContactAddress findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public ContactAddress makePersistent(ContactAddress entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactAddress entity) {
		super.makeTransient(entity);
	}

}
