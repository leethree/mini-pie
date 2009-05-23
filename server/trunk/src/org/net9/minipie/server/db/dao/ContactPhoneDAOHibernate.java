package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.ContactPhoneNo;

public class ContactPhoneDAOHibernate extends GenericHibernateDAO<ContactPhoneNo, Long>
		implements ContactPhoneDAO {

	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	public List<ContactPhoneNo> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public List<ContactPhoneNo> findByExample(ContactPhoneNo exampleInstance, String...excludeProperty) {
		// TODO Auto-generated method stub
		return super.findByExample(exampleInstance, excludeProperty);
	}

	public ContactPhoneNo findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id, true);
	}

	public void flush() {
		// TODO Auto-generated method stub
		super.flush();
	}

	public ContactPhoneNo makePersistent(ContactPhoneNo entity) {
		// TODO Auto-generated method stub
		return super.makePersistent(entity);
	}

	public void makeTransient(ContactPhoneNo entity) {
		// TODO Auto-generated method stub
		super.makeTransient(entity);
	}

}
