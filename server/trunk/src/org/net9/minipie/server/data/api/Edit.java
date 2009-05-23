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

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.exception.DataFormatException;
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

	@XmlAttribute(required = true)
	public void setType(String type) {
		try {
			super.setType(InfoType.value(type));
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

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
	 * @throws DataFormatException
	 */
	@XmlAttribute(required = true)
	public void setField(String field) {
		try {
			this.field = InfoField.value(field);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
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
		try {
			this.id = Formatter.checkId(id);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set (empty string is allowed, but null is not
	 *            allowed)
	 */
	@XmlElement(required = true)
	public void setValue(String value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.data.api.Update#checkThis()
	 */
	@Override
	public Edit checkThis() {
		if (getType() == null)
			throw new InvalidRequestException("Invalid edit format: type missing.");
		if (getInfoField() == null)
			throw new InvalidRequestException("Invalid edit format: field missing.");
		/**
		 * value == null means clear a nullable field, like type
		 */
//		if (getValue() == null)
//			throw new InvalidRequestException("Invalid edit format: no valid new value provided.");
		return this;
	}
}
