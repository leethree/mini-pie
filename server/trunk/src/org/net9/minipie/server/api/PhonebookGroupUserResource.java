/**
 * PhonebookGroupUserResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.GroupUserList;
import org.net9.minipie.server.data.api.GroupUserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.ListGroupMember;

/**
 * @author LeeThree
 *
 */
public class PhonebookGroupUserResource extends BaseResource {
	
	private long groupId;

	/**
	 * Constructor
	 */
	public PhonebookGroupUserResource() {
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	@GET
	@Produces( { "application/xml", "application/json" })
	public GroupUserList get() {
		return new GroupUserList(new Handler<Collection<GroupUserListEntry>>(
				new ListGroupMember(getUserId(), groupId)).execute());
	}
}
