package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.UserURL;

public class UserURLDAOHibernate extends GenericHibernateDAO<UserURL, Long> implements
		UserURLDAO {

	public void clear() {
		super.clear();
	}

	public List<UserURL> findAll() {
		return super.findAll();
	}

	public List<UserURL> findByExample(UserURL exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public UserURL findById(Long id) {
		return super.findById(id, true);
	}

	public void flush() {
		super.flush();

	}

	public UserURL makePersistent(UserURL entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(UserURL entity) {
		super.makeTransient(entity);
	}

}
