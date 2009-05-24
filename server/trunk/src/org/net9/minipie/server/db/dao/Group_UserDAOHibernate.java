/**
 * Group_UserDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.ObjectNotFoundException;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.Group2User;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.Group2User.Id;
import org.net9.minipie.server.db.entity.enums.Bool;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.Group_UserStorage;

/**
 * @author Riversand
 *
 */
public class Group_UserDAOHibernate extends GenericHibernateDAO<Group2User, Id>
		implements Group_UserDAO, Group_UserStorage {
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Group2User findById(Id id) {
		return super.findById(id, true);
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Group_UserDAO#add(java.lang.Long, java.lang.Long)
	 */
	public void add(Long groupId, Long userId) {
		UserDAOHibernate udh = new UserDAOHibernate();
		User user = null;
		try{
			user = udh.findById(userId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no user with useId: "+ userId);
		}
		GroupDAOHibernate gdh = new GroupDAOHibernate();
		Group group = null;
		try{
			group = gdh.findById(groupId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException ("there is no group with groupId: "+groupId);
		}
		Group2User membership = new Group2User(group, user, Bool.FALSE);
		begin();
		makePersistent(membership);
		commit();
		group.getMembers().add(membership);
		gdh.begin();
		gdh.makePersistent(group);
		gdh.commit();
		user.getGroups().add(membership);
		udh.begin();
		udh.makePersistent(user);
		udh.commit();
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Group_UserDAO#remove(java.lang.Long, java.lang.Long)
	 */
	public void remove(Long groupId, Long userId) {
		Group2User membership = null;
		Id id = new Id(groupId, userId);
		try{
			membership = findById(id);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("membership does not exist between group "+groupId+" and user: "+userId);
		}
		begin();
		makeTransient(membership);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Group_UserDAO#selectGroup(java.lang.Long)
	 */
	public Collection<GroupEntry> selectGroup(Long userId) {
		UserDAOHibernate udh = new UserDAOHibernate();
		User user = null;
		try{
			user = udh.findById(userId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no user with userId: "+userId);
		}
		GroupDAOHibernate gdh = new GroupDAOHibernate();
		Collection<GroupEntry> groupEntries = new ArrayList<GroupEntry>();
		for (Group2User membership : user.getGroups()) {
			Long groupId = membership.getGroup().getId();
			Group group = null;
			try{
				group = gdh.findById(groupId);
			}catch(ObjectNotFoundException e){
				throw new NotFoundException("there is no group with groupId: "+groupId);
			}
			try {
				groupEntries.add(new GroupEntry(group.getGroupName(), group.getDescription(),
						group.getCreatorId().longValue(), group.getCreatorName(), group.getPerm()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return groupEntries;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Group_UserDAO#selectMember(java.lang.Long, java.lang.Boolean)
	 */
	public Collection<CommonListEntry> selectMember(Long groupId, Boolean isAdmin) {
		GroupDAOHibernate gdh = new GroupDAOHibernate();
		Group group = null;
		try{
			group = gdh.findById(groupId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no group with groupId: "+groupId);
		}
		Collection<CommonListEntry> members = new ArrayList<CommonListEntry>();
		UserDAOHibernate udh = new UserDAOHibernate();
		for (Group2User membership : group.getMembers()) {
			Long userId = membership.getMember().getId();
			User user = null;
			if((isAdmin && membership.getIsAdmin()==Bool.TRUE)
					|| (!isAdmin && membership.getIsAdmin()==Bool.FALSE)){
				try{
					user = udh.findById(userId);
				}catch(ObjectNotFoundException e){
					throw new NotFoundException("there is no user with userId: "+userId);
				}
				try {
					members.add(new CommonListEntry(user.getId().longValue(), 
							user.getUserName(), user.getImageURL()));
				} catch (DataFormatException e) {
					throw new ServerErrorException(e.getMessage());
				}
			}
		}
		return members;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Group_UserDAO#setAdmin(java.lang.Long, java.lang.Long, java.lang.Boolean)
	 */
	public void setAdmin(Long groupId, Long userId, Boolean isAdmin) {
		Id id = new Id(groupId, userId);
		Group2User membership = null;
		try{
			membership = findById(id);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no membership between group: "+groupId+" and user: "+userId);
		}
		if(isAdmin){
			membership.setIsAdmin(Bool.TRUE);
		}else{
			membership.setIsAdmin(Bool.FALSE);
		}
	}
}
