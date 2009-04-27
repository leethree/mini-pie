package org.net9.minipie.server.db.util;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.entity.constant.Bool;
import org.net9.minipie.server.db.entity.constant.Gender;
import org.net9.minipie.server.db.entity.constant.Permission;

@SuppressWarnings("unused")
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* some code to test basic insert/delete */
//		Contact contact1 = new Contact();
//		Contact contact2 = new Contact();
//		Contact contact3 = new Contact();
//		User user1 = new User();
//		User user2 = new User();
//		UserAddress uAd1 = new UserAddress();
//		UserAddress uAd2 = new UserAddress();
//		ContactAddress uAd3 = new ContactAddress();
//		user1.setUserName("jinyq06");
//		user2.setUserName("her");
//		contact1.setName("Mm");
//		contact2.setName("ppmm");
//		contact3.setName("pp");
//		uAd1.setFormatted("tsighua");
//		uAd2.setFormatted("peking");
//		uAd3.setValue("lanzhou");
//		Session session = HibernateSessionFactory.getSession();
//		session.beginTransaction();
//		user1.getContacts().add(contact1);
//		contact1.setOwner(user1);
//		user1.getContacts().add(contact3);
//		contact3.setOwner(user1);
//		user2.getContacts().add(contact2);
//		contact2.setOwner(user2);
//		user1.getAddresses().add(uAd2);
//		uAd2.setUser(user1);
//		user2.getAddresses().add(uAd1);
//		uAd1.setUser(user2);
//		contact1.getAddress().add(uAd3);
//		uAd3.setContact(contact1);
//		session.save(user1);
//		session.save(user2);
//		session.save(contact1);
//		session.save(contact2);
//		session.save(contact3);
//		session.save(uAd1);
//		session.save(uAd2);
//		session.getTransaction().commit();
//		
//		Group group1 = new Group();
//		group1.setGroupName("bbs");
//		session.beginTransaction();
//		session.save(group1);
//		session.getTransaction().commit();
//		
//		Tag tag1 = new Tag();
//		tag1.setTagName("good");
//		user1.getOwnedTags().add(tag1);
//		tag1.setOwner(user1);
//		session.beginTransaction();
//		session.save(tag1);
//		session.getTransaction().commit();
//		
//		User2User friendship1 = new User2User(user1, user2, Permission.TOCONTACT,
//				 Permission.TOALL);
//		session.beginTransaction();
//		session.save(friendship1);
//		session.getTransaction().commit();
//		
//		Group2User membership = new Group2User(group1, user1, Bool.TRUE);
//		session.beginTransaction();
//		session.save(membership);
//		session.getTransaction().commit();
//		
//		Tag2User tagship1 = new Tag2User(tag1, user2);
//		Tag2Contact tagship2 = new Tag2Contact(tag1, contact1);
//		session.beginTransaction();
//		session.save(tagship1);
//		session.save(tagship2);
//		session.getTransaction().commit();
//		
//		Notification notification1 = new Notification();
//		notification1.setSender(user2);
//		user2.getSentNotification().add(notification1);
//		notification1.setReceiver(user1);
//		user1.getReceivedNotification().add(notification1);
//		session.beginTransaction();
//		session.save(notification1);
//		session.getTransaction().commit();
//		
//		session.beginTransaction();
//		String hql = "from User where id = 1";
//		Query query = session.createQuery(hql);
//		User retrievedUser = (User) query.uniqueResult();
//		System.out.println(retrievedUser.getId());
//		Iterator<UserAddress> iter = retrievedUser.getAddresses().iterator();
//		while(iter.hasNext()){
//			UserAddress ua1 =  iter.next();
//			System.out.println(ua1.getFormatted());
//		}
//		session.getTransaction().commit();
		UserDAOHibernate dao = new UserDAOHibernate();
		Long l = dao.add("jinyq06", "123", "jinyq06@gmail.com");
		Long adId = dao.addAddr(l, "home", null, "100084", Bool.FALSE, Permission.TOCONTACT);
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Long contact1Id = cdh.addUserContact(l, "her");
		cdh.editBasicInfo(contact1Id, "nickName", "mama");
		Long contact1AddrId = cdh.addAddr(contact1Id, "Tsinghua", "University", Bool.FALSE);
		cdh.editAddr(contact1AddrId, "value", "zijing building");
		Long contact1EmailId = cdh.addEmail(contact1Id, "jinyq06@gmail", "gmail", Bool.TRUE);
		Long contact1IMId = cdh.addIM(contact1Id, "601524835", null, Bool.FALSE);
		Long contact1PhoneId = cdh.addTel(contact1Id, "110", "home", null);
		Long contact1URLId = cdh.addURL(contact1Id, null, "url", Bool.TRUE);
		List<Object[]> result = cdh.selectOwnerContact(l);
		Iterator<Object[]> iter = result.iterator();
		while(iter.hasNext()){
			Object[] objs = (Object[]) iter.next();
			for(int i=0; i<7; i++){
				switch(i){
				case 0:
					if(objs[i]!=null){
						String name = (String) objs[i];
						System.out.println(name);
					}
					break;
				case 1:
					if(objs[i]!=null){
						String image = (String) objs[i];
						System.out.println(image);
					}
					break;
				case 2:
					if(objs[i]!=null){
						String nickName = (String) objs[i];
						System.out.println(nickName);
					}
					break;
				case 3:
					if(objs[i]!=null){
						Gender gender = (Gender) objs[i];
						System.out.println(gender);
					}
					break;
				case 4: 
					if(objs[i]!=null){
						Date birthday = (Date)objs[i];
						System.out.println(birthday);
					}
					break;
				case 5:
					if(objs[i]!=null){
						String notes = (String) objs[i];
						System.out.println(notes);
					}
					break;
				case 6:
					if(objs[i]!=null){
						String relationship = (String) objs[i];
						System.out.println(relationship);
					}
					break;
				default:break;
				}
			}
		}
		//cdh.del(contact1Id);	
	}	

}
