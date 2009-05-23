package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.UserPhoneNo;

public class UserPhoneDAOHibernate extends GenericHibernateDAO<UserPhoneNo, Long> implements
		UserPhoneDAO {

	public void clear() {
		super.clear();
	}

	public List<UserPhoneNo> findAll() {
		return super.findAll();
	}

	public List<UserPhoneNo> findByExample(UserPhoneNo exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public UserPhoneNo findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public UserPhoneNo makePersistent(UserPhoneNo entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(UserPhoneNo entity) {
		super.makeTransient(entity);
	}

}
