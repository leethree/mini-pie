/**
 * AddressXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "address")
public class AddressXml implements DetailedInfoXml{
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
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		entity.setValue(value);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return entity.getValue();
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		entity.setValue(type);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return entity.getType();
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	@XmlAttribute
	public void setPrimary(boolean isPrimary) {
		entity.setPrimary(isPrimary);
	}

	/**
	 * @return the primary
	 */
	public boolean getPrimary() {
		return entity.getPrimary();
	}

	/**
	 * @param formatted
	 *            the formatted to set
	 */
	public void setFormatted(String formatted) {
		entity.setFormatted(formatted);
	}

	/**
	 * @return the formatted
	 */
	public String getFormatted() {
		return entity.getFormatted();
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		entity.setZipcode(zipcode);
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return entity.getZipcode();
	}

	/**
	 * @param perm
	 *            the perm to set
	 */
	@XmlAttribute(name = "permission")
	public void setPerm(Permission perm) {
		entity.setPerm(perm);
	}

	/**
	 * @return the perm
	 */
	public Permission getPerm() {
		return entity.getPerm();
	}
}
