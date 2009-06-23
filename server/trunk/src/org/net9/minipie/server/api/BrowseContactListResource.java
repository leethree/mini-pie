/**
 * BrowseContactListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.ContactListEntry;

/**
 * @author LeeThree
 * 
 */
public class BrowseContactListResource extends BaseResource {

	@GET
	@Path("search")
	@Produces( { "application/xml", "application/json" })
	public Collection<ContactListEntry> search() {
		// TODO
		return null;
	}

	@Path("{id}")
	public BrowseContactResource getContact(@PathParam("id") long id) {
		BrowseContactResource contact = getSubResource(BrowseContactResource.class);
		contact.setContactId(id);
		return contact;
	}
}
