package org.net9.minipie.server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.api.xml.ContactXML;

/**
 * @author LeeThree
 * 
 */
@Path("/contact")
public class ContactResource {
	@Context
	private UriInfo uriInfo;

	@GET
	@Produces( { "application/xml", "application/json" })
	public ContactXML get() {
		return new ContactXML(12, "test", uriInfo.getAbsolutePath());
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public void post(ContactXML contact) {
		System.out.println(contact);
	}

}