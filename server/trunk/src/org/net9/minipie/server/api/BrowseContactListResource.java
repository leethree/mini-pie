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
import javax.ws.rs.QueryParam;

import org.net9.minipie.server.data.api.ContactList;
import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.contact.SearchAllContact;

/**
 * @author LeeThree
 * 
 */
public class BrowseContactListResource extends BaseResource {

	@GET
	@Path("search")
	@Produces( { "application/xml", "application/json" })
	public ContactList search(@QueryParam("q") String query) {
		return new ContactList(new Handler<Collection<ContactListEntry>>(
				new SearchAllContact(getUserId(), query)).execute(),
				getResourceUrl());
	}

	@Path("{id}")
	public BrowseContactResource getContact(@PathParam("id") long id) {
		BrowseContactResource contact = getSubResource(BrowseContactResource.class);
		contact.setContactId(id);
		return contact;
	}
}
