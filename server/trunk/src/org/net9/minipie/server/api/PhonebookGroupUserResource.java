/**
 * PhonebookGroupUserResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.net9.minipie.server.data.api.GroupUserList;
import org.net9.minipie.server.data.api.GroupUserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.ListGroupMember;
import org.net9.minipie.server.logic.operation.group.admin.AppointAdmin;
import org.net9.minipie.server.logic.operation.group.admin.InviteUser;

/**
 * @author LeeThree
 * 
 */
public class PhonebookGroupUserResource extends BaseResource {

	private long groupId;

	/**
	 * Constructor
	 */
	public PhonebookGroupUserResource() {
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public GroupUserList get() {
		return new GroupUserList(new Handler<Collection<GroupUserListEntry>>(
				new ListGroupMember(getUserId(), groupId)).execute());
	}

	@POST
	public Response post(@FormParam("userid") long userid,
			@FormParam("message") String message) {
		boolean ret = new Handler<Boolean>(new InviteUser(getUserId(), groupId,
				userid, message)).execute();
		if (ret)
			return Response.ok().build();
		else
			return Response.status(Status.ACCEPTED).build();
	}

	@PUT
	@Path("{id}")
	public Response put(@PathParam("id") long userId) {
		new Handler<Void>(new AppointAdmin(getUserId(), groupId, userId))
				.execute();
		return Response.ok().build();
	}
}
