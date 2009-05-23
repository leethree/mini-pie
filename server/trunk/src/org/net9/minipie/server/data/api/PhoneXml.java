/**
 * PhoneXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author LeeThree
 * 
 */
@XmlType(name = "phone")
public class PhoneXml implements DetailedInfoXml {
	private PhoneNoData entity;

	/**
	 * Constructor
	 */
	public PhoneXml() {
		entity = new PhoneNoData();
	}

	public PhoneXml(PhoneNoData entity) {
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

	@XmlTransient
	public PhoneNoData getInfo() {
		return entity;
	}
	
	/**
	 * Check data consistency
	 * @return this
	 */
	public PhoneXml checkThis() {
		setValue(getValue());
		setType(getType());
		setPrimary(getPrimary());
		return this;
	}
}