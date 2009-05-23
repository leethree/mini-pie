/**
 * NotificationResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.data.api.NotificationList;
import org.net9.minipie.server.data.api.NotificationXml;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.notification.ListAllMyNotification;

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
				new ListAllMyNotification(1L)).excute(), uriInfo
				.getAbsolutePath());
	}
}
