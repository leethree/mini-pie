package org.net9.minipie.server.api.xml;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class ContactXML {
	@XmlElement
	private String name;

	@XmlElement
	private int id;

	@XmlAttribute
	private URI uri;

	public ContactXML() {
	}

	public ContactXML(int id, String name, URI uri) {
		this.name = name;
		this.id = id;
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	/**
	 * @return the uri
	 */
	public URI getUri() {
		return uri;
	}

	@Override
	public String toString() {
		return "Contact:" + id + "," + name;
	}
}
