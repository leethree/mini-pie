/**
 * AddXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.Add;
import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.EmailData;
import org.net9.minipie.server.data.IMData;
import org.net9.minipie.server.data.PhoneNoData;
import org.net9.minipie.server.data.URLData;
import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.exception.UnknownServerException;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "add")
public class AddXml {
	private Add entity;

	/**
	 * Constructor
	 */
	public AddXml() {
		entity = new Add();
	}

	public AddXml(Add entity) {
		this.entity = entity;
	}

	@XmlAttribute
	public InfoType getType() {
		return entity.getType();
	}

	public void setType(InfoType type) {
		entity.setType(type);
	}

	@XmlElements( { @XmlElement(name = "address", type = AddressXml.class),
			@XmlElement(name = "email", type = EmailXml.class),
			@XmlElement(name = "im", type = IMXml.class),
			@XmlElement(name = "phone", type = PhoneXml.class),
			@XmlElement(name = "url", type = UrlXml.class) })
	public DetailedInfoXml getInfo() {
		InfoType type = getType();
		if (type == InfoType.ADDRESS)
			return new AddressXml((AddressData) entity.getInfo());
		else if (type == InfoType.EMAIL)
			return new EmailXml((EmailData) entity.getInfo());
		else if (type == InfoType.IM)
			return new IMXml((IMData) entity.getInfo());
		else if (type == InfoType.PHONE)
			return new PhoneXml((PhoneNoData) entity.getInfo());
		else if (type == InfoType.URL)
			return new UrlXml((URLData) entity.getInfo());
		else
			throw new UnknownServerException("InfoType mismatch");
	}

	public void setInfo(DetailedInfoXml infoXml) {
		// TODO
	}
}
