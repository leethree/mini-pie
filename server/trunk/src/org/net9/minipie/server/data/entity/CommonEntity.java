/**
 * CommonEntity.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.net9.minipie.server.data.constant.Gender;

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
	private Date birthday;
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
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {			// 为什么这里是String? -- LeeThree
		if (birthday == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		ParsePosition parsePos = new ParsePosition(0);
		this.birthday = dateFormat.parse(birthday, parsePos);
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
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
	 *            the addrs to set
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
	 *            the emails to set
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
	 *            the ims to set
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
	 *            the tels to set
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
	 *            the urls to set
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
	 *            the tags to set
	 */
	public void setTags(Collection<TagEntry> tags) {
		this.tags = tags;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
