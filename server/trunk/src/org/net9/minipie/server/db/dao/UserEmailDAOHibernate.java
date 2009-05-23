package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.UserEmail;

public class UserEmailDAOHibernate extends GenericHibernateDAO<UserEmail, Long> implements
		UserEmailDAO {

	public void clear() {
		super.clear();
	}

	public List<UserEmail> findAll() {
		return super.findAll();
	}

	public List<UserEmail> findByExample(UserEmail exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public UserEmail findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();

	}

	public UserEmail makePersistent(UserEmail entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(UserEmail entity) {
		super.makeTransient(entity);
	}

}
