package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.db.entity.User;

public class UserDAOHibernate extends GenericHibernateDAO<User, Long> implements
		UserDAO {
	
	public Long add(String name, String pwd, String email) {
		User newUser = new User();
		newUser.setUserName(name);
		newUser.setPassword(pwd);
		newUser.setRegisterEmail(email);
		Long id = makePersistent(newUser).getId();
		flush();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addAddr(java.lang.Long, java.lang.Object[])
	 */
	public Long addAddr(Long userId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addAddtional(java.lang.Long, java.lang.Object[])
	 */
	public Long addAddtional(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addEmail(java.lang.Long, java.lang.Object[])
	 */
	public Long addEmail(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addIM(java.lang.Long, java.lang.Object[])
	 */
	public Long addIM(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addTel(java.lang.Long, java.lang.Object[])
	 */
	public Long addTel(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addURL(java.lang.Long, java.lang.Object[])
	 */
	public Long addURL(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delAddr(java.lang.Long)
	 */
	public void delAddr(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delAddtional(java.lang.Long)
	 */
	public void delAddtional(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delEmail(java.lang.Long)
	 */
	public void delEmail(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delIM(java.lang.Long)
	 */
	public void delIM(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delTel(java.lang.Long)
	 */
	public void delTel(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delURL(java.lang.Long)
	 */
	public void delURL(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editAdditional(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editAdditional(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editAddr(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editAddr(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editBasicInfo(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editBasicInfo(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editEmail(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editEmail(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editIM(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editIM(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editTel(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editTel(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editURL(java.lang.Long, java.lang.String, java.lang.Object)
	 */
	public void editURL(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectAddr(java.lang.Long)
	 */
	public List<AddressData> selectAddr(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectBasicInfo(java.lang.Long)
	 */
	public List<BasicUser> selectBasicInfo(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectEmail(java.lang.Long)
	 */
	public List<EmailData> selectEmail(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectIM(java.lang.Long)
	 */
	public List<IMData> selectIM(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectTel(java.lang.Long)
	 */
	public List<PhoneNoData> selectTel(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectURL(java.lang.Long)
	 */
	public List<URLData> selectURL(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addAddr(java.lang.Long, org.net9.minipie.server.data.entity.AddressData)
	 */
	public Long addAddr(Long userId, AddressData value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addEmail(java.lang.Long, org.net9.minipie.server.data.entity.EmailData)
	 */
	public Long addEmail(Long userId, EmailData value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addIM(java.lang.Long, org.net9.minipie.server.data.entity.IMData)
	 */
	public Long addIM(Long userId, IMData value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addTel(java.lang.Long, org.net9.minipie.server.data.entity.PhoneNoData)
	 */
	public Long addTel(Long userId, PhoneNoData value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addURL(java.lang.Long, org.net9.minipie.server.data.entity.URLData)
	 */
	public Long addURL(Long userId, URLData value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editAddr(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editAddr(Long userId, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editBasicInfo(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editBasicInfo(Long userId, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editEmail(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editEmail(Long userId, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editIM(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editIM(Long userId, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editTel(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editTel(Long userId, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editURL(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editURL(Long userId, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		super.clear();
	}

	public List<User> findAll() {
		return super.findAll();
	}

	public List<User> findByExample(User exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public User findById(Long id) {
		return super.findById(id, false);
	}

	public void flush() {
		super.flush();
	}

	public User makePersistent(User entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(User entity) {
		super.makeTransient(entity);
	}

}
