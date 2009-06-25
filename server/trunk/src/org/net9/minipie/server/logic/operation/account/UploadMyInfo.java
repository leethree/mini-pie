/**
 * UploadMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.UploadWizard;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class UploadMyInfo extends Command<String> {
	private Long userId;
	private InputStream in;
	private String filePath;
	private String fileName;

	public UploadMyInfo(Long userId, InputStream in, String filePath) {
		this.userId = userId;
		this.in = in;
		this.filePath = filePath;

	}

	// public
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		UserStorage executor = getStorageFactory().getUserStorage();
		ContactStorage executor2 = getStorageFactory().getContactStorage();
		File tempFile;
		String result = "";
		try {
			fileName = userId + "up__." + "txt";
			tempFile = new File(filePath, fileName);
			FileOutputStream ou = new FileOutputStream(tempFile);
			byte[] buffer = new byte[2048];
			int byteread;
			while ((byteread = in.read(buffer)) != -1) {
				ou.write(buffer, 0, byteread);
			}
			ou.close();
			in.close();

		} catch (IOException e) {
			throw new ServerErrorException("operation failed");
		}
		UploadWizard up = new UploadWizard();
		up.upload(tempFile);
		UserEntity pf = up.getMyProfile();
		if (pf != null) {
			if (pf.getBirthday() != null) {
				executor.editBasicInfo(userId, InfoField.BIRTHDAY, pf
						.getBirthday().toString());
				result = result + "user birthday updated\r\n";
			}
			if (pf.getGender() != null) {
				executor.editBasicInfo(userId, InfoField.GENDER, pf.getGender()
						.toString());
				result = result + "user gender updated\r\n";
			}
			if (pf.getDisplayname() != null) {
				executor.editBasicInfo(userId, InfoField.DISPLAYNAME, pf
						.getDisplayname());
				result = result + "user displayname updated\r\n";
			}
			if (pf.getNickName() != null) {
				executor.editBasicInfo(userId, InfoField.NICKNAME, pf
						.getNickName());
				result = result + "user nickname updated\r\n";
			}
			if (pf.getNotes() != null) {
				executor.editBasicInfo(userId, InfoField.NOTE, pf.getNotes());
				result = result + "user note info updated\r\n";
			}

			Collection<AddressData> temp1 = pf.getAddrs();
			if (temp1 != null && temp1.size() != 0) {
				for (AddressData addr : temp1) {
					executor.addAddr(userId, addr);
				}
				result = result + "user address info updated: " + temp1.size()
						+ " address imported\r\n";
			}
			Collection<EmailData> temp5 = pf.getEmails();
			if (temp5 != null && temp5.size() != 0) {
				for (EmailData addr : temp5) {
					executor.addEmail(userId, addr);
				}
				result = result + "user email info updated: " + temp5.size()
						+ " email imported\r\n";
			}
			Collection<IMData> temp2 = pf.getIms();
			if (temp2 != null && temp2.size() != 0) {
				for (IMData addr : temp2) {
					executor.addIM(userId, addr);
				}
				result = result + "user im info updated: " + temp2.size()
						+ " im imported\r\n";
			}
			Collection<PhoneNoData> temp3 = pf.getTels();
			if (temp3 != null && temp3.size() != 0) {
				for (PhoneNoData addr : temp3) {
					executor.addTel(userId, addr);
				}
				result = result + "user phone info updated: " + temp3.size()
						+ " phones imported\r\n";
			}
			Collection<URLData> temp4 = pf.getUrls();
			if (temp4 != null && temp4.size() != 0) {
				for (URLData addr : temp4) {
					executor.addURL(userId, addr);
				}
				result = result + "user url info updated: " + temp4.size()
						+ " url imported\r\n";
			}

		}
		// ***************************************************************************//
		Collection<ContactEntity> contacts = up.getImportedContacts();
		if (contacts.size() != 0) {
			for (ContactEntity ce : contacts) {
				long id = executor2.addUserContact(userId, ce.getName());
				if (ce.getBirthday() != null)
					executor2.editBasicInfo(id, InfoField.BIRTHDAY, ce
							.getBirthday().toString());
				if (ce.getGender() != null)
					executor2.editBasicInfo(id, InfoField.GENDER, ce
							.getGender().toString());
				if (ce.getNickName() != null)
					executor2.editBasicInfo(id, InfoField.NICKNAME, ce
							.getNickName());
				if (ce.getNotes() != null)
					executor2.editBasicInfo(id, InfoField.NOTE, ce.getNotes());
				Collection<AddressData> tmp1 = ce.getAddrs();
				if (tmp1 != null && tmp1.size() != 0) {
					for (AddressData addr : tmp1) {
						executor2.addAddr(id, addr);
					}
				}
				Collection<EmailData> tmp5 = ce.getEmails();
				if (tmp5 != null && tmp5.size() != 0) {
					for (EmailData addr : tmp5) {
						executor2.addEmail(id, addr);
					}
				}
				Collection<IMData> tmp2 = ce.getIms();
				if (tmp2 != null && tmp2.size() != 0) {
					for (IMData addr : tmp2) {
						executor2.addIM(id, addr);
					}
				}
				Collection<PhoneNoData> tmp3 = ce.getTels();
				if (tmp3 != null && tmp3.size() != 0) {
					for (PhoneNoData addr : tmp3) {
						executor2.addTel(id, addr);
					}
				}
				Collection<URLData> tmp4 = ce.getUrls();
				if (tmp4 != null && tmp4.size() != 0) {
					for (URLData addr : tmp4) {
						executor2.addURL(id, addr);
					}
				}
				result = result + "user contact added: id " + id;
			}
		}
		tempFile.delete();
		return result;

	}

}
