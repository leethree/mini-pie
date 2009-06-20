package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;
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
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.db.entity.Contact;
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

	public Long add(String name, String displayName, String pwd, String email) {
		User newUser = new User();
		newUser.setUserName(name);
		newUser.setDisplayName(displayName);
		newUser.setPassword(pwd);
		newUser.setRegisterEmail(email);
		newUser.setPerm(AddAsContactPermission.CONFIRMED_ONES);
		begin();
		Long id = makePersistent(newUser).getId();
		commit();
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
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
			throw new NotFoundException("Cannt find address item with give id");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#delAddtional(java.lang.Long)
	 */
	public void delAddtional(Long id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
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
			throw new NotFoundException("Cannt find email item with give id");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
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
			throw new NotFoundException("Cannt find im item with give id");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
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
			throw new NotFoundException("Cannt find phone item with give id");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectAddr(java.lang.Long)
	 */
	public List<AddressData> selectAddr(Long userId) {
		try {
			UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
			Criterion criterion = Restrictions.eq("user.id", userId);
			List<UserAddress> result = uadh.findByCriteria(criterion);
			// if(result.isEmpty()){
			// throw new NotFoundException("There's no user with ID: "
			// + userId);
			// }
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
				addressData.setPermission(userAddr.getPerm());
				selectedResult.add(addressData);
			}
			return selectedResult;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no user with ID: " + userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.UserDAO#selectBasicInfo(java.lang.Long)
	 */
	public BasicUser selectBasicInfo(Long userId) {
		try {
			UserDAOHibernate udh = new UserDAOHibernate();
			User user = udh.findById(userId);
			BasicUser result = new BasicUser(user.getId(), user.getUserName(),
					user.getRegisterEmail(), user.getPassword(), user
							.getImageURL(), user.getNickName(), user
							.getDisplayName(), user.getGenderPermission(), user
							.getBirthdayPermission(), user
							.getBirthyearPermission(), user.getGender(), user
							.getBirthday(), user.getNotes(), user.getPerm());
			return result;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no user with ID: " + userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectEmail(java.lang.Long)
	 */
	public List<EmailData> selectEmail(Long userId) {
		try {
			UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
			Criterion criterion = Restrictions.eq("user.id", userId);
			List<UserEmail> result = uedh.findByCriteria(criterion);
			// if(result.isEmpty()){
			// throw new
			// NotFoundException("There's no email whose owner has ID: "
			// + userId);
			// }
			List<EmailData> selectedResult = new ArrayList<EmailData>();
			Iterator<UserEmail> iter = result.iterator();
			while (iter.hasNext()) {
				UserEmail userEmail = (UserEmail) iter.next();
				EmailData emailData = new EmailData();
				emailData.setId(userEmail.getId());
				emailData.setValue(userEmail.getValue());
				emailData.setType(userEmail.getType());
				if (userEmail.getPrimary() == Bool.TRUE)
					emailData.setPrimary(true);
				else
					emailData.setPrimary(false);
				emailData.setPermission(userEmail.getPerm());
				selectedResult.add(emailData);
			}
			return selectedResult;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no user with ID: " + userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectIM(java.lang.Long)
	 */
	public List<IMData> selectIM(Long userId) {
		try {
			UserIMDAOHibernate uidh = new UserIMDAOHibernate();
			Criterion criterion = Restrictions.eq("user.id", userId);
			List<UserIM> result = uidh.findByCriteria(criterion);
			// if(result.isEmpty()){
			// throw new NotFoundException("There's no im whose owner has ID: "
			// + userId);
			// }
			List<IMData> selectedResult = new ArrayList<IMData>();
			Iterator<UserIM> iter = result.iterator();
			while (iter.hasNext()) {
				UserIM userIM = (UserIM) iter.next();
				IMData imData = new IMData();
				imData.setId(userIM.getId());
				imData.setValue(userIM.getValue());
				imData.setType(userIM.getType());
				if (userIM.getPrimary() == Bool.TRUE)
					imData.setPrimary(true);
				else
					imData.setPrimary(false);
				imData.setPermission(userIM.getPerm());
				selectedResult.add(imData);
			}
			return selectedResult;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no user with ID: " + userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectTel(java.lang.Long)
	 */
	public List<PhoneNoData> selectTel(Long userId) {
		try {
			UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
			Criterion criterion = Restrictions.eq("user.id", userId);
			List<UserPhoneNo> result = updh.findByCriteria(criterion);
			// if(result.isEmpty()){
			// throw new
			// NotFoundException("There's no phone whose owner has ID: "
			// + userId);
			// }
			List<PhoneNoData> selectedResult = new ArrayList<PhoneNoData>();
			Iterator<UserPhoneNo> iter = result.iterator();
			while (iter.hasNext()) {
				UserPhoneNo userPhone = (UserPhoneNo) iter.next();
				PhoneNoData phoneData = new PhoneNoData();
				phoneData.setId(userPhone.getId());
				phoneData.setValue(userPhone.getValue());
				phoneData.setType(userPhone.getType());
				if (userPhone.getPrimary() == Bool.TRUE)
					phoneData.setPrimary(true);
				else
					phoneData.setPrimary(false);
				phoneData.setPermission(userPhone.getPerm());
				selectedResult.add(phoneData);
			}
			return selectedResult;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no user with ID: " + userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#selectURL(java.lang.Long)
	 */
	public List<URLData> selectURL(Long userId) {
		try {
			UserURLDAOHibernate uudh = new UserURLDAOHibernate();
			Criterion criterion = Restrictions.eq("user.id", userId);
			List<UserURL> result = uudh.findByCriteria(criterion);
			// if(result.isEmpty()){
			// throw new NotFoundException("There's no url whose owner has ID: "
			// + userId);
			// }
			List<URLData> selectedResult = new ArrayList<URLData>();
			Iterator<UserURL> iter = result.iterator();
			while (iter.hasNext()) {
				UserURL userURL = (UserURL) iter.next();
				URLData urlData = new URLData();
				urlData.setId(userURL.getId());
				urlData.setValue(userURL.getValue());
				urlData.setType(userURL.getType());
				if (userURL.getPrimary() == Bool.TRUE)
					urlData.setPrimary(true);
				else
					urlData.setPrimary(false);
				urlData.setPermission(userURL.getPerm());
				selectedResult.add(urlData);
			}
			return selectedResult;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no contact with ID: " + userId);
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#addAddr(java.lang.Long,
	 * org.net9.minipie.server.data.entity.AddressData)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#addEmail(java.lang.
	 * Long, org.net9.minipie.server.data.entity.EmailData)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#addIM(java.lang.Long,
	 * org.net9.minipie.server.data.entity.IMData)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#addTel(java.lang.Long,
	 * org.net9.minipie.server.data.entity.PhoneNoData)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#addURL(java.lang.Long,
	 * org.net9.minipie.server.data.entity.URLData)
	 */
	public Long addURL(Long userId, URLData urlData) {
		User user = findById(userId);
		UserURL userURL = new UserURL();
		try {
			userURL.setValue(urlData.getValue());
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#editAddr(java.lang.
	 * Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editAddr(Long id, InfoField attribute, Object value) {
		UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
		UserAddress userAddr = null;
		try {
			userAddr = uadh.findById(id);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("Cannt find address " + attribute
					+ " item with give id");
		}
		if (attribute == InfoField.VALUE) {
			String addr = (String) value;
			userAddr.setFormatted(addr);
		} else if (attribute == InfoField.TYPE) {
			String type = (String) value;
			userAddr.setType(type);
		} else if (attribute == InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userAddr.setPrimary(primary);
		} else if (attribute == InfoField.PERMISSION) {
			Permission perm = (Permission) value;
			userAddr.setPerm(perm);
		}
		uadh.begin();
		uadh.makePersistent(userAddr);
		uadh.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#editBasicInfo(java.
	 * lang.Long, org.net9.minipie.server.data.field.InfoField,
	 * java.lang.Object)
	 */
	public void editBasicInfo(Long userId, InfoField attribute, Object value) {
		UserDAOHibernate udh = new UserDAOHibernate();
		User user = null;
		try {
			user = udh.findById(userId);
			if (attribute == InfoField.NAME) {
				String userName = (String) value;
				user.setUserName(userName);
			} else if (attribute == InfoField.REGISTEREMAIL) {
				String registerEmail = (String) value;
				user.setRegisterEmail(registerEmail);
			} else if (attribute == InfoField.PASSWORD) {
				String password = (String) value;
				user.setPassword(password);
			} else if (attribute == InfoField.NICKNAME) {
				String nickname = (String) value;
				user.setNickName(nickname);
			} else if (attribute == InfoField.DISPLAYNAME) {
				String displayName = (String) value;
				user.setDisplayName(displayName);
			} else if (attribute == InfoField.GENDERPERMISSION) {
				Permission gPerm = (Permission) value;
				user.setGenderPermission(gPerm);
			} else if (attribute == InfoField.BIRTHDAYPERMISSION) {
				Permission bdPerm = (Permission) value;
				user.setBirthdayPermission(bdPerm);
			} else if (attribute == InfoField.BIRTHYEARPERMISSION) {
				Permission byPerm = (Permission) value;
				user.setBirthyearPermission(byPerm);
			} else if (attribute == InfoField.GENDER) {
				Gender gender = (Gender) value;
				user.setGender(gender);
			} else if (attribute == InfoField.BIRTHDAY) {
				String birthday = (String) value;
				user.setBirthday(birthday);
			} else if (attribute == InfoField.NOTE) {
				String note = (String) value;
				user.setNotes(note);
			} else if (attribute == InfoField.IMAGE) {
				String imageURL = (String) value;
				user.setImageURL(imageURL);
			}
			udh.begin();
			udh.makePersistent(user);
			udh.commit();
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("Cannt find attribute " + attribute
					+ " item with give id");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#editEmail(java.lang
	 * .Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editEmail(Long id, InfoField attribute, Object value) {
		UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
		UserEmail userEmail = null;
		try {
			userEmail = uedh.findById(id);
		} catch (HibernateException e) {
			throw new NotFoundException("Cannt find email " + attribute
					+ " item with give id");
		}
		if (attribute == InfoField.VALUE) {
			String email = (String) value;
			userEmail.setValue(email);
		} else if (attribute == InfoField.TYPE) {
			String type = (String) value;
			userEmail.setType(type);
		} else if (attribute == InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userEmail.setPrimary(primary);
		} else if (attribute == InfoField.PERMISSION) {
			Permission perm = (Permission) value;
			userEmail.setPerm(perm);
		}
		uedh.begin();
		uedh.makePersistent(userEmail);
		uedh.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#editIM(java.lang.Long,
	 * org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editIM(Long id, InfoField attribute, Object value) {
		UserIMDAOHibernate uidh = new UserIMDAOHibernate();
		UserIM userIM = null;
		try {
			userIM = uidh.findById(id);
		} catch (HibernateException e) {
			throw new NotFoundException("Cannt find im " + attribute
					+ " item with give id");
		}
		if (attribute == InfoField.VALUE) {
			String im = (String) value;
			userIM.setValue(im);
		} else if (attribute == InfoField.TYPE) {
			String type = (String) value;
			userIM.setType(type);
		} else if (attribute == InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userIM.setPrimary(primary);
		} else if (attribute == InfoField.PERMISSION) {
			Permission perm = (Permission) value;
			userIM.setPerm(perm);
		}
		uidh.begin();
		uidh.makePersistent(userIM);
		uidh.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#editTel(java.lang.Long,
	 * org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editTel(Long id, InfoField attribute, Object value) {
		UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
		UserPhoneNo userPhone = null;
		try {
			userPhone = updh.findById(id);
		} catch (HibernateException e) {
			throw new NotFoundException("Cannt find tel " + attribute
					+ " item with give id");
		}
		if (attribute == InfoField.VALUE) {
			String tel = (String) value;
			userPhone.setValue(tel);
		} else if (attribute == InfoField.TYPE) {
			String type = (String) value;
			userPhone.setType(type);
		} else if (attribute == InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userPhone.setPrimary(primary);
		} else if (attribute == InfoField.PERMISSION) {
			Permission perm = (Permission) value;
			userPhone.setPerm(perm);
		}
		updh.begin();
		updh.makePersistent(userPhone);
		updh.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#editURL(java.lang.Long,
	 * org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editURL(Long id, InfoField attribute, Object value) {
		UserURLDAOHibernate uudh = new UserURLDAOHibernate();
		UserURL userURL = null;
		try {
			userURL = uudh.findById(id);
		} catch (HibernateException e) {
			throw new NotFoundException("Cannt find url " + attribute
					+ " item with give id");
		}
		if (attribute == InfoField.VALUE) {
			String url = (String) value;
			userURL.setValue(url);
		} else if (attribute == InfoField.TYPE) {
			String type = (String) value;
			userURL.setType(type);
		} else if (attribute == InfoField.PRIMARY) {
			Bool primary = (Bool) value;
			userURL.setPrimary(primary);
		} else if (attribute == InfoField.PERMISSION) {
			Permission perm = (Permission) value;
			userURL.setPerm(perm);
		}
		uudh.begin();
		uudh.makePersistent(userURL);
		uudh.commit();

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
		return super.findById(id, true);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.UserDAO#addAddtional(java.lang.Long,
	 * java.lang.Object[])
	 */
	public Long addAddtional(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.UserDAO#editAdditional(java.lang.Long,
	 * org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void editAdditional(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#findAddressOwner(long)
	 */
	public Long findAddressOwner(Long addrId) {
		UserAddress userAddr = null;
		UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
		try {
			userAddr = uadh.findById(addrId);
		} catch (org.hibernate.ObjectNotFoundException e) {
			throw new NotFoundException("there is no address with addr id: "
					+ addrId);
		}
		return userAddr.getUser().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#findEmailOwner(long)
	 */
	public Long findEmailOwner(Long emailId) {
		UserEmail userEmail = null;
		UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
		try {
			userEmail = uedh.findById(emailId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no email with email id: "
					+ emailId);
		}
		return userEmail.getUser().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.storage.UserStorage#findIMOwner(long)
	 */
	public Long findIMOwner(Long imId) {
		UserIM userIM = null;
		UserIMDAOHibernate uidh = new UserIMDAOHibernate();
		try {
			userIM = uidh.findById(imId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no im with im id: " + imId);
		}
		return userIM.getUser().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.storage.UserStorage#findTelOwner(long)
	 */
	public Long findTelOwner(Long telId) {
		UserPhoneNo userPhoneNo = null;
		UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
		try {
			userPhoneNo = updh.findById(telId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no phone with tel id: "
					+ telId);
		}
		return userPhoneNo.getUser().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.storage.UserStorage#findURLOwner(long)
	 */
	public Long findURLOwner(Long urlId) {
		UserURL userURL = null;
		UserURLDAOHibernate uudh = new UserURLDAOHibernate();
		try {
			userURL = uudh.findById(urlId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no url with url id: " + urlId);
		}
		return userURL.getUser().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#setAddAsContactPermission
	 * (org.net9.minipie.server.data.field.AddAsContactPermission,
	 * java.lang.Long)
	 */
	public Void setAddAsContactPermission(AddAsContactPermission perm,
			Long userId) {
		User user = null;
		try {
			user = findById(userId);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no user with userId: "
					+ userId);
		}
		user.setPerm(perm);
		begin();
		makePersistent(user);
		commit();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#selectLegalUser(java
	 * .lang.String, java.lang.String)
	 */
	public Long selectLegalUser(String name, String password) {
		Criterion criterion1 = Restrictions.eq("userName", name);
		Criterion criterion2 = Restrictions.eq("password", password);
		List<User> result = findByCriteria(criterion1, criterion2);
		if (result.isEmpty()) {
			throw new NotFoundException("illegal userName or password");
		}
		Iterator<User> iter = result.iterator();
		return iter.next().getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#selectUserPassword(
	 * java.lang.String)
	 */
	public String selectUserPassword(String name) {
		Criterion criterion = Restrictions.eq("userName", name);
		List<User> user = findByCriteria(criterion);
		if (user.isEmpty()) {
			throw new NotFoundException("there is no user with userName: "
					+ name);
		}
		Iterator<User> iter = user.iterator();
		return iter.next().getPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#searchAllUser(java.
	 * util.Collection)
	 */
	public Collection<BasicUser> searchAllUser(Collection<Query> queries) {
		Collection<BasicUser> searchResults = new ArrayList<BasicUser>();
		for (Query query : queries) {
			AllSearcher searcher = new AllSearcher(query);
			Collection<User> users = searcher.getUsers();
			for (User user : users) {
				try {
					searchResults.add(new BasicUser(user.getId().longValue(),
							user.getUserName(), user.getRegisterEmail(), user
									.getPassword(), user.getImageURL(), user
									.getNickName(), user.getDisplayName(), user
									.getGenderPermission(), user
									.getBirthdayPermission(), user
									.getBirthyearPermission(),
							user.getGender(), user.getBirthday(), user
									.getNotes(), user.getPerm()));
				} catch (DataFormatException e) {
					throw new ServerErrorException(e.getMessage());
				}
			}
		}
		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.UserStorage#searchMyUserOrContact
	 * (java.lang.Long, java.util.Collection)
	 */
	public Collection<CommonListEntry> searchMyUserOrContact(Long userId,
			Collection<Query> queries) {
		Collection<CommonListEntry> searchResult = new ArrayList<CommonListEntry>();
		for (Query query : queries) {
			LimitedSearcher searcher = new LimitedSearcher(userId, query);
			Collection<User> users = searcher.getUsers();
			Collection<Contact> contacts = searcher.getContacts();
			try {
				for (User user : users) {
					searchResult.add(new CommonListEntry(user.getId()
							.longValue(), user.getUserName(), user
							.getImageURL()));
				}
				for (Contact contact : contacts) {
					searchResult
							.add(new CommonListEntry(contact.getId()
									.longValue(), contact.getName(), contact
									.getImage()));
				}
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return searchResult;
	}

}
