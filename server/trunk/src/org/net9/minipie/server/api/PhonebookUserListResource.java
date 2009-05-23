/**
 * PhonebookUserListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.data.api.PhonebookUserList;
import org.net9.minipie.server.data.api.PhonebookUserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.user.AddUserAsContact;
import org.net9.minipie.server.logic.operation.user.ListMyUserContact;

import com.sun.jersey.api.core.ResourceContext;

/**
 * @author LeeThree
 *
 */
public class PhonebookUserListResource {
	@Context
	private UriInfo uriInfo;
	@Context
	protected ResourceContext resourceContext;

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookUserList get() {
		return new PhonebookUserList(new Handler<Collection<PhonebookUserListEntry>>(
				new ListMyUserContact(1L)).excute(), uriInfo.getAbsolutePath());
	}

	@POST
	public Response post(@FormParam("userid") long targetId,
			@FormParam("message") String message) {
		new Handler<Void>(new AddUserAsContact(1L, targetId, message)).excute();
		return Response.ok().build();
	}

	@Path("{id}")
	public PhonebookUserResource getContact(@PathParam("id") long id) {
		PhonebookUserResource user = resourceContext
				.getResource(PhonebookUserResource.class);
		user.setUserId(id);
		return user;
	}
}
