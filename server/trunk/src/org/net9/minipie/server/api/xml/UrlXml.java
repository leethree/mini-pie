/**
 * UrlXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.net9.minipie.server.data.URLData;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "url")
public class UrlXml implements DetailedInfoXml {
	private URLData entity;

	/**
	 * Constructor
	 */
	public UrlXml() {
		entity = new URLData();
	}

	public UrlXml(URLData entity) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.api.xml.DetailedInfoXml#getInfo()
	 */
	@XmlTransient
	public URLData getInfo() {
		return entity;
	}
}