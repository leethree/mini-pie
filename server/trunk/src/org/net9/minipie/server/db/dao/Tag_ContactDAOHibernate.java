/**
 * Tag_ContactDAOHibernate.java
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
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.Tag;
import org.net9.minipie.server.db.entity.Tag2Contact;
import org.net9.minipie.server.db.entity.Tag2Contact.Id;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.ServerErrorException;
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
	public Collection<BasicContact> selectTaggedContact(Long tagId) {
		Criterion criterion  = Restrictions.eq("id.tagId", tagId);
		List<Tag2Contact> tagContacts = null;
		try{
			tagContacts = findByCriteria(criterion);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("there is no tag relationship with tagid: "+ tagId);
		}
		Iterator<Tag2Contact> iter = tagContacts.iterator();
		List<BasicContact> result = new ArrayList<BasicContact>();
		while(iter.hasNext()){
			Tag2Contact tagContact = iter.next();
			Contact contact = tagContact.getContact();
			try {
				result.add(new BasicContact(contact.getId(), contact.getName(),
						contact.getImage(), contact.getNickName(), contact.getGender(),
						(contact.getBirthday()!=null)? contact.getBirthday().toString(): null, contact.getNotes(),
						contact.getRelationship(), (contact.getOwner()!=null)? contact.getOwner().getId().longValue(): 0, 
						(contact.getShadowOf()!=null)? contact.getShadowOf().getId().longValue():0, 
						(contact.getGroup()!=null)? contact.getGroup().getId().longValue():0, 
						contact.getPermission()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.Tag_ContactDAO#selectTagsOfContact(java.lang.Long)
	 */
	public Collection<TagEntry> selectTagsOfContact(Long contactId) {
		Criterion criterion = Restrictions.eq("contact.id", contactId);
		Collection<Tag2Contact> tagContacts = null;
		try{
			tagContacts = findByCriteria(criterion);
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("cannot find a tag whose contact id is "+contactId);
		}
		Iterator<Tag2Contact> iter = tagContacts.iterator();
		List<TagEntry> result = new ArrayList<TagEntry>();
		while(iter.hasNext()){
			Tag tag = iter.next().getTag();
			try {
				result.add(new TagEntry(tag.getId(), tag.getTagName()));
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public Tag2Contact findById(Id id) {
		return super.findById(id, true);
	}

}
