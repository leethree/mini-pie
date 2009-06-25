/**
 * PhonebookGroupResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.QuitGroup;

/**
 * @author LeeThree
 * 
 */
public class PhonebookGroupResource extends BaseResource {
	private long groupId;

	/**
	 * Constructor
	 */
	public PhonebookGroupResource() {
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	@DELETE
	public Response delete() {
		new Handler<Void>(new QuitGroup(getUserId(), groupId)).execute();
		return Response.ok().build();
	}
	
	@GET
	@Path("user")
	public PhonebookGroupUserResource getGroupUsers() {
		PhonebookGroupUserResource user = getSubResource(PhonebookGroupUserResource.class);
		user.setGroupId(groupId);
		return user;
	}

	@GET
	@Path("contact")
	public PhonebookGroupContactResource getGroupContacts() {
		PhonebookGroupContactResource contact = getSubResource(PhonebookGroupContactResource.class);
		contact.setGroupId(groupId);
		return contact;
	}
}
