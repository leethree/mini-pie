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
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.PhonebookUserList;
import org.net9.minipie.server.data.api.PhonebookUserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.user.AddUserAsContact;
import org.net9.minipie.server.logic.operation.user.ListMyUserContact;

/**
 * @author LeeThree
 * 
 */
public class PhonebookUserListResource extends BaseResource {

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookUserList get() {
		return new PhonebookUserList(
				new Handler<Collection<PhonebookUserListEntry>>(
						new ListMyUserContact(getUserId())).execute(), getResourceUrl());
	}

	@POST
	public Response post(@FormParam("userid") long targetId,
			@FormParam("message") String message) {
		new Handler<Void>(new AddUserAsContact(getUserId(), targetId, message))
				.execute();
		return Response.ok().build();
	}

	@Path("{id}")
	public PhonebookUserResource getUser(@PathParam("id") long id) {
		PhonebookUserResource user = getSubResource(PhonebookUserResource.class);
		user.setUserId(id);
		return user;
	}
}
