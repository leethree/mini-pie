/**
 * CommonEntity.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.Birthdate;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
public class CommonEntity {
	private long id;
	private String name;
	private String image;
	private String nickName;
	private Gender gender;
	private Birthdate birthday;
	private String notes;
	private Collection<AddressData> addrs;
	private Collection<EmailData> emails;
	private Collection<IMData> ims;
	private Collection<PhoneNoData> tels;
	private Collection<URLData> urls;
	private Collection<TagEntry> tags;

	/**
	 * @return the id
	 */
	public CommonEntity(){
		addrs=new ArrayList<AddressData>();
		emails=new ArrayList<EmailData>();
		ims=new ArrayList<IMData>();
		tels=new ArrayList<PhoneNoData>();
		urls=new ArrayList<URLData>();
		tags=new ArrayList<TagEntry>();
	}
	
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 * @throws DataFormatException
	 */
	public void setId(long id) throws DataFormatException {
		this.id = Formatter.checkId(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set (not nullable)
	 */
	public void setName(String name) {
		if (name == null)
			throw new ServerErrorException("The name should not be null.");
		this.name = Formatter.compact(name);
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set (nullable)
	 * @throws DataFormatException
	 */
	public void setImage(String image) throws DataFormatException {
		if (image == null)
			this.image = null;
		else
			this.image = Formatter.formatImage(image);
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set (nullable)
	 */
	public void setNickName(String nickName) {
		if (nickName == null)
			this.nickName = null;
		else
			this.nickName = Formatter.compact(nickName);
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set (nullable)
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public Birthdate getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set (nullable)
	 */
	public void setBirthday(Birthdate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set (nullable)
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the addrs
	 */
	public Collection<AddressData> getAddrs() {
		return addrs;
	}

	/**
	 * @param addrs
	 *            the addrs to set (nullable)
	 */
	public void setAddrs(Collection<AddressData> addrs) {
		this.addrs = addrs;
	}

	/**
	 * @return the emails
	 */
	public Collection<EmailData> getEmails() {
		return emails;
	}

	/**
	 * @param emails
	 *            the emails to set (nullable)
	 */
	public void setEmails(Collection<EmailData> emails) {
		this.emails = emails;
	}

	/**
	 * @return the ims
	 */
	public Collection<IMData> getIms() {
		return ims;
	}

	/**
	 * @param ims
	 *            the ims to set (nullable)
	 */
	public void setIms(Collection<IMData> ims) {
		this.ims = ims;
	}

	/**
	 * @return the tels
	 */
	public Collection<PhoneNoData> getTels() {
		return tels;
	}

	/**
	 * @param tels
	 *            the tels to set (nullable)
	 */
	public void setTels(Collection<PhoneNoData> tels) {
		this.tels = tels;
	}

	/**
	 * @return the urls
	 */
	public Collection<URLData> getUrls() {
		return urls;
	}

	/**
	 * @param urls
	 *            the urls to set (nullable)
	 */
	public void setUrls(Collection<URLData> urls) {
		this.urls = urls;
	}

	/**
	 * @return the tags
	 */
	public Collection<TagEntry> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set (nullable)
	 */
	public void setTags(Collection<TagEntry> tags) {
		this.tags = tags;
	}
}
