/**
 * CompleteContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = "contact")
public class CompleteContact {
	private ContactEntity entity;
	private long shareByUserId;
	private String shareByUserName;
	
	/**
	 * Constructor
	 */
	public CompleteContact() {
	}
	
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
	@XmlElement
	public String getBirthday() {
		if (entity.getBirthday() == null)
			return null;
		else
			return entity.getBirthday().toString();
	}



	/**
	 * @return the gender
	 */
	@XmlElement
	public Gender getGender() {
		return entity.getGender();
	}

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getId();
	}

	/**
	 * @return the image
	 */
	@XmlElement
	public String getImage() {
		return entity.getImage();
	}

	/**
	 * @return the name
	 */
	@XmlElement(name = "nickname")
	public String getNickName() {
		return entity.getNickName();
	}



	/**
	 * @return the nickName
	 */
	@XmlElement
	public String getName() {
		return entity.getName();
	}

	/**
	 * @return the notes
	 */
	@XmlElement(name = "note")
	public String getNotes() {
		return entity.getNotes();
	}

	/**
	 * @return the detailed information
	 */
	@XmlElements( { @XmlElement(name = "address", type = AddressXml.class),
			@XmlElement(name = "email", type = EmailXml.class),
			@XmlElement(name = "im", type = IMXml.class),
			@XmlElement(name = "phone", type = PhoneXml.class),
			@XmlElement(name = "url", type = UrlXml.class) })
	public Collection<DetailedInfoXml> getDetails() {
		Collection<DetailedInfoXml> collection = new ArrayList<DetailedInfoXml>();
		for (AddressData address : entity.getAddrs()) {
			collection.add(new AddressXml(address));
		}
		for (EmailData email : entity.getEmails()) {
			collection.add(new EmailXml(email));
		}
		for (IMData im : entity.getIms()) {
			collection.add(new IMXml(im));
		}
		for (PhoneNoData phone : entity.getTels()) {
			collection.add(new PhoneXml(phone));
		}
		for (URLData url : entity.getUrls()) {
			collection.add(new UrlXml(url));
		}
		return collection;
	}
	
	/**
	 * @return the shareByUserId
	 */
	@XmlAttribute(name = "ownerid")
	public long getShareByUserId() {
		return shareByUserId;
	}
	/**
	 * @return the shareByUserName
	 */
	@XmlElement(name = "owner")
	public String getShareByUserName() {
		return shareByUserName;
	}
}
