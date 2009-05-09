package org.net9.minipie.server.db.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.net9.minipie.server.db.dao.ContactDAOHibernate;
import org.net9.minipie.server.db.dao.UserDAOHibernate;
import org.net9.minipie.server.db.entity.enums.Bool;
import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.storage.BasicContact;

@SuppressWarnings("unused")
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserDAOHibernate dao = new UserDAOHibernate();
		Long l = dao.add("jinyq06", "123", "jinyq06@gmail.com");
		Long adId = dao.addAddr(l, "home", null, "100084", Bool.FALSE,
				Permission.TO_CONTACTS);
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Long contact1Id = cdh.addUserContact(l, "her");
		cdh.editBasicInfo(contact1Id, "nickName", "mama");
		cdh.editBasicInfo(contact1Id, "name", "Jersey");
		cdh.editBasicInfo(contact1Id, "gender", Gender.FEMALE);
		cdh.editBasicInfo(contact1Id, "image", "http://server2.net9");
		cdh.editBasicInfo(contact1Id, "notes", "this is my best friend");
		cdh.editBasicInfo(contact1Id, "relationship", "friends");
		Long contact1AddrId = cdh.addAddr(contact1Id, new AddressData(0,
				"Tsinghua", "University", false, null, null, null));
		cdh.editAddr(contact1AddrId, "value", "zijing building");
		Long contact1EmailId = cdh.addEmail(contact1Id, new EmailData(0,
				"jinyq06@gmail", "gmail", true, null));
		Long contact1IMId = cdh.addIM(contact1Id, new IMData(0, "601524835",
				null, false, null));
		Long contact1PhoneId = cdh.addTel(contact1Id, new PhoneNoData(0,
				"110", "home", false, null));
		Long contact1URLId = cdh.addURL(contact1Id, new URLData(0, null,
				"url", true, null));
		BasicContact result = cdh.selectBasicInfo(contact1Id);
		List<AddressData> addresses = cdh.selectAddr(contact1Id);
		Iterator<AddressData> iter1 = addresses.iterator();
		while (iter1.hasNext()) {
			AddressData addr = iter1.next();
			System.out.println(addr.getValue());
			System.out.println(addr.getType());
			System.out.println(addr.isPrimary());
		}
		System.out.println("**************************************************");
		List<EmailData> emails = cdh.selectEmail(contact1Id);
		Iterator<EmailData> emailIter = emails.iterator();
		while (emailIter.hasNext()) {
			EmailData email = emailIter.next();
			System.out.println(email.getValue());
			System.out.println(email.getType());
			System.out.println(email.isPrimary());
		}
		System.out.println("**************************************************");
		List<IMData> ims = cdh.selectIM(contact1Id);
		Iterator<IMData> imIter = ims.iterator();
		while (imIter.hasNext()) {
			IMData im = imIter.next();
			System.out.println(im.getValue());
			System.out.println(im.getType());
			System.out.println(im.isPrimary());
		}
		System.out.println("**************************************************");
		List<PhoneNoData> tels = cdh.selectTel(contact1Id);
		Iterator<PhoneNoData> telIter = tels.iterator();
		while (telIter.hasNext()) {
			PhoneNoData tel = telIter.next();
			System.out.println(tel.getValue());
			System.out.println(tel.getType());
			System.out.println(tel.isPrimary());
		}
		System.out.println("**************************************************");
		List<URLData> urls = cdh.selectURL(contact1Id);
		Iterator<URLData> urlIter = urls.iterator();
		while (urlIter.hasNext()) {
			URLData url = urlIter.next();
			System.out.println(url.getValue());
			System.out.println(url.getType());
			System.out.println(url.isPrimary());
		}
	    //cdh.del(contact1Id);
	}

}
