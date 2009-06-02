/**
 * PhonebookUserResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.PhonebookCompleteUser;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.user.RemoveUserContact;
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
		return new Handler<PhonebookCompleteUser>(new ViewMyUserContact(getUserId(),
				userId)).execute();
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new RemoveUserContact(getUserId(), userId)).execute();
		return Response.ok().build();
	}
}
