package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.UserAddress;

public class UserAddressDAOHibernate extends GenericHibernateDAO<UserAddress, Long>
		implements UserAddressDAO {

	public void clear() {
		super.clear();

	}

	public List<UserAddress> findAll() {
		return super.findAll();
	}

	public List<UserAddress> findByExample(UserAddress exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public UserAddress findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public UserAddress makePersistent(UserAddress entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(UserAddress entity) {
		super.makeTransient(entity);
	}

}
