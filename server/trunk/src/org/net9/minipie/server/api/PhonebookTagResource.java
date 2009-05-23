/**
 * PhonebookTagResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.data.api.PhonebookContactList;
import org.net9.minipie.server.data.api.PhonebookContactListEntry;
import org.net9.minipie.server.data.api.PhonebookUserList;
import org.net9.minipie.server.data.api.PhonebookUserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.tag.AttachTagToContact;
import org.net9.minipie.server.logic.operation.tag.AttachTagToUser;
import org.net9.minipie.server.logic.operation.tag.DeleteTag;
import org.net9.minipie.server.logic.operation.tag.EditTag;
import org.net9.minipie.server.logic.operation.tag.ListTaggedContact;
import org.net9.minipie.server.logic.operation.tag.ListTaggedUser;

import com.sun.jersey.api.core.ResourceContext;

/**
 * @author LeeThree
 */
public class PhonebookTagResource {
	@Context
	private UriInfo uriInfo;
	@Context
	protected ResourceContext resourceContext;

	private long tagId;

	/**
	 * Constructor
	 */
	public PhonebookTagResource() {
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	@GET
	@Path("user")
	@Produces( { "application/xml", "application/json" })
	public PhonebookUserList getUser() {
		return new PhonebookUserList(
				new Handler<Collection<PhonebookUserListEntry>>(
						new ListTaggedUser(1L, tagId)).execute(), uriInfo.getAbsolutePath());
		// TODO uri
	}

	@POST
	@Path("user")
	public Response postUser(@FormParam("userid") long userId) {
		new Handler<Void>(new AttachTagToUser(1L, userId, tagId)).execute();
		return Response.ok().build();
	}

	@GET
	@Path("contact")
	@Produces( { "application/xml", "application/json" })
	public PhonebookContactList getContact() {
		return new PhonebookContactList(
				new Handler<Collection<PhonebookContactListEntry>>(
						new ListTaggedContact(1L, tagId)).execute(), uriInfo.getAbsolutePath());
		// TODO uri
	}

	@POST
	@Path("contact")
	public Response postContact(@FormParam("contactid") long contactId) {
		new Handler<Void>(new AttachTagToContact(1L, contactId, tagId))
				.execute();
		return Response.ok().build();
	}

	@PUT
	public Response put(@FormParam("tagname") String tagName) {
		new Handler<Void>(new EditTag(1L, tagId, tagName)).execute();
		return Response.ok().build();
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new DeleteTag(1L, tagId)).execute();
		return Response.ok().build();
	}
}
