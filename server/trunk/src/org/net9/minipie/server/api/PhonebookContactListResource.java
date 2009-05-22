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

import org.net9.minipie.server.data.api.PhonebookContactListEntry;
import org.net9.minipie.server.data.api.PhonebookContactList;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.contact.AddMyContact;
import org.net9.minipie.server.logic.operation.contact.ListMyContact;

import com.sun.jersey.api.core.ResourceContext;

public class PhonebookContactListResource {
	@Context
	private UriInfo uriInfo;
	@Context
	protected ResourceContext resourceContext;

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookContactList get() {
		return new PhonebookContactList(new Handler<Collection<PhonebookContactListEntry>>(
				new ListMyContact(1L)).excute(), uriInfo.getAbsolutePath());
	}

	@POST
	public Response post(@FormParam("name") String name) {
		long newId = new Handler<Long>(new AddMyContact(1L, name)).excute();
		return Response.created(uriInfo.getAbsolutePath().resolve(newId + "/"))
				.build();
	}

	@Path("{id}")
	public PhonebookContactResource getContact(@PathParam("id") long id) {
		PhonebookContactResource contact = resourceContext
				.getResource(PhonebookContactResource.class);
		contact.setContactId(id);
		return contact;
	}
}