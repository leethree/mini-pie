/**
 * PhonebookGroupListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.CreateGroup;

/**
 * @author LeeThree
 * 
 */
public class BrowseGroupListResource extends BaseResource {

	@POST
	public Response post(@FormParam("groupname") String groupName) {
		long newId = new Handler<Long>(new CreateGroup(getUserId(), groupName))
				.execute();
		return Response.created(getResourceUrl().resolve(newId + "/")).build();
	}

	@Path("{id}")
	public BrowseGroupResource getGroup(@PathParam("id") long id) {
		BrowseGroupResource group = getSubResource(BrowseGroupResource.class);
		group.setGroupId(id);
		return group;
	}
}
