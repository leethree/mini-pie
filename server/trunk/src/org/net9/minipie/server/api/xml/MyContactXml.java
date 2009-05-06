/**
 * MyContactXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.EmailData;
import org.net9.minipie.server.data.IMData;
import org.net9.minipie.server.data.PersonalCompleteContact;
import org.net9.minipie.server.data.PhoneNoData;
import org.net9.minipie.server.data.URLData;
import org.net9.minipie.server.data.constant.Gender;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "contact")
public class MyContactXml {
	private PersonalCompleteContact entity;

	/**
	 * Constructor
	 */
	public MyContactXml() {
	}

	public MyContactXml(PersonalCompleteContact entity) {
		this.entity = entity;
	}

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getCompleteContact().getId();
	}

	/**
	 * @return the name
	 */
	@XmlElement
	public String getName() {
		return entity.getCompleteContact().getBasicContact().getName();
	}

	@XmlElement(name = "nick")
	public String getNickName() {
		return entity.getCompleteContact().getNickName();
	}

	@XmlElement
	public String getImage() {
		return entity.getCompleteContact().getImage();
	}

	/**
	 * @return the gender
	 */
	@XmlElement
	public String getGender() {
		Gender gender = entity.getCompleteContact().getBasicContact()
				.getGender();
		if (gender != null)
			return gender.toString();
		else
			return null;
	}

	/**
	 * @return the birthday
	 */
	@XmlElement
	public Date getBirthday() {
		return entity.getCompleteContact().getBasicContact().getBirthday();
	}

	/**
	 * @return the notes
	 */
	@XmlElement
	public String getNotes() {
		return entity.getCompleteContact().getBasicContact().getNotes();
	}

	/**
	 * @return the relationship
	 */
	@XmlAttribute(name = "rel")
	public String getRelationship() {
		return entity.getCompleteContact().getBasicContact().getRelationship();
	}

	/**
	 * @return the perm
	 */
	@XmlAttribute(name = "permission")
	public String getPerm() {
		if (entity.getPerm() == null)
			return null;
		else
			return entity.getPerm().toString();
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
		for (AddressData address : entity.getCompleteContact().getAddrs()) {
			collection.add(new AddressXml(address));
		}
		for (EmailData email : entity.getCompleteContact().getEmails()) {
			collection.add(new EmailXml(email));
		}
		for (IMData im : entity.getCompleteContact().getIms()) {
			collection.add(new IMXml(im));
		}
		for (PhoneNoData phone : entity.getCompleteContact().getPhoneNos()) {
			collection.add(new PhoneXml(phone));
		}
		for (URLData url : entity.getCompleteContact().getUrls()) {
			collection.add(new UrlXml(url));
		}
		return collection;
	}
}
