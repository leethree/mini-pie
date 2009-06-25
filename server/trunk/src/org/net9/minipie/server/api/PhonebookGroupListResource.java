/**
 * PhonebookGroupListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.net9.minipie.server.data.api.GroupList;
import org.net9.minipie.server.data.api.GroupListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.JoinGroup;
import org.net9.minipie.server.logic.operation.group.ListMyGroups;

/**
 * @author LeeThree
 * 
 */
public class PhonebookGroupListResource extends BaseResource {

	@GET
	@Produces( { "application/xml", "application/json" })
	public GroupList get() {
		return new GroupList(new Handler<Collection<GroupListEntry>>(
				new ListMyGroups(getUserId())).execute(), getResourceUrl());
	}

	@POST
	public Response post(@FormParam("groupid") long groupId,
			@FormParam("message") String message) {
		boolean ret = new Handler<Boolean>(new JoinGroup(getUserId(), groupId,
				message)).execute();
		if (ret)
			return Response.ok().build();
		else
			return Response.status(Status.ACCEPTED).build();
	}

	@Path("{id}")
	public PhonebookGroupResource getGroup(@PathParam("id") long id) {
		PhonebookGroupResource group = getSubResource(PhonebookGroupResource.class);
		group.setGroupId(id);
		return group;
	}
}
