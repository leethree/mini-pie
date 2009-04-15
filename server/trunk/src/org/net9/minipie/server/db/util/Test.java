package org.net9.minipie.server.db.util;
import org.hibernate.Session;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.ContactAddress;
import org.net9.minipie.server.db.entity.ContactEmail;
import org.net9.minipie.server.db.entity.ContactIM;
import org.net9.minipie.server.db.entity.ContactPhoneNo;
import org.net9.minipie.server.db.entity.ContactURL;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.Group2User;
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
		User user1 = new User();
		UserAddress addr1 = new UserAddress(), addr2 = new UserAddress();
		User user2 = new User();
		user2.setUserName("Her");
		Session session = HibernateSessionFactory.getSession();
		addr1.setZipcode("730070");
		addr2.setZipcode("100084");
		addr1.setFormatted("Tsinghua University");
		addr2.setFormatted("Peking University");
		user1.setUserName("Jinyq06");
		user1.getAddresses().add(addr1);
		user1.getAddresses().add(addr2);
		UserEmail email2 = new UserEmail();
		email2.setValue("jinyq06@gmail.com");
		UserIM usrIm = new UserIM();
		usrIm.setValue("here and there is everywhere");
		user2.getEmails().add(email2);
		user2.getIms().add(usrIm);
		UserPhoneNo tel = new UserPhoneNo();
		tel.setValue("7670191");
		user2.getPhono().add(tel);
		UserURL url = new UserURL();
		url.setValue("http://localhost:3306");
		user2.getUrl().add(url);
		session.beginTransaction();
		session.save(user1);
		session.save(user2);
		session.getTransaction().commit();
		session.beginTransaction();
		User2User friendship = new User2User(user1, user2, Permission.TOCONTACT, Permission.TOSELF);
		Contact person = new Contact();
		ContactAddress contactAddress = new  ContactAddress();
		contactAddress.setValue("Bernand St");
		contactAddress.setIsPrimary(Bool.TRUE);
		contactAddress.setType("home");
		person.getAddress().add(contactAddress);
		person.setOwnerId(user1.getId());
		person.setGender(Gender.MALE);
		ContactEmail email = new ContactEmail();
		email.setValue("her07@gmail.com");
		person.getEmails().add(email);
		ContactIM imc = new ContactIM();
		imc.setValue("127.0.0.1");
		person.getIms().add(imc);
		ContactPhoneNo phoneNo = new ContactPhoneNo();
		phoneNo.setValue("7670210");
		person.getPhones().add(phoneNo);
		ContactURL curl = new ContactURL();
		curl.setValue("localhost");
		person.getUrls().add(curl);
		session.save(person);
		session.save(friendship);
		session.getTransaction().commit();
		Group group = new Group();
		group.setGroupName("MiniPie");
		session.beginTransaction();
		session.save(group);
		session.getTransaction().commit();
		Group2User membership = new Group2User(group, user2, Bool.TRUE);
		session.beginTransaction();
		session.save(membership);
		session.getTransaction().commit();
		Group2User membership2 = new Group2User(group, user1, Bool.FALSE);
		session.beginTransaction();
		session.save(membership2);
		session.getTransaction().commit();
		System.out.println(membership.getIsAdmin());
		System.out.println(membership2.getIsAdmin());
		Tag tag = new Tag();
		tag.setOwnerId(user1.getId());
		tag.setTagName("pet");
		session.beginTransaction();
		session.save(tag);
		session.getTransaction().commit();
		Tag2User tagged = new Tag2User(tag, user2);
		session.beginTransaction();
		session.save(tagged);
		session.getTransaction().commit();
		Tag2Contact taggedContact = new Tag2Contact(tag, person);
		session.beginTransaction();
		session.save(taggedContact);
		session.getTransaction().commit();
	}

}
