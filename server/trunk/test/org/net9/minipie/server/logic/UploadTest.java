/**
 * UploadTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import java.io.File;
import java.util.Collection;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.logic.operation.util.UploadWizard;

/**
 * @author Riversand
 *
 */
public class UploadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UploadWizard uploader = new UploadWizard();
		uploader.upload(new File("H:/Win7_Doc/My Document/Work Data/java program/test.txt"));
		//uploader.upload(new File("H:/Win7_Doc/My Document/Work Data/java program/soft/pro/doc/test_import.txt"));
		UserEntity user = uploader.getMyProfile();
		System.out.println("*******************************my profile*********************************");
		System.out.println(user.getName());
		System.out.println(user.getDisplayname());
		System.out.println(user.getNickName());
		System.out.println(user.getGender());
		System.out.println(user.getBirthday());
		System.out.println(user.getRegisteredEmail());
		System.out.println(user.getNotes());
		Collection<AddressData> myAddress = user.getAddrs();
		System.out.println("**************address*********************");
		for (AddressData addressData : myAddress) {
			System.out.println(addressData.getType()+" "+addressData.getValue()+" "+addressData.getZipcode()+" "+addressData.isPrimary());
		}
		Collection<EmailData> myEmails = user.getEmails();
		System.out.println("**************email*********************");
		for (EmailData emailData : myEmails) {
			System.out.println(emailData.getType()+" "+emailData.getValue()+" "+emailData.isPrimary());
		}
		Collection<IMData> myIms = user.getIms();
		System.out.println("**************im*********************");
		for (IMData data : myIms) {
			System.out.println(data.getType()+" "+data.getValue()+" "+data.isPrimary());
		}
		Collection<PhoneNoData> myTels = user.getTels();
		System.out.println("**************tel*********************");
		for (PhoneNoData phoneNoData : myTels) {
			System.out.println(phoneNoData.getType()+" "+phoneNoData.getValue()+" "+phoneNoData.isPrimary());
		}
		Collection<URLData> myURL = user.getUrls();
		System.out.println("**************url*********************");
		for (URLData data : myURL) {
			try {
				System.out.println(data.getType()+" "+data.getValue()+" "+data.isPrimary());
			} catch (DataFormatException e) {
				System.out.println("dataformat error");
			}
		}
		
		for(ContactEntity entity: uploader.getImportedContacts()){
			System.out.println("*****************************************a contact**************************************");
			System.out.println(entity.getName()+" "+entity.getNickName()+" "+entity.getNotes()+" "+entity.getBirthday()+" "+entity.getGender());
			Collection<AddressData> address = entity.getAddrs();
			System.out.println("**************address*********************");
			for (AddressData addressData : address) {
				System.out.println(addressData.getType()+" "+addressData.getValue()+" "+addressData.getZipcode()+" "+addressData.isPrimary());
			}
			Collection<EmailData> emails = entity.getEmails();
			System.out.println("**************email*********************");
			for (EmailData emailData : emails) {
				System.out.println(emailData.getType()+" "+emailData.getValue()+" "+emailData.isPrimary());
			}
			Collection<IMData> ims = entity.getIms();
			System.out.println("**************im*********************");
			for (IMData data : ims) {
				System.out.println(data.getType()+" "+data.getValue()+" "+data.isPrimary());
			}
			Collection<PhoneNoData> tels = entity.getTels();
			System.out.println("**************tel*********************");
			for (PhoneNoData phoneNoData : tels) {
				System.out.println(phoneNoData.getType()+" "+phoneNoData.getValue()+" "+phoneNoData.isPrimary());
			}
			Collection<URLData> URL = entity.getUrls();
			System.out.println("**************url*********************");
			for (URLData data : URL) {
				try {
					System.out.println(data.getType()+" "+data.getValue()+" "+data.isPrimary());
				} catch (DataFormatException e) {
					System.out.println("dataformat error");
				}
			}
		}
		
	}

}
