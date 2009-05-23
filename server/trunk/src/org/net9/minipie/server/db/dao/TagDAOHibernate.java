/**
 * TagDAOHibernate.java
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
import org.net9.minipie.server.data.storage.TagData;
import org.net9.minipie.server.db.entity.Tag;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.TagStorage;

/**
 * @author Riversand
 *
 */
public class TagDAOHibernate extends GenericHibernateDAO<Tag, Long> implements
		TagDAO, TagStorage {

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.TagDAO#addTag(java.lang.Long, java.lang.String)
	 */
	public Long addTag(Long userId, String tagName) {
		User user = null;
		UserDAOHibernate udh = new UserDAOHibernate();
		try{
			user = udh.findById(userId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no user with id: "+userId);
		}
		Tag tag = new Tag();
		tag.setOwner(user);
		tag.setTagName(tagName);
		begin();
		makePersistent(tag);
		commit();
		user.getOwnedTags().add(tag);
		udh.begin();
		udh.makePersistent(user);
		udh.commit();
		Long id = tag.getId();
		return id;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.TagDAO#editTag(java.lang.Long, java.lang.String)
	 */
	public void editTag(Long tagId, String newName) {
		Tag tag = null;
		try{
			tag = findById(tagId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag with id: "+tagId);
		}
		tag.setTagName(newName);
		begin();
		makePersistent(tag);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.TagDAO#removeTag(java.lang.Long)
	 */
	public void removeTag(Long tagId) {
		Tag tag = null;
		try{
			tag = findById(tagId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag with id: "+tagId);
		}
		begin();
		makeTransient(tag);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.TagDAO#selectAllTags(java.lang.Long)
	 */
	public Collection<TagEntry> selectAllTags(Long userId) {
		Criterion criterion  = Restrictions.eq("owner.id", userId);
		List<Tag> tags = findByCriteria(criterion);
		if(tags.isEmpty()){
			throw new NotFoundException("user with userId: "+userId+" does not have tags");
		}
		Iterator<Tag> iter = tags.iterator();
		List<TagEntry> result = new ArrayList<TagEntry>();
		while(iter.hasNext()){
			Tag tag = iter.next();
			try {
				result.add(new TagEntry(tag.getId(), tag.getTagName()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.TagDAO#selectId(java.lang.Long, java.lang.String)
	 */
	public Long selectId(Long userId, String tagName) {
		Criterion criterion1 = Restrictions.eq("owner.id", userId);
		Criterion criterion2 = Restrictions.eq("tagName", tagName);
		List<Tag> tags = findByCriteria(criterion1, criterion2);
		if(tags.isEmpty()){
			throw new NotFoundException("there is no tag matching this condition: ownerid: "+userId+" tagName: "+tagName);
		}
		Iterator<Tag> iter = tags.iterator();
		return iter.next().getId();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.TagDAO#selectName(java.lang.Long)
	 */
	public TagData selectTag(Long tagId) {
		Tag tag = null;
		try{
			tag = findById(tagId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag with id: "+tagId);
		}
		return new TagData(tag.getId(), tag.getTagName(), tag.getOwner().getId());
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Tag findById(Long id) {
		return super.findById(id, true);
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#begin()
	 */
}
