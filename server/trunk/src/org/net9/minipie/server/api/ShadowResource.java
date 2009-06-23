/**
 * ShadowResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.CompleteContact;
import org.net9.minipie.server.data.api.StatusReportList;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.api.UpdateList;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.MacroCommand;
import org.net9.minipie.server.logic.operation.user.UpdateUserShadow;
import org.net9.minipie.server.logic.operation.user.ViewMyUserShadow;

/**
 * @author LeeThree
 * 
 */
public class ShadowResource extends BaseResource {
	private long userId;

	/**
	 * Constructor
	 */
	public ShadowResource() {
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
	public CompleteContact get() {
		return new Handler<CompleteContact>(new ViewMyUserShadow(
				getUserId(), userId)).execute();
	}
	
	/**
	 * 
	 * @param updates
	 * @return
	 */
	@POST
	@Consumes( { "application/xml", "application/json" })
	@Produces( { "application/xml", "application/json" })
	public Response post(UpdateList updates) {
		if (updates.getUpdates().size() == 0) {
			throw new InvalidRequestException("No update information provided.");
		} else if (updates.getUpdates().size() == 1) {
			for (Update update : updates.getUpdates()) {
				return update(update);
			}
			throw new ServerErrorException("Unexpected size of updates.");
		} else {
			MacroCommand macro = new MacroCommand();
			for (Update update : updates.getUpdates()) {
				macro.addCommand(new UpdateUserShadow(getUserId(), userId,
						update.checkThis()));
			}
			StatusReportList statuses = new Handler<StatusReportList>(macro)
					.execute();
			return Response.status(StatusReportList.MULTI_STATUS).entity(
					statuses).build();
		}
	}

	/**
	 * 
	 * @param update
	 * @return
	 */
	public Response update(Update update) {
		new Handler<Void>(new UpdateUserShadow(getUserId(), userId, update
				.checkThis())).execute();
		return Response.ok().build();
	}
}
