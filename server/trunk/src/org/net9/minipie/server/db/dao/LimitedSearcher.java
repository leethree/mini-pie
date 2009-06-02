/**
 * LimitedSearcher.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
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
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.UserAddress;
import org.net9.minipie.server.db.entity.UserEmail;
import org.net9.minipie.server.db.entity.UserIM;
import org.net9.minipie.server.db.entity.UserPhoneNo;
import org.net9.minipie.server.db.entity.UserURL;

/**
 * @author Riversand
 * 
 */
public class LimitedSearcher extends BaseSearcher {

	private Collection<Contact> contacts;
	private Criterion contactCriterion;
	private Long userId;

	public LimitedSearcher(Long userId, Query query) {
		super(query);
		parseQueryToCriterion();
		contacts = new ArrayList<Contact>();
		this.userId = userId;
	}

	public void setQuery(Query query) {
		super.setQuery(query);
	}

	public Query getQuery() {
		return super.getQuery();
	}

	public Collection<User> getUsers() {
		return super.getUsers();
	}

	/**
	 * @return the contacts
	 */
	public Collection<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return super.getCriterion();
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.db.dao.BaseSearcher#doSearch()
	 */
	@Override
	protected void doSearch() {
		InfoType type = query.getType();
		UserDAOHibernate udh = new UserDAOHibernate();
		User thisOne = udh.findById(userId);
		if (type == InfoType.ADDRESS) {
			UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
			ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
			List<UserAddress> userResult = uadh.findByCriteria(criterion);
			List<ContactAddress> contactResult = cadh
					.findByCriteria(contactCriterion);
			for (ContactAddress contactAddress : contactResult) {
				if (contactAddress.getContact().getOwner().getId() == this.userId) {
					contacts.add(contactAddress.getContact());
				}
			}
			for (UserAddress userAddress : userResult) {
				if (thisOne.getUsers1().contains(userAddress.getUser())
						|| thisOne.getUsers2().contains(userAddress.getUser())) {
					users.add(userAddress.getUser());
				}
			}
		} else if (type == InfoType.BASIC) {
			ContactDAOHibernate cdh = new ContactDAOHibernate();
			List<User> userResult = udh.findByCriteria(criterion);
			List<Contact> contactResult = cdh.findByCriteria(contactCriterion);
			for (Contact contact : contactResult) {
				if(contact.getOwner().getId()==userId){
					contacts.add(contact);
				}
			}
			for (User user : userResult) {
				if(thisOne.getUsers1().contains(user) || thisOne.getUsers2().contains(user)){
					users.add(user);
				}
			}
		} else if (type == InfoType.EMAIL) {
			UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
			ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
			List<UserEmail> userResult = uedh.findByCriteria(criterion);
			List<ContactEmail> contactResult = cedh
					.findByCriteria(contactCriterion);
			for (ContactEmail contactEmail : contactResult) {
				if (contactEmail.getContact().getOwner().getId() == this.userId) {
					contacts.add(contactEmail.getContact());
				}
			}
			for (UserEmail userEmail : userResult) {
				if (thisOne.getUsers1().contains(userEmail.getUser())
						|| thisOne.getUsers2().contains(userEmail.getUser())) {
					users.add(userEmail.getUser());
				}
			}
		} else if (type == InfoType.IM) {
			UserIMDAOHibernate uidh = new UserIMDAOHibernate();
			ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
			List<UserIM> userResult = uidh.findByCriteria(criterion);
			List<ContactIM> contactResult = cidh
					.findByCriteria(contactCriterion);
			for (ContactIM contactIM : contactResult) {
				if (contactIM.getContact().getOwner().getId() == this.userId) {
					contacts.add(contactIM.getContact());
				}
			}
			for (UserIM userIM : userResult) {
				if (thisOne.getUsers1().contains(userIM.getUser())
						|| thisOne.getUsers2().contains(userIM.getUser())) {
					users.add(userIM.getUser());
				}
			}
		} else if (type == InfoType.PHONE) {
			UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
			ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
			List<UserPhoneNo> userResult = updh.findByCriteria(criterion);
			List<ContactPhoneNo> contactResult = cpdh
					.findByCriteria(contactCriterion);
			for (ContactPhoneNo contactPhoneNo : contactResult) {
				if (contactPhoneNo.getContact().getOwner().getId() == this.userId) {
					contacts.add(contactPhoneNo.getContact());
				}
			}
			for (UserPhoneNo userPhoneNo : userResult) {
				if (thisOne.getUsers1().contains(userPhoneNo.getUser())
						|| thisOne.getUsers2().contains(userPhoneNo.getUser())) {
					users.add(userPhoneNo.getUser());
				}
			}
		} else if (type == InfoType.URL) {
			UserURLDAOHibernate uudh = new UserURLDAOHibernate();
			ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
			List<UserURL> userResult = uudh.findByCriteria(criterion);
			List<ContactURL> contactResult = cudh
					.findByCriteria(contactCriterion);
			for (ContactURL contactURL : contactResult) {
				if (contactURL.getContact().getOwner().getId() == this.userId) {
					contacts.add(contactURL.getContact());
				}
			}
			for (UserURL userURL : userResult) {
				if (thisOne.getUsers1().contains(userURL.getUser())
						|| thisOne.getUsers2().contains(userURL.getUser())) {
					users.add(userURL.getUser());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
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
			contactCriterion = Restrictions.eq(field, gender);
		} else if (field.equals("zipcode")) {
			criterion = Restrictions.eq(field, query.getValue());
			contactCriterion = null;
		} else if (field.equals("name")) {
			criterion = Restrictions.eq("displayName", query.getValue());
			contactCriterion = null;
		} else {
			criterion = Restrictions.eq(field, query.getValue());
			contactCriterion = Restrictions.eq(field, query.getValue());
		}
	}

}
