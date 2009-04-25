package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactURL;

public class ContactURLDAOHibernate extends GenericHibernateDAO<ContactURL, Long>
		implements ContactURLDAO {

	public void clear() {
		super.clear();
	}

	public List<ContactURL> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public List<ContactURL> findByExample(ContactURL exampleInstance, String...excludeProperty) {
		// TODO Auto-generated method stub
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactURL findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id, false);
	}

	public void flush() {
		super.flush();

	}

	public ContactURL makePersistent(ContactURL entity) {
		// TODO Auto-generated method stub
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactURL entity) {
		// TODO Auto-generated method stub
		super.makeTransient(entity);
	}

}
