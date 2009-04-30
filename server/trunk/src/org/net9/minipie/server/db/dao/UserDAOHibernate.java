package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.UserAddress;
import org.net9.minipie.server.data.constant.Bool;
import org.net9.minipie.server.data.constant.Permission;

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

	public Long addAddr(Long userId, Object... value) {
		UserAddress userAddr = new UserAddress();
		begin();
		User user = findById(userId);
		int k = 0;
		for(Object obj: value){
			switch(k){
			case 0:
				if(obj!=null){
					String type = (String) obj;
					userAddr.setType(type);
				}
				break;
			case 1:
				if(obj!=null){
					String formatted = (String) obj;
					userAddr.setFormatted(formatted);
				}
				break;
			case 2:
				if(obj!=null){
					String zip = (String) obj;
					userAddr.setZipcode(zip);
				}
				break;
			case 3:
				if(obj!=null){
					Bool primary = (Bool) obj;
					userAddr.setPrimary(primary);
				}
				break;
			case 4:
				if(obj!=null){
					Permission perm = (Permission) obj;
					userAddr.setPerm(perm);
				}
				break;
			default: break;
			}
			k++;
		}
		user.getAddresses().add(userAddr);
		userAddr.setUser(user);
		super.makePersistent(user);
		commit();
		UserAddressDAOHibernate dao = new UserAddressDAOHibernate();
		dao.begin();
		Long addrId = dao.makePersistent(userAddr).getId();
		dao.commit();
		return addrId;
	}

	public Long addAddtional(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addEmail(Long userId, Object... value) {
		
		return null;
	}

	public Long addIM(Long userId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addTel(Long userId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addURL(Long userId, Object... value) {
		// TODO Auto-generated method stub
		return null;
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

	public List<Object[]> selectAddr(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectAddtional(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectBasicInfo(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectEmail(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectIM(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectTel(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectURL(Long userId) {
		// TODO Auto-generated method stub
		return null;
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
