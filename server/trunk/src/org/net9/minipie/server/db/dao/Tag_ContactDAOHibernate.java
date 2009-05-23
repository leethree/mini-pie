/**
 * Tag_ContactDAOHibernate.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.Tag;
import org.net9.minipie.server.db.entity.Tag2Contact;
import org.net9.minipie.server.db.entity.Tag2Contact.Id;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;

/**
 * @author Riversand
 *
 */
public class Tag_ContactDAOHibernate extends GenericHibernateDAO<Tag2Contact, Id>
		implements Tag_ContactDAO, Tag_ContactStorage {

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_ContactDAO#add(java.lang.Long, java.lang.Long)
	 */
	public void add(Long tagId, Long contactId) {
		TagDAOHibernate tdh = new TagDAOHibernate();
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Tag tag = null;
		Contact contact = null;
		try{
			tag = tdh.findById(tagId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag with id: "+tagId);
		}
		try{
			contact = cdh.findById(contactId);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no contact with id: "+contactId);
		}
		Tag2Contact tagContact = new Tag2Contact(tag, contact);
		begin();
		makePersistent(tagContact);
		commit();
		tag.getTaggedContacts().add(tagContact);
		tdh.begin();
		tdh.makePersistent(tag);
		tdh.commit();
		contact.getOwnTags().add(tagContact);
		cdh.begin();
		cdh.makePersistent(contact);
		cdh.commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_ContactDAO#del(java.lang.Long, java.lang.Long)
	 */
	public void del(Long tagId, Long contactId) {
		Id id = new Id(tagId, contactId);
		Tag2Contact tagContact = null;
		try{
			tagContact = findById(id);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag 2 contact with tag id: "
					+tagId+" and with contact id: "+contactId);
		}
		begin();
		makeTransient(tagContact);
		commit();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_ContactDAO#selectTaggedContact(java.lang.Long)
	 */
	public Collection<CommonListEntry> selectTaggedContact(Long tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_ContactDAO#selectTagsOfContact(java.lang.Long)
	 */
	public Collection<TagEntry> selectTagsOfContact(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Tag2Contact findById(Id id) {
		return super.findById(id, false);
	}

}
