/**
 * CompleteContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.Gender;

/**
 * @author Seastar, LeeThree
 * TODO
 */
public class CompleteContact {
	private ContactEntity entity;
	private long shareByUserId;
	private String shareByUserName;
	
	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public CompleteContact(ContactEntity entity,long sharedId,String sharedName) {
		this.entity = entity;
		this.shareByUserId=sharedId;
		this.shareByUserName=sharedName;
	}

	
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		if (entity.getBirthday() == null)
			return null;
		else
			return entity.getBirthday().toString();
	}



	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return entity.getGender();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return entity.getId();
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



	/**
	 * @return the nickName
	 */
	public String getName() {
		return entity.getName();
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return entity.getNotes();
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		if (entity.getRelationship() == null)
			return null;
		else
			return entity.getRelationship().toString();
	}

	/**
	 * @return the addrs
	 */
	public Collection<AddressData> getAddrs() {
		return entity.getAddrs();
	}

	/**
	 * @return the emails
	 */
	public Collection<EmailData> getEmails() {
		return entity.getEmails();
	}

	/**
	 * @return the ims
	 */
	public Collection<IMData> getIms() {
		return entity.getIms();
	}

	/**
	 * @return the phoneNos
	 */
	public Collection<PhoneNoData> getTels() {
		return entity.getTels();
	}

	/**
	 * @return the urls
	 */
	public Collection<URLData> getUrls() {
		return entity.getUrls();
	}
	
	/**
	 * @return the shareByUserId
	 */
	public long getShareByUserId() {
		return shareByUserId;
	}
	/**
	 * @return the shareByUserName
	 */
	public String getShareByUserName() {
		return shareByUserName;
	}
}
