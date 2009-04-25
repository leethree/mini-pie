package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactEmail;

public class ContactEmailDAOHibernate extends GenericHibernateDAO<ContactEmail, Long>
		implements ContactEmailDAO {

	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	public List<ContactEmail> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public List<ContactEmail> findByExample(ContactEmail exampleInstance, String...excludeProperty) {
		// TODO Auto-generated method stub
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactEmail findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id, false);
	}

	public void flush() {
		// TODO Auto-generated method stub
		super.flush();
	}

	public ContactEmail makePersistent(ContactEmail entity) {
		// TODO Auto-generated method stub
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactEmail entity) {
		// TODO Auto-generated method stub
		super.makeTransient(entity);
	}

}
