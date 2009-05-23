package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactPhoneNo;

public class ContactPhoneDAOHibernate extends GenericHibernateDAO<ContactPhoneNo, Long>
		implements ContactPhoneDAO {

	public void clear() {
		super.clear();
	}

	public List<ContactPhoneNo> findAll() {
		return super.findAll();
	}

	public List<ContactPhoneNo> findByExample(ContactPhoneNo exampleInstance, String...excludeProperty) {
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactPhoneNo findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public ContactPhoneNo makePersistent(ContactPhoneNo entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactPhoneNo entity) {
		super.makeTransient(entity);
	}

}
