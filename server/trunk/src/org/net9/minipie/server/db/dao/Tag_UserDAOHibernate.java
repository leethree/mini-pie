/**
 * Tag_UserDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.db.entity.Tag;
import org.net9.minipie.server.db.entity.Tag2User;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.Tag2User.Id;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.Tag_UserStorage;

/**
 * @author Riversand
 *
 */
public class Tag_UserDAOHibernate extends GenericHibernateDAO<Tag2User, Id> implements
		Tag_UserDAO, Tag_UserStorage {

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_UserDAO#add(java.lang.Long, java.lang.Long)
	 */
	public void add(Long tagId, Long userId) {
		TagDAOHibernate tdh = new TagDAOHibernate();
		UserDAOHibernate udh = new UserDAOHibernate();
		Tag tag = null;
		User user = null;
		try{
			tag = tdh.findById(tagId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag with id: "+tagId);
		}
		try{
			user = udh.findById(userId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no user with id: "+userId);
		}
		Tag2User tagUser = new Tag2User(tag, user);
		begin();
		makePersistent(tagUser);
		commit();
		tag.getTaggedUsers().add(tagUser);
		tdh.begin();
		tdh.makePersistent(tag);
		tdh.commit();
		user.getTags().add(tagUser);
		udh.begin();
		udh.makePersistent(user);
		udh.commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_UserDAO#del(java.lang.Long, java.lang.Long)
	 */
	public void del(Long tagId, Long userId) {
		Id id = new Id(tagId, userId);
		Tag2User tagUser = null;
		try{
			tagUser = findById(id);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag 2 user with tag id: "
					+tagId+" and with user id: "+userId);
		}
		begin();
		makeTransient(tagUser);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_UserDAO#selectTaggedUser(java.lang.Long)
	 */
	public Collection<BasicUser> selectTaggedUser(Long tagId) {
		Criterion criterion  = Restrictions.eq("id.tagId", tagId);
		List<Tag2User> tagUsers = null;
		try{
			tagUsers = findByCriteria(criterion);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag relationship with tagid: "+ tagId);
		}
		Iterator<Tag2User> iter = tagUsers.iterator();
		List<BasicUser> result = new ArrayList<BasicUser>();
		while(iter.hasNext()){
			Tag2User tagUser = iter.next();
			User user = tagUser.getUser();
			try {
				result.add(new BasicUser(user.getId().longValue(), user.getUserName(),
						user.getRegisterEmail(), user.getPassword(), user.getImageURL(),
						user.getNickName(), user.getDisplayName(), user.getGenderPermission(),
						user.getBirthdayPermission(), user.getBirthyearPermission(),
						user.getGender(), user.getBirthday().toString(), user.getNotes(), user.getPerm()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_UserDAO#selectTagsOfUser(java.lang.Long, java.lang.Long)
	 */
	public Collection<TagEntry> selectTagsOfUser(Long userId, Long ownerId) {
		Criterion criterion = Restrictions.eq("user.id", userId);
		List<TagEntry> tags = new ArrayList<TagEntry>();
		List<Tag2User> tagUsers = null;
		try{
			tagUsers = findByCriteria(criterion);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("no tag2user with userid: "+userId+" "
					+"and with tagownerid: "+ownerId);
		}
		Iterator<Tag2User> iter = tagUsers.iterator();
		while(iter.hasNext()){
			Tag tag = iter.next().getTag();
			if(!tag.getOwner().getId().equals(ownerId)){
				continue;
			}
			try {
				tags.add(new TagEntry(tag.getId(), tag.getTagName()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return tags;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Tag2User findById(Id id) {
		return super.findById(id, true);
	}
}
