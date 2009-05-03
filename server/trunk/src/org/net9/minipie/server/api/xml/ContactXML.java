/**
 * ContactXML.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.Contact;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "contact")
public class ContactXML {

	private Contact entity;
	private URI uri;

	public ContactXML() {
		entity = new Contact();
	}

	/**
	 * Constructor
	 * 
	 * @param contact
	 * @param absolutePath
	 */
	public ContactXML(Contact contact, URI absolutePath) {
		this.entity = contact;
		this.uri = absolutePath;
	}

	@XmlElement(required = true)
	public String getName() {
		return entity.getName();
	}

	public void setName(String name) {
		entity.setName(name);
	}

	@XmlAttribute
	public Long getId() {
		return entity.getId();
	}

	public void setId(Long id) {
		entity.setId(id);
	}

	/**
	 * @return the uri
	 */
	@XmlAttribute
	public URI getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Contact:" + getId() + "," + getName() + "," + getUri();
	}
}
