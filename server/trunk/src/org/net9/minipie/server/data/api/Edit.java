/**
 * Edit.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.constant.InfoField;
import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author Seastar, LeeThree
 * 
 */
@XmlType(name = "edit")
public class Edit extends Update {
	private InfoField field;
	private long id;
	private String value;

	/**
	 * Constructor
	 */
	public Edit() {
		
	}

	@XmlAttribute
	public void setType(String type) {
		try {
			super.setType(InfoType.valueOf(type.toUpperCase()));
		} catch (IllegalArgumentException e) {
			throw new InvalidRequestException("Invalid information type.");
		}
	};

	/**
	 * @return the field
	 */
	@XmlTransient
	public InfoField getInfoField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	@XmlAttribute(required = true)
	public void setField(String field) {
		try {
			this.field = InfoField.valueOf(field.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidRequestException("Invalid information field.");
		}
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@XmlElement
	public void setId(long id) {
		if (id < 0) {
			throw new InvalidRequestException("id is illegal");
		}
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}
}
