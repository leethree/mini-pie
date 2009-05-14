/**
 * PhonebookContactList.java
 *     in package: * org.net9.minipie.server.api.xmlhelper
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "contacts")
public class PhonebookContactList {
	private Collection<PhonebookContactListEntry> list;

	/**
	 * Constructor
	 */
	public PhonebookContactList() {
	}

	/**
	 * Constructor
	 */
	public PhonebookContactList(Collection<PhonebookContactListEntry> list, URI uri) {
		for (PhonebookContactListEntry entry : list) {
			entry.setUri(UriBuilder.fromUri(uri).path(entry.getId() + "/")
					.build());
		}
		this.list = list;
	}

	/**
	 * @return the list
	 */
	@XmlElement(name = "contact")
	public Collection<PhonebookContactListEntry> getList() {
		return list;
	}
}
