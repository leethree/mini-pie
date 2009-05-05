/**
 * AddressXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "address")
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
	public String getType() {
		return entity.getType();
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		entity.setValue(type);
	}

	/**
	 * @return the primary
	 */
	@XmlAttribute
	public boolean getPrimary() {
		return entity.getPrimary();
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
		return entity.getPerm().toString();
	}

	/**
	 * @param perm
	 *            the perm to set
	 */
	public void setPerm(Permission perm) {
		entity.setPerm(perm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.api.xml.DetailedInfoXml#getInfo()
	 */
	@XmlTransient
	public AddressData getInfo() {
		return entity;
	}

}
