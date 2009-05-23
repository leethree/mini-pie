/**
 * TagXml.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.net9.minipie.server.data.entity.TagEntry;

/**
 * @author LeeThree
 * 
 */
@XmlType(name = "tag")
public class TagXml {
	private TagEntry tag;

	/**
	 * Constructor
	 */
	public TagXml() {
	}

	/**
	 * 
	 * Constructor
	 * @param tag
	 */
	public TagXml(TagEntry tag) {
		this.tag = tag;
	}

	@XmlAttribute
	public long getId() {
		return tag.getId();
	}

	@XmlValue
	public String getName() {
		return tag.getName();
	}
}
