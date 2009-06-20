/**
 * PersonalProfile.java
 *     in package: * org.net9.minipie.server.data.api
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
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Permission;

/**
 * @author Seastar, LeeThree
 */
@XmlRootElement(name = "profile")
public class PersonalProfile {
	private UserEntity entity;

	/**
	 * Constructor
	 */
	public PersonalProfile() {
	}

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

	@XmlElement(name = "birth_date_perm")
	public String getBirthdayPermission() {
		return entity.getBirthdatePermission().toString();
	}

	@XmlElement(name = "birth_year_perm")
	public String getBirthyearPermission() {
		return entity.getBirthyearPermission().toString();
	}

	/**
	 * @return the gender
	 */
	@XmlElement
	public String getGender() {
		if (entity.getGender() == null)
			return null;
		else
			return entity.getGender().toString();
	}

	@XmlElement(name = "gender_perm")
	public String getGenderPermission() {
		return entity.getGenderPermission().toString();
	}

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getId();
	}

	@XmlElement(name = "register_email")
	public String getRegisterEmail() {
		return entity.getRegisteredEmail();
	}

	/**
	 * @return the image
	 */
	@XmlElement
	public String getImage() {
		return entity.getImage();
	}

	/**
	 * @return the nickname
	 */
	@XmlElement(name = "nickname")
	public String getNickName() {
		return entity.getNickName();
	}

	@XmlElement(name = "name")
	public String getDisplayName() {
		return entity.getDisplayname();
	}

	@XmlElement(name = "add_as_contact_perm")
	public String getAddAsContactPermission() {
		return entity.getAddAsContactPermission().toString();
	}

	/**
	 * @return the name
	 */
	@XmlElement(name = "username")
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
	@XmlElements( {
			@XmlElement(name = "address", type = ProfileAddressXml.class),
			@XmlElement(name = "email", type = ProfileEmailXml.class),
			@XmlElement(name = "im", type = ProfileIMXml.class),
			@XmlElement(name = "phone", type = ProfilePhoneXml.class),
			@XmlElement(name = "url", type = ProfileUrlXml.class) })
	public Collection<ProfileDetailedInfoXml> getDetails() {
		Collection<ProfileDetailedInfoXml> collection = new ArrayList<ProfileDetailedInfoXml>();
		for (AddressData address : entity.getAddrs()) {
			collection.add(new ProfileAddressXml(address));
		}
		for (EmailData email : entity.getEmails()) {
			collection.add(new ProfileEmailXml(email));
		}
		for (IMData im : entity.getIms()) {
			collection.add(new ProfileIMXml(im));
		}
		for (PhoneNoData phone : entity.getTels()) {
			collection.add(new ProfilePhoneXml(phone));
		}
		for (URLData url : entity.getUrls()) {
			collection.add(new ProfileUrlXml(url));
		}
		return collection;
	}

}
