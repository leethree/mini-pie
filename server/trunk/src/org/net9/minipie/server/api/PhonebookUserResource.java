/**
 * PhonebookUserResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.PhonebookCompleteUser;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.user.RemoveUserContact;
import org.net9.minipie.server.logic.operation.user.ShareUser;
import org.net9.minipie.server.logic.operation.user.UpdateRelationship;
import org.net9.minipie.server.logic.operation.user.ViewMyUserContact;

/**
 * @author LeeThree
 * 
 */
public class PhonebookUserResource extends BaseResource {

	private long userId;

	/**
	 * Constructor
	 */
	public PhonebookUserResource() {
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookCompleteUser get() {
		return new Handler<PhonebookCompleteUser>(new ViewMyUserContact(
				getUserId(), userId)).execute();
	}

	/**
	 * change shared user permission
	 * 
	 * @return
	 */
	@PUT
	public Response put(@FormParam("permission") String permission,
			@FormParam("rel") String relationship) {
		if (permission != null && !permission.isEmpty()) {
			try {
				Permission perm = Permission.value(permission);
				new Handler<Void>(new ShareUser(getUserId(), userId, perm))
						.execute();
				return Response.ok().build();
			} catch (DataFormatException e) {
				throw new InvalidRequestException("Invalid permission value");
			}
		} else if (relationship != null && !relationship.isEmpty()) {
			new Handler<Void>(new UpdateRelationship(getUserId(), userId,
					relationship)).execute();
			return Response.ok().build();
		}
		throw new InvalidRequestException(
				"No valid permission provided: permission or rel expected.");
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new RemoveUserContact(getUserId(), userId)).execute();
		return Response.ok().build();
	}
}
