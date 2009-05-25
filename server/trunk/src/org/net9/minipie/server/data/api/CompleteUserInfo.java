/**
 * CommpleteUserInfo.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Gender;

/**
 * @author Seastar
 *
 */
public class CompleteUserInfo {
	private UserEntity entity;
	
	/**
	 * Constructor
	 */
	public CompleteUserInfo(UserEntity entity) {
		this.entity=entity;
		
		//this.shareByUserId=entity.getId();
	}
	
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
		
}
