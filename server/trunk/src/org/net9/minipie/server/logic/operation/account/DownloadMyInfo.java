/**
 * DownloadMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class DownloadMyInfo extends Command<String> {
	private long userId;
	private String filePath;
	// private String urlPath;
	private String fileName = "";

	public DownloadMyInfo(long userId, String filePath) {
		this.userId = userId;
		this.filePath = filePath;
		generateFileName();
	}

	private void generateFileName() {
		UUID id = UUID.randomUUID();
		long n = id.getLeastSignificantBits();
		long m = id.getMostSignificantBits();
		if (userId % 3 == 0) {
			fileName = String.valueOf((long) (userId * Math.random())) + "__"
					+ String.valueOf(Math.abs(m)) + ".txt";
		} else if (userId % 3 == 1) {
			fileName = String.valueOf((long) ((userId + 7) * Math.random()))
					+ "__" + String.valueOf(Math.abs(n)) + ".txt";
		} else if (userId % 6 == 2) {
			fileName = "__" + String.valueOf(Math.abs(n + 77))
					+ String.valueOf((long) ((userId + 777) * Math.random()))
					+ ".txt";
		} else {
			fileName = "__" + String.valueOf(Math.abs(m + 7))
					+ String.valueOf((long) ((userId + 7777) * Math.random()))
					+ ".txt";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		UserStorage executor = getStorageFactory().getUserStorage();
		UserEntity entity = executor.selectBasicInfo(userId).getEntity();
		entity.setAddrs(executor.selectAddr(userId));
		entity.setEmails(executor.selectEmail(userId));
		entity.setIms(executor.selectIM(userId));
		entity.setTels(executor.selectTel(userId));
		entity.setUrls(executor.selectURL(userId));
		System.out.println(entity.getName());
		FileWriter os;
		try {
			os = new FileWriter(new File(filePath, fileName));
			os.write("profile=" + entity.getId() + "\r\n");
			os
					.write("%%\t\tName\t\t\tDisplayName\t\t\tNickName\t\t\tGender\t\t\t"
							+ "Birthday\t\t\tRegisteredEmail\t\t\tNotes\r\n");
			os.write("  \t\t" + "\"" + entity.getName() + "\"\t::\t\t" + "\""
					+ entity.getDisplayname() + "\"\t::\t\t" + "\""
					+ entity.getNickName() + "\"\t::\t\t" + "\""
					+ entity.getGender() + "\"\t::\t\t");
			os.write("\"" + entity.getBirthday() == null ? "null" : entity
					.getBirthday().toString()
					+ "\"\t::\t\t"
					+ "\""
					+ entity.getRegisteredEmail()
					+ "\"\t::\t\t" + "\"" + entity.getNotes() + "\"\r\n");
			Collection<AddressData> details = entity.getAddrs();
			os.write("  \t\t\taddr=" + details.size() + "\r\n");
			for (AddressData temp : details) {
				os
						.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tzipcode\t\t\tvalue\r\n");
				os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
						+ "\"" + String.valueOf(temp.isPrimary())
						+ "\"\t::\t\t" + "\"" + temp.getZipcode().toString()
						+ "\"\t::\t\t" + "\"" + temp.getValue() + "\"\t\t"
						+ "\r\n");
				os.write("\r\n");
			}
			Collection<EmailData> detail1 = entity.getEmails();
			os.write("  \t\t\temail=" + detail1.size() + "\r\n");
			for (EmailData temp : detail1) {
				os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
				os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
						+ "\"" + String.valueOf(temp.isPrimary())
						+ "\"\t::\t\t" + "\"" + temp.getValue() + "\"\t\t"
						+ "\r\n");
				os.write("\r\n");
			}

			Collection<IMData> detail2 = entity.getIms();
			os.write("  \t\t\tim=" + detail2.size() + "\r\n");
			for (IMData temp : detail2) {
				os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
				os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
						+ "\"" + String.valueOf(temp.isPrimary())
						+ "\"\t::\t\t" + "\"" + temp.getValue() + "\"\t\t"
						+ "\r\n");
				os.write("\r\n");
			}

			Collection<PhoneNoData> detail3 = entity.getTels();
			os.write("  \t\t\ttel=" + detail3.size() + "\r\n");
			for (PhoneNoData temp : detail3) {
				os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
				os
						.write("  \t\t\t" + "\"" + temp.getType()
								+ "\"\t::\t\t" + "\""
								+ String.valueOf(temp.isPrimary())
								+ "\"\t::\t\t" + "\"" + temp.getValue() + "\""
								+ "\r\n");
				os.write("\r\n");
			}

			Collection<URLData> detail4 = entity.getUrls();
			os.write("  \t\t\turl=" + detail4.size() + "\r\n");
			for (URLData temp : detail4) {
				os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
				os
						.write("  \t\t\t" + "\"" + temp.getType()
								+ "\"\t::\t\t" + "\""
								+ String.valueOf(temp.isPrimary())
								+ "\"\t::\t\t" + "\"" + temp.getValue() + "\""
								+ "\r\n");
				os.write("\r\n");
			}
			os
					.write("%%************************************************************************************************%%\r\n\r\n");
			os.flush();
			// os.close();

			// os = new FileWriter(new File(filePath, fileName),true);
			ContactStorage executor2 = getStorageFactory().getContactStorage();
			Collection<CommonListEntry> contacts = executor2
					.selectOwnerContact(userId);
			os.write("contacts=" + contacts.size() + "\r\n");
			for (CommonListEntry ce : contacts) {
				ContactEntity contact;
				os.write(" \t\tcontact=" + ce.getEntity().getId() + "\r\n");
				contact = new ContactEntity(executor2.selectBasicInfo(
						ce.getEntity().getId()).getEntity());
				contact.setAddrs(executor2.selectAddr(contact.getId()));
				contact.setEmails(executor2.selectEmail(contact.getId()));
				contact.setIms(executor2.selectIM(contact.getId()));
				contact.setTels(executor2.selectTel(contact.getId()));
				contact.setUrls(executor2.selectURL(contact.getId()));
				// System.out.println(contact.getName());

				os.write("%%\t\tName\t\t\tNickName\t\t\tGender\t\t\t"
						+ "Birthday\t\t\tNotes\r\n");
				os.write("  \t\t" + "\"" + contact.getName() + "\"\t::\t\t"
						+ "\"" + contact.getNickName() + "\"\t::\t\t" + "\""
						+ contact.getGender() + "\"\t::\t\t");
				if (contact.getBirthday() != null)
					os.write("\"" + contact.getBirthday().toString()
							+ "\"\t::\t\t" + "\"" + contact.getNotes()
							+ "\"\r\n");
				else
					os.write("\"" + "null" + "\"\t::\t\t" + "\""
							+ contact.getNotes() + "\"\r\n");
				Collection<AddressData> cdetails = contact.getAddrs();
				os.write("  \t\t\taddr=" + cdetails.size() + "\r\n");
				for (AddressData temp : cdetails) {
					os
							.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tzipcode\t\t\tvalue\r\n");

					os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
							+ "\"" + String.valueOf(temp.isPrimary())
							+ "\"\t::\t\t" + "\"" + temp.getZipcode()
							+ "\"\t::\t\t" + "\"" + temp.getValue() + "\"\t\t"
							+ "\r\n");
					os.write("\r\n");
				}
				Collection<EmailData> cdetail1 = contact.getEmails();
				os.write("  \t\t\temail=" + cdetail1.size() + "\r\n");
				for (EmailData temp : cdetail1) {
					os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
					os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
							+ "\"" + String.valueOf(temp.isPrimary())
							+ "\"\t::\t\t" + "\"" + temp.getValue() + "\"\t\t"
							+ "\r\n");
					os.write("\r\n");
				}

				Collection<IMData> cdetail2 = contact.getIms();
				os.write("  \t\t\tim=" + cdetail2.size() + "\r\n");
				for (IMData temp : cdetail2) {
					os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
					os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
							+ "\"" + String.valueOf(temp.isPrimary())
							+ "\"\t::\t\t" + "\"" + temp.getValue() + "\"\t\t"
							+ "\r\n");
					os.write("\r\n");
				}

				Collection<PhoneNoData> cdetail3 = contact.getTels();
				os.write("  \t\t\ttel=" + cdetail3.size() + "\r\n");
				for (PhoneNoData temp : cdetail3) {
					os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
					os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
							+ "\"" + String.valueOf(temp.isPrimary())
							+ "\"\t::\t\t" + "\"" + temp.getValue() + "\""
							+ "\r\n");
					os.write("\r\n");
				}

				Collection<URLData> cdetail4 = contact.getUrls();
				os.write("  \t\t\turl=" + cdetail4.size() + "\r\n");
				for (URLData temp : cdetail4) {
					os.write("%%\t\t\ttype\t\t\tisPrimary\t\t\tvalue\r\n");
					os.write("  \t\t\t" + "\"" + temp.getType() + "\"\t::\t\t"
							+ "\"" + String.valueOf(temp.isPrimary())
							+ "\"\t::\t\t" + "\"" + temp.getValue() + "\""
							+ "\r\n");
					os.write("\r\n");
				}

			}
			os.close();
		} catch (IOException e) {
			throw new ServerErrorException("can't write the file");
		} catch (DataFormatException e) {

		}

		return fileName;
	}
}
