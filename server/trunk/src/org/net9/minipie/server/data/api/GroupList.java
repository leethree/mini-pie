/**
 * GroupList.java
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
@XmlRootElement(name = "groups")
public class GroupList {
	private Collection<GroupListEntry> list;
	
	/**
	 * Constructor
	 */
	public GroupList() {
	}
	
	/**
	 * Constructor
	 */
	public GroupList(Collection<GroupListEntry> list, URI uri) {
		for (GroupListEntry entry : list) {
			entry.setUri(UriBuilder.fromUri(uri).path(entry.getId() + "/")
					.build());
		}
		this.list = list;
	}
	
	/**
	 * @return the list
	 */
	@XmlElement(name = "group")
	public Collection<GroupListEntry> getList() {
		return list;
	}
}
