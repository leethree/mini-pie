package org.net9.minipie.server.db;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.dao.User_UserDAOHibernate;
import org.net9.minipie.server.db.entity.enums.Bool;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.BasicContact;

@SuppressWarnings("unused")
public class DbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserDAOHibernate dao = new UserDAOHibernate();
			Long l = dao.add("jinyq06", "123", "jinyq06@gmail.com");
			Long l2 = dao.add("tom", "333", "tom06@gmail.com");
			System.out.println("l "+l+"l2 "+l2);
			User_UserDAOHibernate bindManager = new User_UserDAOHibernate();
			bindManager.add(l2, l);
			System.out.println("long id "+l);
			Long adId = dao.addAddr(l, new AddressData(1, "home", "Zijing", false, "Tsinghua", "100084",
					Permission.TO_CONTACTS));
			ContactDAOHibernate cdh = new ContactDAOHibernate();
			Long contact1Id = cdh.addUserContact(l, "her");
			cdh.editBasicInfo(contact1Id, InfoField.NAME, "Jersey");
			cdh.editBasicInfo(contact1Id, InfoField.GENDER, Gender.FEMALE);
			cdh.editBasicInfo(contact1Id, InfoField.NOTE, "this is my best friend");
			cdh.editBasicInfo(contact1Id, InfoField.RELATIONSHIP, "friends");
			AddressData addressData = new AddressData();
			addressData.setPermission(Permission.TO_CONTACTS);
			addressData.setPrimary(false);
			addressData.setType("home");
			addressData.setValue("zijing");
			addressData.setZipcode("10084");
			Long contact1AddrId = cdh.addAddr(contact1Id, addressData);
			cdh.editAddr(contact1AddrId, InfoField.VALUE, "zijing building");
			EmailData emailData = new EmailData();
			emailData.setPermission(Permission.TO_EVERYONE);
			emailData.setPrimary(true);
			emailData.setType("main");
			emailData.setValue("jinyq06@gmail.com");
			Long contact1EmailId = cdh.addEmail(contact1Id, emailData);
			IMData imData = new IMData();
			imData.setValue("601524835");
			Long contact1IMId = cdh.addIM(contact1Id, imData);
			PhoneNoData phoneNoData = new PhoneNoData();
			phoneNoData.setValue("13581979236");
			Long contact1PhoneId = cdh.addTel(contact1Id, phoneNoData);
			URLData urlData = new URLData();
			urlData.setValue("www.google.cn/my.jpg");
			Long contact1URLId = cdh.addURL(contact1Id, urlData);
			BasicContact result = cdh.selectBasicInfo(contact1Id);
			List<AddressData> addresses = cdh.selectAddr(contact1Id);
			Iterator<AddressData> iter1 = addresses.iterator();
			while (iter1.hasNext()) {
				AddressData addr = iter1.next();
				System.out.println(addr.getValue());
				System.out.println(addr.getType());
				System.out.println(addr.isPrimary());
			}
			System.out
					.println("**************************************************");
			List<EmailData> emails = cdh.selectEmail(contact1Id);
			Iterator<EmailData> emailIter = emails.iterator();
			while (emailIter.hasNext()) {
				EmailData email = emailIter.next();
				System.out.println(email.getValue());
				System.out.println(email.getType());
				System.out.println(email.isPrimary());
			}
			System.out
					.println("**************************************************");
			List<IMData> ims = cdh.selectIM(contact1Id);
			Iterator<IMData> imIter = ims.iterator();
			while (imIter.hasNext()) {
				IMData im = imIter.next();
				System.out.println(im.getValue());
				System.out.println(im.getType());
				System.out.println(im.isPrimary());
			}
			System.out
					.println("**************************************************");
			List<PhoneNoData> tels = cdh.selectTel(contact1Id);
			Iterator<PhoneNoData> telIter = tels.iterator();
			while (telIter.hasNext()) {
				PhoneNoData tel = telIter.next();
				System.out.println(tel.getValue());
				System.out.println(tel.getType());
				System.out.println(tel.isPrimary());
			}
			System.out
					.println("**************************************************");
			List<URLData> urls = cdh.selectURL(contact1Id);
			Iterator<URLData> urlIter = urls.iterator();
			while (urlIter.hasNext()) {
				URLData url = urlIter.next();
				System.out.println(url.getValue());
				System.out.println(url.getType());
				System.out.println(url.isPrimary());
			}
			// cdh.del(contact1Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
