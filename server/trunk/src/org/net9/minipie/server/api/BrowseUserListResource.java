/**
 * BrowseUserListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.UserListEntry;

/**
 * @author LeeThree
 *
 */
public class BrowseUserListResource extends BaseResource {
	
	@GET
	@Path("search")
	@Produces( { "application/xml", "application/json" })
	public Collection<UserListEntry> search() {
		// TODO
		return null;
	}
	
	@Path("{id}")
	public BrowseUserResource getUser(@PathParam("id") long id) {
		BrowseUserResource user = getSubResource(BrowseUserResource.class);
		user.setUserId(id);
		return user;
	}
}
