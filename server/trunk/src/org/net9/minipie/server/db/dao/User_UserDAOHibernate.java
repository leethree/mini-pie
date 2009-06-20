/**
 * User_userDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.UserRelation;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.User2User;
import org.net9.minipie.server.db.entity.User2User.Id;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Riversand
 * 
 */
public class User_UserDAOHibernate extends GenericHibernateDAO<User2User, Id>
		implements User_UserDAO, User_UserStorage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#add(java.lang.Long,
	 * java.lang.Long)
	 */
	public void add(Long userId1, Long userId2) {
		UserDAOHibernate udh = new UserDAOHibernate();
		User user1 = null;
		User user2 = null;
		try {
			user1 = udh.findById(userId1);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("Cannt find user with give id "
					+ userId1);
		}
		try {
			user2 = udh.findById(userId2);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("Cannt find user with given id "
					+ userId2);
		}
		User2User bind = new User2User(user1, user2, // only this constructor in
														// user2user
				// gives a primary key. Don't simply use
				// setUser1 or setUser2 methods, otherwise,
				// one get strange errors.
				Permission.TO_CONTACTS, Permission.TO_CONTACTS);
		begin();
		getSession().save(bind);
		commit();
		user1.getUsers2().add(bind);
		user2.getUsers1().add(bind);
		udh.begin();
		udh.makePersistent(user1);
		udh.commit();
		udh.begin();
		udh.makePersistent(user2);
		udh.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#del(java.lang.Long,
	 * java.lang.Long)
	 */
	public void del(Long userId1, Long userId2) {
		Id id = new Id(userId1, userId2);
		User2User bind = null;
		try {
			bind = findById(id);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(
					"cannot find bind between user1 with id: " + userId1
							+ " user2 with id: " + userId2);
		}
		begin();
		makeTransient(bind);
		commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#edit(java.lang.Long,
	 * java.lang.Long, org.net9.minipie.server.data.field.InfoField,
	 * java.lang.Object)
	 */
	public void edit(Long userId1, Long userId2, InfoField attribute,
			Object value) {
		Id id1 = new Id(userId1, userId2);
		User2User bind = null;
		boolean flag = false;
		try {
			bind = findById(id1);
		} catch (ObjectNotFoundException e) {
			flag = true;
		}
		if (flag == true) {
			Id id2 = new Id(userId2, userId1);
			try {
				bind = findById(id2);
			} catch (ObjectNotFoundException e) {
				throw new NotFoundException(
						"cannot find bind between user1 with id: " + userId1
								+ " user2 with id: " + userId2);
			}
		}
		if (attribute == InfoField.LEFTPERMISSION) {
			Permission lPerm = (Permission) value;
			bind.setLeft(lPerm);
		} else if (attribute == InfoField.RIGHTPERMISSION) {
			Permission rPerm = (Permission) value;
			bind.setRight(rPerm);
		}else if(attribute==InfoField.PERMISSION){
			if(!flag){
				bind.setLeft((Permission) value);
			}else{
				bind.setRight((Permission)value);
			}
		}else if (attribute == InfoField.RELATIONSHIP) {
			String relation = (String) value;
			bind.setRelationship(relation);
		}
		begin();
		makePersistent(bind);
		commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.User_UserDAO#selectRelationship(java.lang
	 * .Long, java.lang.Long)
	 */
	public String selectRelationship(Long userId1, Long userId2) {
		Id id1 = new Id(userId1, userId2);
		User2User bind = null;
		boolean flag = false;
		try {
			bind = findById(id1);
		} catch (ObjectNotFoundException e) {
			flag = true;
		}
		if (flag == true) {
			Id id2 = new Id(userId2, userId1);
			try {
				bind = findById(id2);
			} catch (ObjectNotFoundException e) {
				throw new NotFoundException(
						"cannot find bind between user1 with id: " + userId1
								+ " user2 with id: " + userId2);
			}
		}
		String relationship = bind.getRelationship();
		return relationship;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.User_UserDAO#selectSharedUser(java.lang
	 * .Long, org.net9.minipie.server.data.field.Permission)
	 */
	public Collection<CommonListEntry> selectSharedUser(Long userId,
			Permission permission) {
		Criterion criterion1 = Restrictions.eq("user1.id", userId);
		Criterion criterion2 = Restrictions.eq("left", permission);
		Criterion criterion3 = Restrictions.eq("user2.id", userId);
		Criterion criterion4 = Restrictions.eq("right", permission);
		List<User2User> binds1 = null;
		List<User2User> binds2 = null;
		binds1 = findByCriteria(criterion1, criterion2);
		binds2 = findByCriteria(criterion3, criterion4);
		if (binds1.isEmpty() && binds2.isEmpty()) {
			throw new NotFoundException("there is no shared user for user: "
					+ userId);
		}
		List<CommonListEntry> result = new ArrayList<CommonListEntry>();
		if (binds1 != null) {
			Iterator<User2User> iter1 = binds1.iterator();
			while (iter1.hasNext()) {
				User user2 = iter1.next().getuser2();
				try {
					result.add(new CommonListEntry(user2.getId(), user2
							.getUserName(), user2.getImageURL()));
				} catch (DataFormatException e) {
					throw new ServerErrorException(e.getMessage());
				}
			}
		}
		if (binds2 != null) {
			Iterator<User2User> iter2 = binds2.iterator();
			while (iter2.hasNext()) {
				User user1 = iter2.next().getUser1();
				try {
					result.add(new CommonListEntry(user1.getId(), user1
							.getUserName(), user1.getImageURL()));
				} catch (DataFormatException e) {
					throw new ServerErrorException(e.getMessage());
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public User2User findById(Id id) {
		return super.findById(id, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.User_UserStorage#selectMyUserContact
	 * (java.lang.Long)
	 */
	public Collection<UserRelation> selectMyUserContact(Long id) {
		Criterion criterion1 = Restrictions.eq("user1.id", id);
		List<User2User> binds1 = null;
		try {
			binds1 = findByCriteria(criterion1);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no user1 with id: " + id);
		}
		Criterion criterion2 = Restrictions.eq("user2.id", id);
		List<User2User> binds2 = null;
		try {
			binds2 = findByCriteria(criterion2);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("there is no user2 with id: " + id);
		}
		if (binds1.isEmpty() && binds2.isEmpty()) {
			throw new NotFoundException(
					"there is no relationship between user with id: " + id);
		}
		List<UserRelation> result = new ArrayList<UserRelation>();
		Iterator<User2User> iter1 = binds1.iterator();
		while (iter1.hasNext()) {
			User2User bind = iter1.next();
			result.add(new UserRelation(bind.getuser2().getId(), bind
					.getRelationship(), bind.getRight()));
		}
		Iterator<User2User> iter2 = binds2.iterator();
		while (iter2.hasNext()) {
			User2User bind = iter2.next();
			result.add(new UserRelation(bind.getUser1().getId(), bind
					.getRelationship(), bind.getRight()));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.User_UserStorage#selectPermission
	 * (java.lang.Long, java.lang.Long)
	 */
	public Permission selectPermission(Long userId1, Long userId2) {
		Id id1 = new Id(userId1, userId2);
		User2User bind = null;
		boolean flag = false;
		try {
			bind = findById(id1);
			return bind.getLeft();
		} catch (ObjectNotFoundException e) {
			flag = true;
		}
		if (flag) {
			Id id2 = new Id(userId2, userId1);
			try {
				bind = findById(id2);
				return bind.getRight();
			} catch (ObjectNotFoundException e) {
				throw new NotFoundException(
						"there is no such bind with user id " + userId1 + " "
								+ userId2);
			}
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.logic.storage.User_UserStorage#getRelations()
	 */
	public Map<Long, Long> getRelations() {
		Map<Long, Long> result = new TreeMap<Long, Long>();
		Collection<User2User> relations = null;
		try {
			relations = findByCriteria();
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("cannot find any relationships.");
		}
		for (User2User user2User : relations) {
			result.put(user2User.getUser1().getId(), user2User.getuser2()
					.getId());
		}
		return result;
	}
}
