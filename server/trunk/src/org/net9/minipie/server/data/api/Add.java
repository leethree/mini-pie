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

import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.data.entity.Info;
import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author Seastar, LeeThree
 * 
 */
@XmlType(name = "add")
public class Add extends Update {
	private Info info;

	/**
	 * Constructor
	 */
	public Add() {
		System.out.println("add!");
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
	 * @return the info
	 */
	@XmlTransient
	public Info getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	@XmlElements( { @XmlElement(name = "address", type = AddressXml.class),
			@XmlElement(name = "email", type = EmailXml.class),
			@XmlElement(name = "im", type = IMXml.class),
			@XmlElement(name = "phone", type = PhoneXml.class),
			@XmlElement(name = "url", type = UrlXml.class) })
	public void setDetail(DetailedInfoXml detail) {
		System.out.println(detail);
		this.info = detail.getInfo();
	}
}
