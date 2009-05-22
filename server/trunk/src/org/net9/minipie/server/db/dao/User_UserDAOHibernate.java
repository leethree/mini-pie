/**
 * User_userDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;

import org.hibernate.ObjectNotFoundException;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.User2User;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.storage.User_UserStorage;

/**
 * @author Riversand
 *
 */
public class User_UserDAOHibernate extends GenericHibernateDAO<User2User, Long> implements
		User_UserDAO , User_UserStorage{

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#add(java.lang.Long, java.lang.Long)
	 */
	public void add(Long userId1, Long userId2) {
		UserDAOHibernate udh = new UserDAOHibernate();
		User user1 = null;
		User user2 = null;
		try{
			user1 = udh.findById(userId1);
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find user with give id "+ userId1);
		}
		try{
			user2 = udh.findById(userId2);
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find user with given id "+ userId2);
		}
		User2User bind = new User2User(user1, user2,  // only this constructor in user2user
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

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#del(java.lang.Long, java.lang.Long)
	 */
	public void del(Long userId1, Long userId2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#edit(java.lang.Long, java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.Object)
	 */
	public void edit(Long userId1, Long userId2, InfoField attribute,
			Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#selectRelationship(java.lang.Long, java.lang.Long)
	 */
	public String selectRelationship(Long userId1, Long userId2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.User_UserDAO#selectSharedUser(java.lang.Long, org.net9.minipie.server.data.field.Permission)
	 */
	public Collection<CommonListEntry> selectSharedUser(Long userId,
			Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public User2User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
