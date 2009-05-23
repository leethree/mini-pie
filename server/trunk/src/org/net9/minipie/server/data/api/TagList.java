/**
 * TagList.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "tags")
public class TagList {
	private Collection<TagXml> tags;

	/**
	 * Constructor
	 */
	public TagList() {
	}

	/**
	 * Constructor
	 */
	public TagList(Collection<TagXml> list) {
		tags = list;
	}

	/**
	 * @return the tags
	 */
	@XmlElement(name = "tag")
	public Collection<TagXml> getTags() {
		return tags;
	}
}
