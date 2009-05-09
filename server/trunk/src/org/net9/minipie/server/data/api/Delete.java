/**
 * Delete.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.constant.InfoType;
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

	@XmlAttribute
	public void setType(String type) {
		try {
			super.setType(InfoType.valueOf(type.toUpperCase()));
		} catch (IllegalArgumentException e) {
			throw new InvalidRequestException("Invalid information type.");
		}
	};

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
}
