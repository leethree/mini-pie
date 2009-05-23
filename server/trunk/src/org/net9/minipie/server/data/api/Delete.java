/**
 * Delete.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author Seastar, LeeThree
 * 
 */
@XmlType(name = "delete")
public class Delete extends Update {
	private long id;

	/**
	 * Constructor
	 */
	public Delete() {
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@XmlElement(required = true)
	public void setId(long id) {
		try {
			this.id = Formatter.checkId(id);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.data.api.Update#checkThis()
	 */
	@Override
	public Delete checkThis() {
		if (getType() == null)
			throw new InvalidRequestException("Invalid delete format: : type missing.");
		setId(getId());
		return this;
	}
}
