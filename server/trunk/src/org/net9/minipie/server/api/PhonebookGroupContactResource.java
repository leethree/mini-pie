/**
 * PhonebookGroupContactResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.ContactList;
import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.ListGroupContact;
import org.net9.minipie.server.logic.operation.group.ShareContactToGroup;
import org.net9.minipie.server.logic.operation.group.UnshareContact;

/**
 * @author LeeThree
 * 
 */
public class PhonebookGroupContactResource extends BaseResource {
	private long groupId;

	/**
	 * Constructor
	 */
	public PhonebookGroupContactResource() {
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
	public ContactList get() {
		return new ContactList(new Handler<Collection<ContactListEntry>>(
				new ListGroupContact(getUserId(), groupId)).execute(),
				getResourceUrl());
		// TODO uri
	}

	@POST
	public Response post(@FormParam("contactid") long contactId) {
		new Handler<Void>(new ShareContactToGroup(getUserId(), groupId,
				contactId)).execute();
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long contactId) {
		new Handler<Void>(new UnshareContact(getUserId(), groupId, contactId))
				.execute();
		return Response.ok().build();
	}
}
