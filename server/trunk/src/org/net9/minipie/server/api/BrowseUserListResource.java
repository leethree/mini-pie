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
import javax.ws.rs.QueryParam;

import org.net9.minipie.server.data.api.UserList;
import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.user.SearchAllUser;

/**
 * @author LeeThree
 * 
 */
public class BrowseUserListResource extends BaseResource {

	@GET
	@Path("search")
	@Produces( { "application/xml", "application/json" })
	public UserList search(@QueryParam("q") String query) {
		return new UserList(new Handler<Collection<UserListEntry>>(
				new SearchAllUser(query)).execute(), getResourceUrl());
	}

	@Path("{id}")
	public BrowseUserResource getUser(@PathParam("id") long id) {
		BrowseUserResource user = getSubResource(BrowseUserResource.class);
		user.setUserId(id);
		return user;
	}
}
