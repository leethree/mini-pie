package org.net9.minipie.server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.api.xml.MyContactXml;
import org.net9.minipie.server.api.xml.UpdateListXml;
import org.net9.minipie.server.data.PersonalCompleteContact;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.DeleteMyContact;
import org.net9.minipie.server.logic.operation.UpdateMyContact;
import org.net9.minipie.server.logic.operation.ViewMyContact;

public class PhonebookContact {
	@SuppressWarnings("unused")
	@Context
	private UriInfo uriInfo;

	private long contactId;

	/**
	 * Constructor
	 */
	public PhonebookContact() {
	}

	// public PhonebookContact(long contactId) {
	// this.contactId = contactId;
	// }

	/**
	 * @param contactId
	 *            the contactId to set
	 */
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public MyContactXml get() {
		return new MyContactXml(new Handler<PersonalCompleteContact>(
				new ViewMyContact(1L, contactId)).excute());
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public Response post(UpdateListXml updates) {
		new Handler<Void>(new UpdateMyContact(contactId, 1L, updates
				.getEntity())).excute();
		return Response.ok().build();
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new DeleteMyContact(1L, contactId)).excute();
		return Response.ok().build();
	}
}