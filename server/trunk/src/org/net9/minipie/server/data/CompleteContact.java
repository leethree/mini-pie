/**
 * CompleteContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import java.util.Collection;

//
/**
 * @author Seastar
 *
 */
public class CompleteContact {
	private BasicContact basicContact;
	private Collection<AddressData> addrs;
	private Collection<EmailData> emails;
	private Collection<IMData>	ims;
	private Collection<PhoneNoData> phoneNos;
	private Collection<URLData> urls;
	
	public CompleteContact(){
		basicContact=null;
		addrs=null;
		emails=null;
		ims=null;
		phoneNos=null;
		urls=null;
	}
	
	public CompleteContact(BasicContact basicContact,Collection<AddressData> addrs,
			Collection<EmailData> emails,Collection<IMData> ims,
			Collection<PhoneNoData> phoneNos,Collection<URLData> urls){
		setAddrs(addrs);
		setBasicContact(basicContact);
		setEmails(emails);
		setIMs(ims);
		setPhoneNos(phoneNos);
		setURLs(urls);
	}
	
	/**
	 * @return the id
	 */
	public Long getId(){
		return basicContact.getId();
	}
	public String getNickName(){
		return basicContact.getNickName();
	}
	public String getImage(){
		return basicContact.getImage();
	}
	/**
	 * @return the addrs
	 */
	public Collection<AddressData> getAddrs() {
		return addrs;
	}
	/**
	 * @return the basicContact
	 */
	public BasicContact getBasicContact() {
		return basicContact;
	}
	/**
	 * @return the emails
	 */
	public Collection<EmailData> getEmails() {
		return emails;
	}
	/**
	 * @return the ims
	 */
	public Collection<IMData> getIms() {
		return ims;
	}
	/**
	 * @return the phoneNos
	 */
	public Collection<PhoneNoData> getPhoneNos() {
		return phoneNos;
	}
	/**
	 * @return the urls
	 */
	public Collection<URLData> getUrls() {
		return urls;
	}
	/**
	 * @param addrs the addrs to set
	 */
	public void setAddrs(Collection<AddressData> addrs) {
		this.addrs = addrs;
	}
	/**
	 * @param basicContact the basicContact to set
	 */
	public void setBasicContact(BasicContact basicContact) {
		this.basicContact = basicContact;
	}
	/**
	 * @param emails the emails to set
	 */
	public void setEmails(Collection<EmailData> emails) {
		this.emails = emails;
	}
	/**
	 * @param ims the ims to set
	 */
	public void setIMs(Collection<IMData> ims) {
		this.ims = ims;
	}
	/**
	 * @param phoneNos the phoneNos to set
	 */
	public void setPhoneNos(Collection<PhoneNoData> phoneNos) {
		this.phoneNos = phoneNos;
	}

	/**
	 * @param urls the urls to set
	 */
	public void setURLs(Collection<URLData> urls) {
		this.urls = urls;
	}
	
}
