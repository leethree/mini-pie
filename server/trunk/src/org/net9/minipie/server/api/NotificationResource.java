/**
 * NotificationResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.data.api.NotificationList;
import org.net9.minipie.server.data.api.NotificationXml;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.notification.ListAllMyNotification;
import org.net9.minipie.server.logic.operation.user.ConfirmContactApply;

import com.sun.jersey.api.core.ResourceContext;

/**
 * @author LeeThree
 * 
 */
@Path("/notification")
public class NotificationResource {
	@Context
	private UriInfo uriInfo;
	@Context
	protected ResourceContext resourceContext;

	@GET
	@Produces( { "application/xml", "application/json" })
	public NotificationList get() {
		return new NotificationList(new Handler<Collection<NotificationXml>>(
				new ListAllMyNotification(1L)).execute(), uriInfo
				.getAbsolutePath());
	}

	@PUT
	@Path("{id}")
	public Response put(@FormParam("confirmation") boolean confirm,
			@PathParam("id") long id) {
		new Handler<Void>(new ConfirmContactApply(1L, id, confirm)).execute();
		return Response.ok().build();
	}
}
