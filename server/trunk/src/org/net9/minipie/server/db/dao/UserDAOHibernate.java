package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.db.entity.ContactEmail;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.UserAddress;
import org.net9.minipie.server.db.entity.UserEmail;
import org.net9.minipie.server.db.entity.UserIM;
import org.net9.minipie.server.db.entity.UserPhoneNo;
import org.net9.minipie.server.db.entity.UserURL;
import org.net9.minipie.server.db.entity.enums.Bool;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.UserStorage;

public class UserDAOHibernate extends GenericHibernateDAO<User, Long> implements
		UserDAO, UserStorage {
	
	public Long add(String name, String pwd, String email) {
		User newUser = new User();
		newUser.setUserName(name);
		newUser.setPassword(pwd);
		newUser.setRegisterEmail(email);
		newUser.setPerm(Permission.TO_CONTACTS);
		begin();
		Long id = makePersistent(newUser).getId();
		commit();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delAddr(java.lang.Long)
	 */
	public void delAddr(Long id) {
		try {
			UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
			UserAddress userAddr = uadh.findById(id);
			uadh.begin();
			uadh.makeTransient(userAddr);
			uadh.commit();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannt find address item with give id");
		}
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
		try {
			UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
			UserEmail userEmail = uedh.findById(id);
			uedh.begin();
			uedh.makeTransient(userEmail);
			uedh.commit();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannt find email item with give id");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delIM(java.lang.Long)
	 */
	public void delIM(Long id) {
		try {
			UserIMDAOHibernate uidh = new UserIMDAOHibernate();
			UserIM userIM = uidh.findById(id);
			uidh.begin();
			uidh.makeTransient(userIM);
			uidh.commit();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannt find im item with give id");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delTel(java.lang.Long)
	 */
	public void delTel(Long id) {
		try {
			UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
			UserPhoneNo userPhone = updh.findById(id);
			updh.begin();
			updh.makeTransient(userPhone);
			updh.commit();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannt find phone item with give id");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#delURL(java.lang.Long)
	 */
	public void delURL(Long id) {
		try {
			UserURLDAOHibernate uudh = new UserURLDAOHibernate();
			UserURL userURL = uudh.findById(id);
			uudh.begin();
			uudh.makeTransient(userURL);
			uudh.commit();
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("Cannt find url item with give id");
		}
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectAddr(java.lang.Long)
	 */
	public List<AddressData> selectAddr(Long userId) {
		try {
			UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
			Criterion criterion = Restrictions.eq("user.id", userId);
			List<UserAddress> result = uadh.findByCriteria(criterion);
			List<AddressData> selectedResult = new ArrayList<AddressData>();
			Iterator<UserAddress> iter = result.iterator();
			while (iter.hasNext()) {
				UserAddress userAddr = (UserAddress) iter.next();
				AddressData addressData = new AddressData();
				addressData.setId(userAddr.getId());
				addressData.setValue(userAddr.getFormatted());
				addressData.setType(userAddr.getType());
				if (userAddr.getPrimary() == Bool.TRUE)
					addressData.setPrimary(true);
				else
					addressData.setPrimary(false);
				addressData.setPermission(Permission.TO_CONTACTS);
				selectedResult.add(addressData);
			}
			return selectedResult;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no user with ID: "
					+ userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
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
	public Long addAddr(Long userId, AddressData addressData) {
		User user = findById(userId);
		UserAddress userAddr = new UserAddress();
		userAddr.setFormatted(addressData.getValue());
		userAddr.setType(addressData.getType());
		if (addressData.isPrimary() == true)
			userAddr.setPrimary(Bool.TRUE);
		else
			userAddr.setPrimary(Bool.FALSE);
		userAddr.setPerm(addressData.getPermission());
		userAddr.setUser(user);
		UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
		uadh.begin();
		Long id = uadh.makePersistent(userAddr).getId();
		uadh.commit();
		begin();
		user.getAddresses().add(userAddr);
		makePersistent(user);
		commit();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addEmail(java.lang.Long, org.net9.minipie.server.data.entity.EmailData)
	 */
	public Long addEmail(Long userId, EmailData emailData) {
		User user = findById(userId);
		UserEmail userEmail = new UserEmail();
		userEmail.setValue(emailData.getValue());
		userEmail.setType(emailData.getType());
		if (emailData.isPrimary() == true)
			userEmail.setPrimary(Bool.TRUE);
		else
			userEmail.setPrimary(Bool.FALSE);
		userEmail.setPerm(emailData.getPermission());
		userEmail.setUser(user);
		UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
		uedh.begin();
		Long id = uedh.makePersistent(userEmail).getId();
		uedh.commit();
		begin();
		user.getEmails().add(userEmail);
		makePersistent(user);
		commit();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addIM(java.lang.Long, org.net9.minipie.server.data.entity.IMData)
	 */
	public Long addIM(Long userId, IMData imData) {
		User user = findById(userId);
		UserIM userIM = new UserIM();
		userIM.setValue(imData.getValue());
		userIM.setType(imData.getType());
		if (imData.isPrimary() == true)
			userIM.setPrimary(Bool.TRUE);
		else
			userIM.setPrimary(Bool.FALSE);
		userIM.setPerm(imData.getPermission());
		userIM.setUser(user);
		UserIMDAOHibernate uidh = new UserIMDAOHibernate();
		uidh.begin();
		Long id = uidh.makePersistent(userIM).getId();
		uidh.commit();
		begin();
		user.getIms().add(userIM);
		makePersistent(user);
		commit();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addTel(java.lang.Long, org.net9.minipie.server.data.entity.PhoneNoData)
	 */
	public Long addTel(Long userId, PhoneNoData phoneNoData) {
		User user = findById(userId);
		UserPhoneNo userPhone = new UserPhoneNo();
		userPhone.setValue(phoneNoData.getValue());
		userPhone.setType(phoneNoData.getType());
		if (phoneNoData.isPrimary() == true)
			userPhone.setPrimary(Bool.TRUE);
		else
			userPhone.setPrimary(Bool.FALSE);
		userPhone.setPerm(phoneNoData.getPermission());
		userPhone.setUser(user);
		UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
		updh.begin();
		Long id = updh.makePersistent(userPhone).getId();
		updh.commit();
		begin();
		user.getPhono().add(userPhone);
		makePersistent(user);
		commit();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#addURL(java.lang.Long, org.net9.minipie.server.data.entity.URLData)
	 */
	public Long addURL(Long userId, URLData urlData) {
		User user = findById(userId);
		UserURL userURL = new UserURL();
		userURL.setValue(urlData.getValue());
		userURL.setType(urlData.getType());
		if (urlData.isPrimary() == true)
			userURL.setPrimary(Bool.TRUE);
		else
			userURL.setPrimary(Bool.FALSE);
		userURL.setPerm(urlData.getPermission());
		userURL.setUser(user);
		UserURLDAOHibernate uudh = new UserURLDAOHibernate();
		uudh.begin();
		Long id = uudh.makePersistent(userURL).getId();
		uudh.commit();
		begin();
		user.getUrl().add(userURL);
		makePersistent(user);
		commit();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editAddr(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editAddr(Long id, InfoField attribute, Object value) {
		UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
		UserAddress userAddr = null;
		try {
			userAddr = uadh.findById(id);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannt find address " + attribute
					+ " item with give id");
		}
		if (attribute==InfoField.VALUE) {
			String addr = (String) value;
			userAddr.setFormatted(addr);
		} else if (attribute==InfoField.TYPE) {
			String type = (String) value;
			userAddr.setType(type);
		} else if (attribute==InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userAddr.setPrimary(primary);
		} else if(attribute==InfoField.PERMISSION){
			Permission perm = (Permission) value;
			userAddr.setPerm(perm);
		}
		uadh.begin();
		uadh.makePersistent(userAddr);
		uadh.commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editBasicInfo(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editBasicInfo(Long userId, InfoField attribute, Object value) {
		UserDAOHibernate udh = new UserDAOHibernate();
		User user = null;
		try{
			user = udh.findById(userId);
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannot find user info "+ attribute
					+ " item with given userId");
		}
		if(attribute==InfoField.NAME){
			String userName = (String) value;
			user.setUserName(userName);
		}
		udh.begin();
		udh.makePersistent(user);
		udh.commit();
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.UserStorage#editEmail(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editEmail(Long id, InfoField attribute, Object value) {
		UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
		UserEmail userEmail = null;
		try {
			userEmail = uedh.findById(id);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannt find email " + attribute
					+ " item with give id");
		}
		if (attribute==InfoField.VALUE) {
			String email = (String) value;
			userEmail.setValue(email);
		} else if (attribute==InfoField.TYPE) {
			String type = (String) value;
			userEmail.setType(type);
		} else if (attribute==InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userEmail.setPrimary(primary);
		} else if(attribute==InfoField.PERMISSION){
			Permission perm = (Permission) value;
			userEmail.setPerm(perm);
		}
		uedh.begin();
		uedh.makePersistent(userEmail);
		uedh.commit();
		
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

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#addAddtional(java.lang.Long, java.lang.Object[])
	 */
	public Long addAddtional(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.UserDAO#editAdditional(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editAdditional(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

}
