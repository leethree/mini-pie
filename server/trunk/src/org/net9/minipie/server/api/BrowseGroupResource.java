/**
 * PhonebookGroupResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.Group;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.ViewGroupInfo;
import org.net9.minipie.server.logic.operation.group.admin.DisbandGroup;

/**
 * @author LeeThree
 * 
 */
public class BrowseGroupResource extends BaseResource {
	private long groupId;

	/**
	 * Constructor
	 */
	public BrowseGroupResource() {
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
	public Group get() {
		return new Handler<Group>(new ViewGroupInfo(groupId)).execute();
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new DisbandGroup(getUserId(), groupId)).execute();
		return Response.ok().build();
	}

	@Path("user")
	public PhonebookGroupUserResource getGroupUsers() {
		PhonebookGroupUserResource user = getSubResource(PhonebookGroupUserResource.class);
		user.setGroupId(groupId);
		return user;
	}

	@Path("contact")
	public PhonebookGroupContactResource getGroupContacts() {
		PhonebookGroupContactResource contact = getSubResource(PhonebookGroupContactResource.class);
		contact.setGroupId(groupId);
		return contact;
	}
}
