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
public class UserList {
	private Collection<UserListEntry> list;

	/**
	 * Constructor
	 */
	public UserList() {
	}

	/**
	 * Constructor
	 */
	public UserList(Collection<UserListEntry> list, URI uri) {
		for (UserListEntry entry : list) {
			entry.setUri(UriBuilder.fromUri(uri).path(entry.getId() + "/")
					.build());
		}
		this.list = list;
	}

	/**
	 * @return the list
	 */
	@XmlElement(name = "user")
	public Collection<UserListEntry> getList() {
		return list;
	}
}
