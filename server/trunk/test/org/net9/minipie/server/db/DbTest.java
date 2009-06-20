package org.net9.minipie.server.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.GroupDAOHibernate;
import org.net9.minipie.server.db.dao.Group_UserDAOHibernate;
import org.net9.minipie.server.db.dao.NotificationDAOHibernate;
import org.net9.minipie.server.db.dao.TagDAOHibernate;
import org.net9.minipie.server.db.dao.Tag_ContactDAOHibernate;
import org.net9.minipie.server.db.dao.Tag_UserDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.dao.User_UserDAOHibernate;
import org.net9.minipie.server.db.entity.Group;
import org.net9.minipie.server.db.entity.Notification;
import org.net9.minipie.server.db.entity.enums.Bool;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.NotificationData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.data.field.NotificationType;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.Query;

@SuppressWarnings("unused")
public class DbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserDAOHibernate udh = new UserDAOHibernate();
//			Long l = udh.add("jinyq06", "123", "jinyq06@gmail.com");
//			Long l1 = udh.add("Rachael", "880211", "rachael@gmail.com");
//			Long l2 = udh.add("tom", "333", "tom06@gmail.com");
//			System.out.println("l "+l+" l1 "+l1+" l2 "+l2);
//			User_UserDAOHibernate bindManager = new User_UserDAOHibernate();
//			bindManager.add(l2, l);
//			bindManager.add(l1, l2);
//			System.out.println("long id "+l);
//			Long adId = udh.addAddr(l, new AddressData(1, "home", "Zijing", false, "Tsinghua", "100084",
//					Permission.TO_CONTACTS));
//			ContactDAOHibernate cdh = new ContactDAOHibernate();
//			Long contact1Id = cdh.addUserContact(l, "her");
//			cdh.editBasicInfo(contact1Id, InfoField.NAME, "Jersey");
//			cdh.editBasicInfo(contact1Id, InfoField.GENDER, Gender.FEMALE);
//			cdh.editBasicInfo(contact1Id, InfoField.NOTE, "this is my best friend");
//			cdh.editBasicInfo(contact1Id, InfoField.RELATIONSHIP, "friends");
//			AddressData addressData = new AddressData();
//			addressData.setPermission(Permission.TO_CONTACTS);
//			addressData.setPrimary(false);
//			addressData.setType("home");
//			addressData.setValue("zijing");
//			addressData.setZipcode("10084");
//			Long contact1AddrId = cdh.addAddr(contact1Id, addressData);
//			cdh.editAddr(contact1AddrId, InfoField.VALUE, "zijing building");
//			EmailData emailData = new EmailData();
//			emailData.setPermission(Permission.TO_EVERYONE);
//			emailData.setPrimary(true);
//			emailData.setType("main");
//			emailData.setValue("jinyq06@gmail.com");
//			Long contact1EmailId = cdh.addEmail(contact1Id, emailData);
//			IMData imData = new IMData();
//			imData.setValue("601524835");
//			Long contact1IMId = cdh.addIM(contact1Id, imData);
//			PhoneNoData phoneNoData = new PhoneNoData();
//			phoneNoData.setValue("13581979236");
//			Long contact1PhoneId = cdh.addTel(contact1Id, phoneNoData);
//			URLData urlData = new URLData();
//			urlData.setValue("www.google.cn/my.jpg");
//			Long contact1URLId = cdh.addURL(contact1Id, urlData);
//			BasicContact result = cdh.selectBasicInfo(contact1Id);
//			List<AddressData> addresses = cdh.selectAddr(contact1Id);
//			Iterator<AddressData> iter1 = addresses.iterator();
//			while (iter1.hasNext()) {
//				AddressData addr = iter1.next();
//				System.out.println(addr.getValue());
//				System.out.println(addr.getType());
//				System.out.println(addr.isPrimary());
//			}
//			System.out
//					.println("**************************************************");
//			List<EmailData> emails = cdh.selectEmail(contact1Id);
//			Iterator<EmailData> emailIter = emails.iterator();
//			while (emailIter.hasNext()) {
//				EmailData email = emailIter.next();
//				System.out.println(email.getValue());
//				System.out.println(email.getType());
//				System.out.println(email.isPrimary());
//			}
//			System.out
//					.println("**************************************************");
//			List<IMData> ims = cdh.selectIM(contact1Id);
//			Iterator<IMData> imIter = ims.iterator();
//			while (imIter.hasNext()) {
//				IMData im = imIter.next();
//				System.out.println(im.getValue());
//				System.out.println(im.getType());
//				System.out.println(im.isPrimary());
//			}
//			System.out
//					.println("**************************************************");
//			List<PhoneNoData> tels = cdh.selectTel(contact1Id);
//			Iterator<PhoneNoData> telIter = tels.iterator();
//			while (telIter.hasNext()) {
//				PhoneNoData tel = telIter.next();
//				System.out.println(tel.getValue());
//				System.out.println(tel.getType());
//				System.out.println(tel.isPrimary());
//			}
//			System.out
//					.println("**************************************************");
//			List<URLData> urls = cdh.selectURL(contact1Id);
//			Iterator<URLData> urlIter = urls.iterator();
//			while (urlIter.hasNext()) {
//				URLData url = urlIter.next();
//				System.out.println(url.getValue());
//				System.out.println(url.getType());
//				System.out.println(url.isPrimary());
//			}
//			// cdh.del(contact1Id);
//			User_UserDAOHibernate uudh = new User_UserDAOHibernate();
//			Collection<CommonListEntry> sharedUsers = uudh.selectSharedUser(l2, Permission.TO_CONTACTS);
//			Iterator<CommonListEntry> iter = sharedUsers.iterator();
//			while(iter.hasNext()){
//				CommonListEntry entry = iter.next();
//				System.out.println(entry.getEntity().getId()+" "+entry.getEntity().getName());
//			}
//			TagDAOHibernate tdh = new TagDAOHibernate();
//			Long tagId1 = tdh.addTag(l, "my best");
//			tdh.editTag(tagId1, "my best best");
//			System.out.println(tdh.selectId(l, "my best best"));
//			System.out.println((tdh.selectTag(tagId1)).getName());
//			Tag_ContactDAOHibernate tcdh = new Tag_ContactDAOHibernate();
//			tcdh.add(tagId1, contact1Id);
//			Collection<BasicContact> taggedContacts = tcdh.selectTaggedContact(tagId1);
//			Iterator<BasicContact> iter2 = taggedContacts.iterator();
//			while(iter2.hasNext()){
//				BasicContact basicContact = iter2.next();
//				System.out.println(basicContact.getEntity().getId()+" "+basicContact.getEntity().getName());
//			}
//			Collection<TagEntry> tags = tcdh.selectTagsOfContact(contact1Id);
//			Iterator<TagEntry> iter3 = tags.iterator();
//			while(iter3.hasNext()){
//				TagEntry tag = iter3.next();
//				System.out.println(tag.getId()+" "+tag.getName());
//			}
//			//tcdh.del(tagId1, contact1Id);
//			//tdh.removeTag(tagId1);
//			Tag_UserDAOHibernate tudh = new Tag_UserDAOHibernate();
//			tudh.add(tagId1, l2);
//			//tdh.removeTag(tagId1);
//			Collection<TagEntry> tagsOfUser = tudh.selectTagsOfUser(l2, l);
//			Iterator<TagEntry> iter4 = tagsOfUser.iterator();
//			while(iter4.hasNext()){
//				TagEntry tag = iter4.next();
//				System.out.println("haha"+tag.getId()+" "+tag.getName());
//			}
//			//tudh.del(tagId1, l2);
//			NotificationData notificationData = new NotificationData(new Long(1), l, l2, "please help" +
//					"me", NotificationType.CONTACT_APPLICATION);
//			NotificationDAOHibernate ndh = new NotificationDAOHibernate();
//			Long notificationId1 = ndh.add(notificationData);
//			//ndh.del(notificationId1);
//			NotificationData testData = ndh.selectNotification(notificationId1);
//			System.out.println(testData.getContent()+" "+testData.getSenderId()+
//					" "+testData.getReceiverId());
//			Collection<NotificationData> notifications = ndh.selectReceiver(l2);
//			Iterator<NotificationData> iter5 = notifications.iterator();
//			while(iter5.hasNext()){
//				NotificationData notif = iter5.next();
//				System.out.println(notif.getContent()+" "+notif.getSenderId()+" "+notif.getReceiverId());
//			}
//			System.out.println(udh.findAddressOwner(new Long(l)));
//			GroupDAOHibernate gdh = new GroupDAOHibernate();
//			Long g1Id = gdh.createGroup("firstGroup", l1);
//			Group_UserDAOHibernate gudh = new Group_UserDAOHibernate();
//			gudh.add(g1Id, l1);
			
			Query query = new Query(InfoType.IM, InfoField.VALUE, "rosenberger");
			Collection<Query> queries = new ArrayList<Query>();
			queries.add(query);
			Collection<CommonListEntry> users = udh.searchMyUserOrContact(new Long(1), queries);
			for (CommonListEntry commonListEntry : users) {
				System.out.println(commonListEntry.getEntity().getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
