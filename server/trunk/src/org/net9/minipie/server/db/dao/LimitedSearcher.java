/**
 * LimitedSearcher.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
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
import org.net9.minipie.server.db.entity.User2User;
import org.net9.minipie.server.db.entity.UserAddress;
import org.net9.minipie.server.db.entity.UserEmail;
import org.net9.minipie.server.db.entity.UserIM;
import org.net9.minipie.server.db.entity.UserPhoneNo;
import org.net9.minipie.server.db.entity.UserURL;
import org.net9.minipie.server.exception.NotFoundException;

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
		doSearch();
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
		User_UserDAOHibernate u_udh = new User_UserDAOHibernate();
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
				boolean flag = false;
				User2User bind = null;
				try{
					bind = u_udh.findById(new User2User.Id(userId, userAddress.getUser().getId()));
				}catch(ObjectNotFoundException e){
					flag = true;
				}
				if(flag){
					try{
						bind = u_udh.findById(new User2User.Id(userAddress.getUser().getId(), userId));
					}catch(ObjectNotFoundException e){
						throw new NotFoundException("there is no bind between user "+userId+" and user "+userAddress.getUser().getId());
					}
				}
				if(bind!=null){
					users.add(userAddress.getUser());
				}
			}
		} else if (type == InfoType.BASIC) {
			ContactDAOHibernate cdh = new ContactDAOHibernate();
			List<User> userResult = udh.findByCriteria(criterion);
			List<Contact> contactResult = cdh.findByCriteria(contactCriterion);
			for (Contact contact : contactResult) {
				if (contact.getOwner().getId() == userId) {
					contacts.add(contact);
				}
			}
			for (User user : userResult) {
				boolean flag = false;
				User2User bind = null;
				try{
					bind = u_udh.findById(new User2User.Id(userId, user.getId()));
				}catch(ObjectNotFoundException e){
					flag = true;
				}
				if(flag){
					try{
						bind = u_udh.findById(new User2User.Id(user.getId(), userId));
					}catch(ObjectNotFoundException e){
						throw new NotFoundException("there is no bind between user " + userId +" and user "+user.getId());
					}
				}
				if(bind!=null){
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
				boolean flag = false;
				User2User bind = null;
				try{
					bind = u_udh.findById(new User2User.Id(userId, userEmail.getUser().getId()));
				}catch(ObjectNotFoundException e){
					flag = true;
				}
				if(flag){
					try{
						bind = u_udh.findById(new User2User.Id(userEmail.getUser().getId(), userId));
					}catch(ObjectNotFoundException e){
						throw new NotFoundException("there is no bind between user "+userId+" and user "+userEmail.getUser().getId());
					}
				}
				if(bind!=null){
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
				boolean flag = false;
				User2User bind = null;
				try{
					bind = u_udh.findById(new User2User.Id(userId, userIM.getUser().getId()));
				}catch(ObjectNotFoundException e){
					flag = true;
				}
				if(flag){
					try{
						bind = u_udh.findById(new User2User.Id(userIM.getUser().getId(), userId));
					}catch(ObjectNotFoundException e){
						throw new NotFoundException("there is no bind between user "+userId+" and user "+userIM.getUser().getId());
					}
				}
				if(bind!=null){
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
				boolean flag = false;
				User2User bind = null;
				try{
					bind = u_udh.findById(new User2User.Id(userId, userPhoneNo.getUser().getId()));
				}catch(ObjectNotFoundException e){
					flag = true;
				}
				if(flag){
					try{
						bind = u_udh.findById(new User2User.Id(userPhoneNo.getUser().getId(), userId));
					}catch(ObjectNotFoundException e){
						throw new NotFoundException("there is no bind between user "+userId+" and user "+userPhoneNo.getUser().getId());
					}
				}
				if(bind!=null){
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
				boolean flag = false;
				User2User bind = null;
				try{
					bind = u_udh.findById(new User2User.Id(userId, userURL.getUser().getId()));
				}catch(ObjectNotFoundException e){
					flag = true;
				}
				if(flag){
					try{
						bind = u_udh.findById(new User2User.Id(userURL.getUser().getId(), userId));
					}catch(ObjectNotFoundException e){
						throw new NotFoundException("there is no bind between user "+userId+" and user "+userURL.getUser().getId());
					}
				}
				if(bind!=null){
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
		} else if (field.equals("formatted")) {
			criterion = Restrictions.eq("formatted", query.getValue());
			contactCriterion = Restrictions.eq("value", query.getValue());
		} else if (field.equals("value") && query.getType() == InfoType.ADDRESS) {
			criterion = Restrictions.eq("formatted", query.getValue());
			contactCriterion = Restrictions.eq("value", query.getValue());
		} else {
			criterion = Restrictions.eq(field, query.getValue());
			contactCriterion = Restrictions.eq(field, query.getValue());
		}
	}

}
