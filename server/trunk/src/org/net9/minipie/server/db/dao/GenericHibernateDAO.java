package org.net9.minipie.server.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.net9.minipie.server.db.util.HibernateSessionFactory;
import org.net9.minipie.server.exception.NotFoundException;

public abstract class GenericHibernateDAO <T, Id extends Serializable> implements GenericDAO<T, Id>{
	private Class<T> persistentClass;
	private Session session;
	
	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>)
		( (ParameterizedType) getClass().getGenericSuperclass() )
		.getActualTypeArguments()[0];
	}
	public void setSession(Session s) {
		this.session = s;
	}
	protected Session getSession() {
		if (session == null)
			session = HibernateSessionFactory.getSession();
		return session;
	}
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(Id id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (T) getSession().load(getPersistentClass(), id);
		return entity;
	}
	public List<T> findAll() {
		try{
			return findByCriteria();
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There is no wanted item");
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}
	public T makePersistent(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}
	public void makeTransient(T entity) {
		getSession().delete(entity);
	}
	public void flush() {
		getSession().flush();
	}
	public void clear() {
		getSession().clear();
	}
	public void begin(){
		getSession().beginTransaction();
	}
	public void commit(){
		getSession().getTransaction().commit();
	}
	/**
	* Use this inside subclasses as a convenience method.
	*/
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) throws ObjectNotFoundException{
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

}
