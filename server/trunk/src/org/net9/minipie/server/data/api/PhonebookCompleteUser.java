/**
 * PhonebookCompleteUser.java
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
import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;

/**
 * @author Seastar, LeeThree
 *
 */
@XmlRootElement(name = "user")
public class PhonebookCompleteUser {
	private UserEntity entity;

	/**
	 * Constructor
	 */
	public PhonebookCompleteUser() {
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public PhonebookCompleteUser(UserEntity entity) {
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
	public String getGender() {
		if (entity.getGender() == null)
			return null;
		else
			return entity.getGender().toString();
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

	
	/**
	 * @return the name
	 */
	@XmlElement(name = "nickname")
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
	@XmlElement(name = "username")
	public String getName() {
		return entity.getName();
	}
	
	@XmlElement(name = "name")
	public String getDisplayName(){
		return entity.getDisplayname();
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
	@XmlElement(name = "note")
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
	@XmlAttribute(name = "rel")
	public String getRelationship() {
		if (entity.getRelationship() == null)
			return null;
		else
			return entity.getRelationship().toString();
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
	 * @return the perm
	 */
	@XmlAttribute
	public String getPermission() {
		if (entity.getPermission() == null)
			return null;
		else
			return entity.getPermission().toString();
	}
	
	/**
	 * 
	 * @return
	 */
	@XmlElement(name = "tag")
	public Collection<TagXml> getTags() {
		Collection<TagXml> tags = new ArrayList<TagXml>();
		for (TagEntry tagEntry : entity.getTags()) {
			tags.add(new TagXml(tagEntry));
		}
		return tags;
	}
}
