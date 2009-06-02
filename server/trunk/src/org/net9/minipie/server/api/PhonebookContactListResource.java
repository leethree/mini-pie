package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.PhonebookContactList;
import org.net9.minipie.server.data.api.PhonebookContactListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.contact.AddMyContact;
import org.net9.minipie.server.logic.operation.contact.ListMyContact;

public class PhonebookContactListResource extends BaseResource{

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookContactList get() {
		return new PhonebookContactList(new Handler<Collection<PhonebookContactListEntry>>(
				new ListMyContact(getUserId())).execute(), getResourceUrl());
	}

	@POST
	public Response post(@FormParam("name") String name) {
		long newId = new Handler<Long>(new AddMyContact(getUserId(), name)).execute();
		return Response.created(getResourceUrl().resolve(newId + "/"))
				.build();
	}

	@Path("{id}")
	public PhonebookContactResource getContact(@PathParam("id") long id) {
		PhonebookContactResource contact = getSubResource(PhonebookContactResource.class);
		contact.setContactId(id);
		return contact;
	}
}