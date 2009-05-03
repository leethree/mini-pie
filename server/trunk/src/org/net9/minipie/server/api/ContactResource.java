/**
 * ContactResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.api.xml.ContactXML;
import org.net9.minipie.server.data.Contact;

/**
 * @author LeeThree
 * 
 */
@Path("/contact")
public class ContactResource {
	@Context
	private UriInfo uriInfo;

	/**
	 * Constructor
	 */
	public ContactResource() {
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public ContactXML get() {
		Contact contact = new Contact();
		contact.setName("test name");
		contact.setId(new Long(234));
		return new ContactXML(contact, uriInfo.getAbsolutePath());
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public void post(ContactXML contact) {
		System.out.println(contact);
	}

}