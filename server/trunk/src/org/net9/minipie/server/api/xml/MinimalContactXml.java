/**
 * MinimalContactXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.MinimalContact;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "contact")
public class MinimalContactXml {
	private MinimalContact entity;
	private URI uri;

	/**
	 * Constructor
	 */
	public MinimalContactXml() {
	}

	public MinimalContactXml(MinimalContact contact, URI uri) {
		this.entity = contact;
		this.uri = UriBuilder.fromUri(uri).path(entity.getId() + "/").build();
	}

	@XmlAttribute
	public long getId() {
		return entity.getId();
	}

	@XmlElement
	public String getName() {
		return entity.getName();
	}

	@XmlElement
	public String getImage() {
		return entity.getImage();
	}

	/**
	 * @return the uri
	 */
	@XmlAttribute
	public URI getUri() {
		return uri;
	}
}
