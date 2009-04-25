package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactIM;

public class ContactIMDAOHibernate extends GenericHibernateDAO<ContactIM, Long> implements
		ContactIMDAO {

	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	public List<ContactIM> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public List<ContactIM> findByExample(ContactIM exampleInstance, String...excludeProperty) {
		// TODO Auto-generated method stub
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactIM findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id, false);
	}

	public void flush() {
		// TODO Auto-generated method stub
		super.flush();
	}

	public ContactIM makePersistent(ContactIM entity) {
		// TODO Auto-generated method stub
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactIM entity) {
		// TODO Auto-generated method stub
		super.makePersistent(entity);
	}

}
