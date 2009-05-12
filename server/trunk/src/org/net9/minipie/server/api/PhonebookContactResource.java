package org.net9.minipie.server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.data.api.ErrorReport;
import org.net9.minipie.server.data.api.PhonebookCompleteContact;
import org.net9.minipie.server.data.api.UpdateList;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.DeleteMyContact;
import org.net9.minipie.server.logic.operation.UpdateMyContact;
import org.net9.minipie.server.logic.operation.ViewMyContact;

public class PhonebookContactResource {
	@SuppressWarnings("unused")
	@Context
	private UriInfo uriInfo;

	private long contactId;

	/**
	 * Constructor
	 */
	public PhonebookContactResource() {
	}

	/**
	 * @param contactId
	 *            the contactId to set
	 */
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookCompleteContact get() {
		return new Handler<PhonebookCompleteContact>(new ViewMyContact(1L,
				contactId)).excute();
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public Response post(UpdateList updates) {
		new Handler<ErrorReport>(new UpdateMyContact(contactId, 1L, updates
				.getUpdates())).excute();
		return Response.ok().build();
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new DeleteMyContact(1L, contactId)).excute();
		return Response.ok().build();
	}
}