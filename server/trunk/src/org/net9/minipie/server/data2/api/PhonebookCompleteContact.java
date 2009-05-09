/**
 * PhonebookCompleteContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.api;

import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data2.entity.AddressData;
import org.net9.minipie.server.data2.entity.ContactEntity;
import org.net9.minipie.server.data2.entity.EmailData;
import org.net9.minipie.server.data2.entity.IMData;
import org.net9.minipie.server.data2.entity.PhoneNoData;
import org.net9.minipie.server.data2.entity.Tag;
import org.net9.minipie.server.data2.entity.URLData;

/**
 * @author Seastar, LeeThree
 * 
 */
@XmlRootElement(name = "contact")
public class PhonebookCompleteContact {
	private ContactEntity entity;

	/**
	 * Constructor
	 */
	public PhonebookCompleteContact() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public PhonebookCompleteContact(ContactEntity entity) {
		this.entity = entity;
	}

	/**
	 * @return the birthday
	 */
	@XmlElement
	public Date getBirthday() {
		return entity.getBirthday();
	}

	// /**
	// * @param birthday
	// * the birthday to set
	// */
	// public void setBirthday(String birthday) {
	// entity.setBirthday(birthday);
	// }

	/**
	 * @return the gender
	 */
	@XmlElement
	public Gender getGender() {
		return entity.getGender();
	}

	// /**
	// * @param gender
	// * the gender to set
	// */
	// public void setGender(Gender gender) {
	// entity.setGender(gender);
	// }

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getId();
	}

	// /**
	// * @param id
	// * the id to set
	// */
	// public void setId(long id) {
	// entity.setId(id);
	// }

	/**
	 * @return the image
	 */
	@XmlElement
	public String getImage() {
		return entity.getImage();
	}

	// /**
	// * @param image
	// * the image to set
	// */
	// public void setImage(String image) {
	// entity.setImage(image);
	// }

	// /**
	// * @param name
	// * the name to set
	// */
	// public void setNickName(String nickName) {
	// entity.setNickName(nickName);
	// }

	/**
	 * @return the name
	 */
	@XmlElement
	public String getNickName() {
		return entity.getNickName();
	}

	// /**
	// * @param nickName
	// * the nickName to set
	// */
	// public void setName(String name) {
	// entity.setName(name);
	// }

	/**
	 * @return the nickName
	 */
	@XmlElement
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
	@XmlElement
	public String getNotes() {
		return entity.getNotes();
	}

	// /**
	// * @param relationship
	// * the relationship to set
	// */
	// public void setRelationship(String relationship) {
	// entity.setRelationship(relationship);
	// }

	/**
	 * @return the relationship
	 */
	@XmlElement
	public String getRelationship() {
		return entity.getRelationship();
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
	//	
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

	/**
	 * @return the perm
	 */
	public Permission getPermission() {
		return entity.getPermission();
	}

	// /**
	// * @param perm the perm to set
	// */
	// public void setPermission(Permission permission) {
	// entity.setPermission(permission);
	// }
	//	
	/**
	 * @return the tags
	 */
	public Collection<Tag> getTags() {
		return entity.getTags();
	}

	// /**
	// * @param tags the tags to set
	// */
	// public void setTags(Collection<Tag> tags) {
	// entity.setTags(tags);
	// }
}
