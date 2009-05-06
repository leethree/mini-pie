/**
 * DeleteXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.net9.minipie.server.data.Delete;
import org.net9.minipie.server.data.Update;
import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "delete")
public class DeleteXml implements UpdateXml{
	private Delete entity;

	/**
	 * Constructor
	 */
	public DeleteXml() {
		entity = new Delete();
	}

	public DeleteXml(Delete entity) {
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
	
	@XmlTransient
	public Update getUpdate() {
		return entity;
	}
}
