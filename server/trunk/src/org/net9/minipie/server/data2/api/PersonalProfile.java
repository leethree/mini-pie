/**
 * PersonalProfile.java
 *     in package: * org.net9.minipie.server.data2.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.api;

import java.util.Collection;
import java.util.Date;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data2.entity.AddressData;
import org.net9.minipie.server.data2.entity.ContactEntity;
import org.net9.minipie.server.data2.entity.EmailData;
import org.net9.minipie.server.data2.entity.IMData;
import org.net9.minipie.server.data2.entity.PhoneNoData;
import org.net9.minipie.server.data2.entity.URLData;

/**
 * @author Seastar, LeeThree
 *
 */
public class PersonalProfile {
	private ContactEntity entity; 

	/**
	 * Constructor
	 * @param entity
	 */
	public PersonalProfile(ContactEntity entity) {
		this.entity = entity;
	}

//	public PersonalProfile() {
//		entity=new ContactEntity();
//	}
//
//	public PersonalProfile(long id,  String name,String image, String nickName, 
//			Gender gender, String birthday,String notes, String relationship,
//			Collection<AddressData> addrs, Collection<EmailData> emails,
//			Collection<IMData> ims, Collection<PhoneNoData> phoneNos,
//			Collection<URLData> urls) {
//		entity.setId(id);
//		entity.setName(name);
//		entity.setImage(image);
//		entity.setNickName(nickName);
//		entity.setGender(gender);
//		entity.setBirthday(birthday);
//		entity.setNotes(notes);
//		entity.setRelationship(relationship);
//		entity.setAddrs(addrs);
//		entity.setEmails(emails);
//		entity.setIms(ims);
//		entity.setTels(phoneNos);
//		entity.setUrls(urls);
//	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return entity.getBirthday();
	}
	
//	/**
//	 * @param birthday
//	 *            the birthday to set
//	 */
//	public void setBirthday(String birthday) {
//		entity.setBirthday(birthday);
//	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return entity.getGender();
	}

//	/**
//	 * @param gender
//	 *            the gender to set
//	 */
//	public void setGender(Gender gender) {
//		entity.setGender(gender);
//	}

	/**
	 * @return the id
	 */
	public long getId() {
		return entity.getId();
	}

//	/**
//	 * @param id
//	 *            the id to set
//	 */
//	public void setId(long id) {
//		entity.setId(id);
//	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return entity.getImage();
	}

//	/**
//	 * @param image
//	 *            the image to set
//	 */
//	public void setImage(String image) {
//		entity.setImage(image);
//	}

//	/**
//	 * @param name
//	 *            the name to set
//	 */
//	public void setNickName(String nickName) {		
//		entity.setNickName(nickName);
//	}

	/**
	 * @return the name
	 */
	public String getNickName() {
		return entity.getNickName();
	}

//	/**
//	 * @param nickName
//	 *            the nickName to set
//	 */
//	public void setName(String name) {
//		entity.setName(name);
//	}

	/**
	 * @return the nickName
	 */
	public String getName() {
		return entity.getName();
	}

//	/**
//	 * @param notes
//	 *            the notes to set
//	 */
//	public void setNotes(String notes) {
//		entity.setNotes(notes);
//	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return entity.getNotes();
	}

//	/**
//	 * @param relationship
//	 *            the relationship to set
//	 */
//	public void setRelationship(String relationship) {		
//		entity.setRelationship(relationship);
//	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return entity.getRelationship();
	}


	/**
	 * @return the addrs
	 */
	public Collection<AddressData> getAddrs() {
		return entity.getAddrs();
	}
	
//	/**
//	 * @param addrs
//	 *            the addrs to set
//	 */
//	public void setAddrs(Collection<AddressData> addrs) {
//		entity.setAddrs(addrs);
//	}
	
	/**
	 * @return the emails
	 */
	public Collection<EmailData> getEmails() {
		return entity.getEmails();
	}
	
//	/**
//	 * @param emails
//	 *            the emails to set
//	 */
//	public void setEmails(Collection<EmailData> emails) {
//		entity.setEmails(emails);
//	}
	
	/**
	 * @return the ims
	 */
	public Collection<IMData> getIms() {
		return entity.getIms();
	}
	
//	/**
//	 * @param ims
//	 *            the ims to set
//	 */
//	public void setIMs(Collection<IMData> ims) {
//		entity.setIms(ims);
//	}
	
	/**
	 * @return the phoneNos
	 */
	public Collection<PhoneNoData> getTels() {
		return entity.getTels();
	}
	
//	/**
//	 * @param phoneNos
//	 *            the phoneNos to set
//	 */
//	public void setPhoneNos(Collection<PhoneNoData> tels) {
//		entity.setTels(tels);
//	}
	
	/**
	 * @return the urls
	 */
	public Collection<URLData> getUrls() {
		return entity.getUrls();
	}

//	/**
//	 * @param urls
//	 *            the urls to set
//	 */
//	public void setURLs(Collection<URLData> urls) {
//		entity.setUrls(urls);
//	}

}
