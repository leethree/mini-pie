/**
 * GroupUserList.java
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
@XmlRootElement(name = "users")
public class GroupUserList {
	private Collection<GroupUserListEntry> list;

	/**
	 * Constructor
	 */
	public GroupUserList() {
	}

	/**
	 * Constructor
	 */
	public GroupUserList(Collection<GroupUserListEntry> list) {
		this.list = list;
	}

	/**
	 * @return the list
	 */
	@XmlElement(name = "user")
	public Collection<GroupUserListEntry> getList() {
		return list;
	}
}
