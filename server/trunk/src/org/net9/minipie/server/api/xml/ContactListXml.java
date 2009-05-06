/**
 * ContactListXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.net9.minipie.server.data.MinimalContact;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "contacts")
public class ContactListXml {
	private Collection<MinimalContact> list;
	private URI path;

	/**
	 * Constructor
	 */
	public ContactListXml() {
	}

	/**
	 * Constructor
	 */
	public ContactListXml(Collection<MinimalContact> list, URI path) {
		this.list = list;
		this.path = path;
	}

	@XmlElement(name = "contact")
	public Collection<MinimalContactXml> getContactList() {
		Collection<MinimalContactXml> listXml = new ArrayList<MinimalContactXml>();
		for (MinimalContact contact : list) {
			listXml.add(new MinimalContactXml(contact, path));
		}
		return listXml;
	}

}
