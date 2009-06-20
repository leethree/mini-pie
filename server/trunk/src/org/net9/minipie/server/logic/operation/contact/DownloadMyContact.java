/**
 * DownloadMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation.contact
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

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
import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.Tag_ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class DownloadMyContact extends Command<String>{

	private long userId;
	private String filePath;
	// private String urlPath;
	private String fileName = "";

	public DownloadMyContact(long userId, String filePath) {
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
					+ String.valueOf(Math.abs(m)) + ".cvx";
		} else if (userId % 3 == 1) {
			fileName = String.valueOf((long) ((userId + 7) * Math.random()))
					+ "__" + String.valueOf(Math.abs(n)) + ".cvx";
		} else if (userId % 6 == 2) {
			fileName = "__" + String.valueOf(Math.abs(n + 77))
					+ String.valueOf((long) ((userId + 777) * Math.random()))
					+ ".cvx";
		} else {
			fileName = "__" + String.valueOf(Math.abs(m + 7))
					+ String.valueOf((long) ((userId + 7777) * Math.random()))
					+ ".cvx";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		Tag_ContactStorage executor2 = getStorageFactory().getTag_ContactStorage();
		Collection<CommonListEntry> cl = executor.selectOwnerContact(userId);
		for (CommonListEntry ce : cl) {
			long contactId=ce.getEntity().getId();
			ContactEntity entity = executor.selectBasicInfo(
					contactId).getEntity();
			entity.setAddrs(executor.selectAddr(contactId));
			entity.setEmails(executor.selectEmail(contactId));
			entity.setIms(executor.selectIM(contactId));
			entity.setTels(executor.selectTel(contactId));
			entity.setUrls(executor.selectURL(contactId));
			entity.setTags(executor2.selectTagsOfContact(contactId));
			
			try {
				FileWriter os = new FileWriter(new File(filePath, fileName));
				
				os.write("Basic Contact Info of " + ce.getEntity().getName()+ "\r\n");
				os.write("Name,NickName,Notes,Birthday,Gender,Gender\r\n");
				os.write(entity.getName() + "," + entity.getNickName() + ","
						+ "," + entity.getNotes() + ","
						+ entity.getBirthday().toString() + "," + "\r\n\r\n");

				os.write("Address of " + ce.getEntity().getName()+ "\r\n");
				for (AddressData temp : entity.getAddrs()) {
					os.write("value,type,primary,zipcode\r\n");
					os.write(temp.getValue() + "," + temp.getType() + ","
							+ String.valueOf(temp.isPrimary()) + ","							
							+ temp.getZipcode() + "\r\n");
					os.write("\r\n");
				}

				os.write("Email of " + ce.getEntity().getName()+ "\r\n");
				for (EmailData temp : entity.getEmails()) {
					os.write("value,type,primary\r\n");
					os.write(temp.getValue() + "," + temp.getType() + ","
							+ String.valueOf(temp.isPrimary()) + ","
							+  "\r\n");
					os.write("\r\n");
				}

				os.write("IM of " + ce.getEntity().getName()+ "\r\n");
				for (IMData temp : entity.getIms()) {
					os.write("value,type,primary\r\n");
					os.write(temp.getValue() + "," + temp.getType() + ","
							+ String.valueOf(temp.isPrimary()) + ","
							+  "\r\n");
					os.write("\r\n");
				}

				os.write("Telephone of " + ce.getEntity().getName()+ "\r\n");
				for (PhoneNoData temp : entity.getTels()) {
					os.write("value,type,primary\r\n");
					os.write(temp.getValue() + "," + temp.getType() + ","
							+ String.valueOf(temp.isPrimary()) + ","
							+  "\r\n");
					os.write("\r\n");
				}

				os.write("URL of " + ce.getEntity().getName()+ "\r\n");
				for (URLData temp : entity.getUrls()) {
					os.write("value,type,primary\r\n");
					os.write(temp.getValue().toString() + "," + temp.getType()
							+ "," + String.valueOf(temp.isPrimary()) + ","
							 + "\r\n");
					os.write("\r\n");
				}

				os.write("Tag of " + ce.getEntity().getName()+ "\r\n");
				for (TagEntry temp : entity.getTags()) {					
					os.write(temp.getName()+",");					
				}
				os.write("\r\n");
				
			} catch (IOException e) {
				throw new ServerErrorException("can't write the file");
			} catch (DataFormatException e) {
				throw new ServerErrorException("data format error");
			}
		}
		return fileName;
	}
}
