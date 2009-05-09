/**
 * AddressXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.AddressData;


/**
 * @author LeeThree
 * 
 */
@XmlType(name = "address")
public class AddressXml implements DetailedInfoXml {
	private AddressData entity;

	/**
	 * Constructor
	 */
	public AddressXml() {
		entity = new AddressData();
	}

	public AddressXml(AddressData entity) {
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
	@XmlElement
	public String getValue() {
		return entity.getValue();
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		entity.setValue(value);
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
	public void setPrimary(boolean isPrimary) {
		entity.setPrimary(isPrimary);
	}

	/**
	 * @return the formatted
	 */
	@XmlElement
	public String getFormatted() {
		return entity.getFormatted();
	}

	/**
	 * @param formatted
	 *            the formatted to set
	 */
	public void setFormatted(String formatted) {
		entity.setFormatted(formatted);
	}

	/**
	 * @return the zipcode
	 */
	@XmlElement
	public String getZipcode() {
		return entity.getZipcode();
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		entity.setZipcode(zipcode);
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

	@XmlTransient
	public AddressData getInfo() {
		return entity;
	}

}
