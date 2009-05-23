package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.UserIM;

public class UserIMDAOHibernate extends GenericHibernateDAO<UserIM, Long> implements
		UserIMDAO {

	public void clear() {
		super.clear();
	}

	public List<UserIM> findAll() {
		return super.findAll();
	}

	public List<UserIM> findByExample(UserIM exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public UserIM findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();
	}

	public UserIM makePersistent(UserIM entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(UserIM entity) {
		super.makeTransient(entity);
	}

}
