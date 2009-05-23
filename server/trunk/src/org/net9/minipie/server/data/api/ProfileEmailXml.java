/**
 * EmailXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author LeeThree
 * 
 */
@XmlType(name = "email")
public class ProfileEmailXml implements ProfileDetailedInfoXml {
	private EmailData entity;

	/**
	 * Constructor
	 */
	public ProfileEmailXml() {
		entity = new EmailData();
	}

	public ProfileEmailXml(EmailData entity) {
		this.entity = entity;
	}

	/**
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return entity.getId();
	}

	/**
	 * @return the value
	 */
	@XmlElement(required = true)
	public String getValue() {
		return entity.getValue();
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		try {
			entity.setValue(value);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/**
	 * @return the type
	 */
	@XmlElement
	public String getType() {
		return entity.getType();
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		entity.setType(type);
	}

	/**
	 * @return the primary
	 */
	@XmlAttribute
	public String getPrimary() {
		if (entity.isPrimary())
			return "true";
		else
			return null;
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	public void setPrimary(String isPrimary) {
		if (isPrimary == null)
			entity.setPrimary(false);
		else if (isPrimary.equals("true"))
			entity.setPrimary(true);
		else
			entity.setPrimary(false);
	}

	/**
	 * @return the perm
	 */
	@XmlAttribute(name = "permission")
	public String getPerm() {
		if (entity.getPermission() == null)
			return null;
		else
			return entity.getPermission().toString();
	}

	@XmlTransient
	public EmailData getInfo() {
		return entity;
	}
	
	/**
	 * Check data consistency
	 * @return this
	 */
	public ProfileEmailXml checkThis() {
		setValue(getValue());
		setType(getType());
		setPrimary(getPrimary());
		return this;
	}
}
