/**
 * Add.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.data.entity.Info;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author Seastar, LeeThree
 * 
 */
@XmlType(name = "add")
public class Add extends Update {
	private DetailedInfoXml info;

	/**
	 * Constructor
	 */
	public Add() {
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
	 * @return the info
	 */
	@XmlTransient
	public Info getInfo() {
		return info.getInfo();
	}

	/**
	 * @param info
	 *            the info to set
	 */
	@XmlElements( {
			@XmlElement(name = "address", type = AddressXml.class),
			@XmlElement(name = "email", type = EmailXml.class),
			@XmlElement(name = "im", type = IMXml.class),
			@XmlElement(name = "phone", type = PhoneXml.class),
			@XmlElement(name = "url", type = UrlXml.class) })
	public void setDetail(DetailedInfoXml detail) {
		this.info = detail;
	}
	
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.data.api.Update#checkThis()
	 */
	@Override
	public Add checkThis() {
		if (getType() == null)
			throw new InvalidRequestException("Invalid add format: type missing.");
		else if (info == null)
			throw new InvalidRequestException("Invalid add format: detailed info missing.");
		info.checkThis();
		return this;
	}
}
