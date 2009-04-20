package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.constant.Permission;

public class ContactDAOHibernate extends GenericHibernateDAO<Contact, Long> implements
		ContactDAO {
		
	public Long addAddr(Long contactId, Object[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addAddtional(Long contactId, Object[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addEmail(Long contactId, Object[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addGroupContact(Long groupId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addIM(Long contactId, Object[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addTel(Long contactId, Object[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addURL(Long contactId, Object[] value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addUserContact(Long userId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addUserShadow(Long userId, Long targeted) {
		// TODO Auto-generated method stub
		return null;
	}

	public void del(Long contactId) {
		// TODO Auto-generated method stub

	}

	public void delAddr(Long id) {
		// TODO Auto-generated method stub

	}

	public void delAddtional(Long id) {
		// TODO Auto-generated method stub

	}

	public void delEmail(Long id) {
		// TODO Auto-generated method stub

	}

	public void delIM(Long id) {
		// TODO Auto-generated method stub

	}

	public void delTel(Long id) {
		// TODO Auto-generated method stub

	}

	public void delURL(Long id) {
		// TODO Auto-generated method stub

	}

	public void editAdditional(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public void editAddr(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public void editBasicInfo(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public void editEmail(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public void editIM(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public void editTel(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public void editURL(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub

	}

	public List<Object[]> search(Object... criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectAddr(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectAddtional(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectBasicInfo(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectEmail(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectGroupContact(Long groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectIM(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectOwnerContact(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectShadow(Long ownerId, Long shadowOf) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectSharedContact(Long ownerId, Permission perm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectTel(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectURL(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> findByExample(Contact exampleInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contact findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void flush() {
		// TODO Auto-generated method stub

	}

	public Contact makePersistent(Contact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void makeTransient(Contact entity) {
		// TODO Auto-generated method stub

	}

}
