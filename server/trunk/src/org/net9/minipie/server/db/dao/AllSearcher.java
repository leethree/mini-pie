/**
 * Searcher.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.Query;
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
public class AllSearcher extends BaseSearcher{
	/**
	 * Constructor
	 */
	public AllSearcher(Query query) {
		super(query);
		parseQueryToCriterion();
		doSearch();
	}

	/**
	 * @param query2
	 * @return
	 */
	public void parseQueryToCriterion() {
		String field = query.getField().toString();
		if(field.equals("name")){
			criterion = Restrictions.eq("displayName", query.getValue());
		}else if (!field.equals("gender")){
			criterion = Restrictions.eq(field, query.getValue());
		}else {
			Gender gender = null;
			if (query.getValue().equalsIgnoreCase("male")) {
				gender = Gender.MALE;
			} else if (query.getValue().equalsIgnoreCase("female")) {
				gender = Gender.FEMALE;
			}
			criterion = Restrictions.eq(field, gender);
		}

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
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return super.getCriterion();
	}

	/**
	 * 
	 */
	public void doSearch() {
		InfoType type = query.getType();
		if (type == InfoType.ADDRESS) {
			UserAddressDAOHibernate uadh = new UserAddressDAOHibernate();
			List<UserAddress> result = uadh.findByCriteria(criterion,
					Restrictions.eq("perm", Permission.TO_EVERYONE));
			for (UserAddress userAddress : result) {
				users.add(userAddress.getUser());
			}
		} else if (type == InfoType.BASIC) {
			UserDAOHibernate udh = new UserDAOHibernate();
			if (query.getField() == InfoField.BIRTHDAY) {
				users.addAll(udh.findByCriteria(criterion, Restrictions.eq(
						"birthdayPermission", Permission.TO_EVERYONE),
						Restrictions.eq("birthyearPermission",
								Permission.TO_EVERYONE)));
			} else if (query.getField() == InfoField.GENDER) {
				users.addAll(udh.findByCriteria(criterion, Restrictions.eq(
						"genderPermission", Permission.TO_EVERYONE)));
			} else{
				users.addAll(udh.findByCriteria(criterion));
			}
		} else if (type == InfoType.EMAIL) {
			UserEmailDAOHibernate uedh = new UserEmailDAOHibernate();
			List<UserEmail> result = uedh.findByCriteria(criterion,
					Restrictions.eq("perm", Permission.TO_EVERYONE));
			for (UserEmail userEmail : result) {
				users.add(userEmail.getUser());
			}
		} else if (type == InfoType.IM) {
			UserIMDAOHibernate uidh = new UserIMDAOHibernate();
			List<UserIM> result = uidh.findByCriteria(criterion, Restrictions
					.eq("perm", Permission.TO_EVERYONE));
			for (UserIM userIM : result) {
				users.add(userIM.getUser());
			}
		} else if (type == InfoType.PHONE) {
			UserPhoneDAOHibernate updh = new UserPhoneDAOHibernate();
			List<UserPhoneNo> result = updh.findByCriteria(criterion,
					Restrictions.eq("perm", Permission.TO_EVERYONE));
			for (UserPhoneNo userPhoneNo : result) {
				users.add(userPhoneNo.getUser());
			}
		} else if (type == InfoType.URL) {
			UserURLDAOHibernate uudh = new UserURLDAOHibernate();
			List<UserURL> result = uudh.findByCriteria(criterion, Restrictions
					.eq("perm", Permission.TO_EVERYONE));
			for (UserURL userURL : result) {
				users.add(userURL.getUser());
			}
		}
	}
}
