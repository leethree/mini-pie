/**
 * IMXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.IMData;


/**
 * @author LeeThree
 * 
 */
@XmlType(name = "im")
public class IMXml implements DetailedInfoXml {
	private IMData entity;

	/**
	 * Constructor
	 */
	public IMXml() {
		entity = new IMData();
	}

	public IMXml(IMData entity) {
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
	public IMData getInfo() {
		return entity;
	}
}
