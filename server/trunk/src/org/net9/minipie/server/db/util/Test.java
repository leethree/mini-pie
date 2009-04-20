package org.net9.minipie.server.db.util;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.ContactAddress;
import org.net9.minipie.server.db.entity.ContactEmail;
import org.net9.minipie.server.db.entity.ContactIM;
import org.net9.minipie.server.db.entity.ContactPhoneNo;
import org.net9.minipie.server.db.entity.ContactURL;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.Group2User;
import org.net9.minipie.server.db.entity.Notification;
import org.net9.minipie.server.db.entity.Tag;
import org.net9.minipie.server.db.entity.Tag2Contact;
import org.net9.minipie.server.db.entity.Tag2User;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.User2User;
import org.net9.minipie.server.db.entity.UserAddress;
import org.net9.minipie.server.db.entity.UserEmail;
import org.net9.minipie.server.db.entity.UserIM;
import org.net9.minipie.server.db.entity.UserPhoneNo;
import org.net9.minipie.server.db.entity.UserURL;
import org.net9.minipie.server.db.entity.constant.Bool;
import org.net9.minipie.server.db.entity.constant.Gender;
import org.net9.minipie.server.db.entity.constant.Permission;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* some code to test basic insert/delete */
		Contact contact1 = new Contact();
		Contact contact2 = new Contact();
		Contact contact3 = new Contact();
		User user1 = new User();
		User user2 = new User();
		UserAddress uAd1 = new UserAddress();
		UserAddress uAd2 = new UserAddress();
		ContactAddress uAd3 = new ContactAddress();
		user1.setUserName("jinyq06");
		user2.setUserName("her");
		contact1.setName("Mm");
		contact2.setName("ppmm");
		contact3.setName("pp");
		uAd1.setFormatted("tsighua");
		uAd2.setFormatted("peking");
		uAd3.setValue("lanzhou");
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		user1.getContacts().add(contact1);
		contact1.setOwner(user1);
		user1.getContacts().add(contact3);
		contact3.setOwner(user1);
		user2.getContacts().add(contact2);
		contact2.setOwner(user2);
		user1.getAddresses().add(uAd2);
		uAd2.setUser(user1);
		user2.getAddresses().add(uAd1);
		uAd1.setUser(user2);
		contact1.getAddress().add(uAd3);
		uAd3.setContact(contact1);
		session.save(user1);
		session.save(user2);
		session.save(contact1);
		session.save(contact2);
		session.save(contact3);
		session.save(uAd1);
		session.save(uAd2);
		session.getTransaction().commit();
		
		Group group1 = new Group();
		group1.setGroupName("bbs");
		session.beginTransaction();
		session.save(group1);
		session.getTransaction().commit();
		
		Tag tag1 = new Tag();
		tag1.setTagName("good");
		user1.getOwnedTags().add(tag1);
		tag1.setOwner(user1);
		session.beginTransaction();
		session.save(tag1);
		session.getTransaction().commit();
		
		User2User friendship1 = new User2User(user1, user2, Permission.TOCONTACT,
				 Permission.TOALL);
		session.beginTransaction();
		session.save(friendship1);
		session.getTransaction().commit();
		
		Group2User membership = new Group2User(group1, user1, Bool.TRUE);
		session.beginTransaction();
		session.save(membership);
		session.getTransaction().commit();
		
		Tag2User tagship1 = new Tag2User(tag1, user2);
		Tag2Contact tagship2 = new Tag2Contact(tag1, contact1);
		session.beginTransaction();
		session.save(tagship1);
		session.save(tagship2);
		session.getTransaction().commit();
		
		Notification notification1 = new Notification();
		notification1.setSender(user2);
		user2.getSentNotification().add(notification1);
		notification1.setReceiver(user1);
		user1.getReceivedNotification().add(notification1);
		session.beginTransaction();
		session.save(notification1);
		session.getTransaction().commit();
		
		session.beginTransaction();
		String hql = "from User where id = 1";
		Query query = session.createQuery(hql);
		User retrievedUser = (User) query.uniqueResult();
		System.out.println(retrievedUser.getId());
		Iterator<UserAddress> iter = retrievedUser.getAddresses().iterator();
		while(iter.hasNext()){
			UserAddress ua1 =  iter.next();
			System.out.println(ua1.getFormatted());
		}
		session.getTransaction().commit();
	}

}
