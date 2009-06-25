/**
 * ContactSearcher.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.ContactAddress;
import org.net9.minipie.server.db.entity.ContactEmail;
import org.net9.minipie.server.db.entity.ContactIM;
import org.net9.minipie.server.db.entity.ContactPhoneNo;
import org.net9.minipie.server.db.entity.ContactURL;

/**
 * @author Riversand
 *
 */
public class ContactSearcher extends BaseSearcher {
	
	public ContactSearcher(Query query){
		super(query);
		parseQueryToCriterion();
		doSearch();
	}
	

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.BaseSearcher#doSearch()
	 */
	@Override
	protected void doSearch() {
		InfoType type = query.getType();
		if(type==InfoType.BASIC){
			ContactDAOHibernate cdh = new ContactDAOHibernate();
			List<Contact> contactResult = cdh.findByCriteria(criterion);
			for (Contact contact : contactResult) {
				contacts.add(contact);
			}
		}else if(type==InfoType.ADDRESS){
			ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
			List<ContactAddress> contactAddr = cadh.findByCriteria(criterion);
			for (ContactAddress contactAddress : contactAddr) {
				contacts.add(contactAddress.getContact());
			}
		}else if(type==InfoType.EMAIL){
			ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
			List<ContactEmail> contactEmails = cedh.findByCriteria(criterion);
			for (ContactEmail contactEmail : contactEmails) {
				contacts.add(contactEmail.getContact());
			}
		}else if(type==InfoType.IM){
			ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
			List<ContactIM> contactIms = cidh.findByCriteria(criterion);
			for (ContactIM contactIM : contactIms) {
				contacts.add(contactIM.getContact());
			}
		}else if(type==InfoType.PHONE){
			ContactPhoneDAOHibernate cidh = new ContactPhoneDAOHibernate();
			List<ContactPhoneNo> contactPhones = cidh.findByCriteria(criterion);
			for (ContactPhoneNo contactPhone : contactPhones) {
				contacts.add(contactPhone.getContact());
			}
		}else if(type==InfoType.URL){
			ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
			List<ContactURL> contactURLs = cudh.findByCriteria(criterion);
			for (ContactURL contactURL : contactURLs) {
				contacts.add(contactURL.getContact());
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.BaseSearcher#parseQueryToCriterion()
	 */
	@Override
	protected void parseQueryToCriterion() {
		String field = query.getField().toString();
		if (field.equals("gender")) {
			Gender gender = null;
			if (query.getValue().equalsIgnoreCase("male")) {
				gender = Gender.MALE;
			} else if (query.getValue().equalsIgnoreCase("female")) {
				gender = Gender.FEMALE;
			}
			criterion = Restrictions.eq(field, gender);
		} else if (field.equals("zipcode")) {
			criterion = Restrictions.eq(field, query.getValue());
		} else if (field.equals("name")) {
			criterion = Restrictions.eq("name", query.getValue());
		} else if (field.equals("formatted")) {
			criterion = Restrictions.eq("formatted", query.getValue());
		} else if (field.equals("value") && query.getType() == InfoType.ADDRESS) {
			criterion = Restrictions.eq("formatted", query.getValue());
		} else if (field.equals("perm")){
			criterion = Restrictions.ge(field, query.getValue());
		} else if(field.equals("value") && query.getType()!=InfoType.ADDRESS){
			criterion = Restrictions.eq("value", query.getValue());
		} else if(field.equals("type")){
			criterion = Restrictions.eq("type", query.getValue());
		} else if(field.equals("ownerId")){
			criterion = Restrictions.eq("owner.id", Long.valueOf(query.getValue()));
		}
	}

}
