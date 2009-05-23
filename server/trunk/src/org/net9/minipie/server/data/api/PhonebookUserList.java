/**
 * PhonebookUserList.java
 *     in package: * org.net9.minipie.server.data.api
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
@XmlRootElement(name = "users")
public class PhonebookUserList {
	private Collection<PhonebookUserListEntry> list;

	/**
	 * Constructor
	 */
	public PhonebookUserList() {
	}

	/**
	 * Constructor
	 */
	public PhonebookUserList(Collection<PhonebookUserListEntry> list, URI uri) {
		for (PhonebookUserListEntry entry : list) {
			entry.setUri(UriBuilder.fromUri(uri).path(entry.getId() + "/")
					.build());
		}
		this.list = list;
	}

	/**
	 * @return the list
	 */
	@XmlElement(name = "user")
	public Collection<PhonebookUserListEntry> getList() {
		return list;
	}
}
