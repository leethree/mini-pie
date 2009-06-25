/**
 * GroupDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.ObjectNotFoundException;
import org.net9.minipie.server.data.entity.GroupEntity;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.GroupStorage;

/**
 * @author Riversand
 *
 */
public class GroupDAOHibernate extends GenericHibernateDAO<Group, Long> implements
		GroupDAO, GroupStorage {

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GroupDAO#changePermission(java.lang.Long, org.net9.minipie.server.data.field.Permission)
	 */
	public void changePermission(Long groupId, Permission perm) {
		Group group = null;
		try{
			group = findById(groupId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no group with id: "+groupId);
		}	
		group.setPerm(perm);
		begin();
		makePersistent(group);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GroupDAO#createGroup(java.lang.String, java.lang.Long)
	 */
	public Long createGroup(String name, Long userId) {
		Group group = new Group();
		group.setGroupName(name);
		UserDAOHibernate udh = new UserDAOHibernate();
		User user = null;
		try{
			user = udh.findById(userId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no user with userId: "+ userId);
		}
		group.setCreatorName(user.getUserName());
		group.setCreatorId(userId);
		group.setPerm(Permission.TO_EVERYONE);
		begin();
		makePersistent(group);
		commit();
		return group.getId();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GroupDAO#dispand(java.lang.Long)
	 */
	public void disband(Long groupId) {
		Group group = null;
		try{
			group = findById(groupId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no group with groupId: "+groupId);
		}
		begin();
		makeTransient(group);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GroupDAO#editGroup(java.lang.Long, org.net9.minipie.server.data.field.InfoField, java.lang.String)
	 */
	public void editGroup(Long groupId, InfoField attr, Object value) {
		Group group = null;
		try{
			group = findById(groupId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no group with groupId: "+groupId);
		}
		if(attr==InfoField.NAME){
			String name = (String) value;
			group.setGroupName(name);
		}else if(attr==InfoField.DESCRIPTION){
			String description = (String)value;
			group.setDescription(description);
		}else if(attr==InfoField.PERMISSION){
			Permission perm = (Permission) value;
			group.setPerm(perm);
		}
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GroupDAO#selectGroup(java.lang.Long)
	 */
	public GroupEntity selectGroup(Long groupId) {
		Group group = null;
		try{
			group = findById(groupId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no group with groupId: "+ groupId);
		}
		try {
			return new GroupEntity(group.getId(), group.getGroupName(), group.getDescription(), 
					group.getCreatorId().longValue(), group.getCreatorName(), group.getPerm());
		} catch (DataFormatException e) {
			throw new ServerErrorException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Group findById(Long id) {
		return super.findById(id, true);
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GroupDAO#searchGroup(java.util.Collection)
	 */
	public Collection<GroupEntity> searchGroup(Collection<Query> queries) {
		Collection<GroupEntity> searchResult = new ArrayList<GroupEntity>();
		for (Query query : queries) {
			GroupSearcher searcher = new GroupSearcher(query);
			Collection<Group> groups = searcher.getGroups();
			for (Group group : groups) {
				try {
					searchResult.add(new GroupEntity(group.getId().longValue(), group.getGroupName(),
							group.getDescription(), group.getCreatorId(), group.getCreatorName(),
							group.getPerm()));
				} catch (DataFormatException e) {
					throw new ServerErrorException(e.getMessage());
				}
			}
		}
		return null;
	}

}
