package org.net9.minipie.server.db.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, Id extends Serializable>{
	T findById(Id id);
	List<T> findAll();
	List<T> findByExample(T exampleInstance, String...excludeProperties);
	T makePersistent(T entity);
	void makeTransient(T entity);
	void flush();
	void clear();
	void begin();
	void commit();
}
