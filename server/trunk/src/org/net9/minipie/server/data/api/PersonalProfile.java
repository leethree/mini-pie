/**
 * PersonalProfile.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.Permission;

/**
 * @author Seastar, LeeThree TODO
 */
public class PersonalProfile {
	private UserEntity entity;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public PersonalProfile(UserEntity entity) {
		this.entity = entity;
	}

	/**
	 * @return the birthday
	 */
	@XmlElement
	public String getBirthday() {
		if (entity.getBirthday() == null)
			return null;
		else
			return entity.getBirthday().toString();
	}

	public Permission getBirthdayPermission(){
		return entity.getBirthdatePermission();
	}
	
	public Permission getBirthyearPermission(){
		return entity.getBirthyearPermission();
	}
	
	
	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return entity.getGender();
	}
	

	public Permission getGenderPermission(){
		return entity.getGenderPermission();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return entity.getId();
	}

	public String getPassword(){
		return entity.getPassword();
	}
	
	public String getRegisterEmail(){
		return entity.getRegisteredEmail();
	}
	
	
	/**
	 * @return the image
	 */
	public String getImage() {
		return entity.getImage();
	}

	/**
	 * @return the name
	 */
	public String getNickName() {
		return entity.getNickName();
	}
	
	public String getDisplayName(){
		return entity.getDisplayname();
	}
	
	public AddAsContactPermission getAddAsContactPermission(){
		return entity.getAddAsContactPermission();
	}
		
	/**
	 * @return the nickName
	 */
	public String getName() {
		return entity.getName();
	}

	// /**
	// * @param notes
	// * the notes to set
	// */
	// public void setNotes(String notes) {
	// entity.setNotes(notes);
	// }

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return entity.getNotes();
	}


	/**
	 * @return the addrs
	 */
	public Collection<AddressData> getAddrs() {
		return entity.getAddrs();
	}

	// /**
	// * @param addrs
	// * the addrs to set
	// */
	// public void setAddrs(Collection<AddressData> addrs) {
	// entity.setAddrs(addrs);
	// }

	/**
	 * @return the emails
	 */
	public Collection<EmailData> getEmails() {
		return entity.getEmails();
	}

	// /**
	// * @param emails
	// * the emails to set
	// */
	// public void setEmails(Collection<EmailData> emails) {
	// entity.setEmails(emails);
	// }

	/**
	 * @return the ims
	 */
	public Collection<IMData> getIms() {
		return entity.getIms();
	}

	// /**
	// * @param ims
	// * the ims to set
	// */
	// public void setIMs(Collection<IMData> ims) {
	// entity.setIms(ims);
	// }

	/**
	 * @return the phoneNos
	 */
	public Collection<PhoneNoData> getTels() {
		return entity.getTels();
	}

	// /**
	// * @param phoneNos
	// * the phoneNos to set
	// */
	// public void setPhoneNos(Collection<PhoneNoData> tels) {
	// entity.setTels(tels);
	// }

	/**
	 * @return the urls
	 */
	public Collection<URLData> getUrls() {
		return entity.getUrls();
	}

	// /**
	// * @param urls
	// * the urls to set
	// */
	// public void setURLs(Collection<URLData> urls) {
	// entity.setUrls(urls);
	// }

}
