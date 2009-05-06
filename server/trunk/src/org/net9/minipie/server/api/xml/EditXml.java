/**
 * EditXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.net9.minipie.server.data.Edit;
import org.net9.minipie.server.data.Update;
import org.net9.minipie.server.data.constant.InfoField;
import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "edit")
public class EditXml implements UpdateXml{
	private Edit entity;

	/**
	 * Constructor
	 */
	public EditXml() {
		entity = new Edit();
	}

	public EditXml(Edit entity) {
		this.entity = entity;
	}

	@XmlAttribute(required = true)
	public String getType() {
		return entity.getType().toString();
	}

	public void setType(String type) {
		try {
			entity.setType(InfoType.valueOf(type.toUpperCase()));
		} catch (IllegalArgumentException e) {
			throw new InvalidRequestException("Invalid information type.");
		}
	}

	/**
	 * @return the field
	 */
	@XmlAttribute(required = true)
	public String getField() {
		return entity.getField().toString();
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		try {
			entity.setField(InfoField.valueOf(field.toUpperCase()));
		} catch (IllegalArgumentException e) {
			throw new InvalidRequestException("Invalid information field.");
		}
	}

	/**
	 * @return the id
	 */
	@XmlElement(required = true)
	public long getId() {
		return entity.getId();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		entity.setId(id);
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
	
	@XmlTransient
	public Update getUpdate() {
		return entity;
	}
}
