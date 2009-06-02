/**
 * ProfileResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.PersonalProfile;
import org.net9.minipie.server.data.api.StatusReportList;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.api.UpdateList;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.MacroCommand;
import org.net9.minipie.server.logic.operation.account.UpdateMyInfo;
import org.net9.minipie.server.logic.operation.account.UploadMyImage;
import org.net9.minipie.server.logic.operation.account.ViewMyInfo;

/**
 * @author LeeThree
 * 
 */
@Path("/profile")
public class ProfileResource extends BaseResource {

	@GET
	@Produces( { "application/xml", "application/json" })
	public PersonalProfile get() {
		return new Handler<PersonalProfile>(new ViewMyInfo(getUserId()))
				.execute();
	}

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
				macro.addCommand(new UpdateMyInfo(getUserId(), update
						.checkThis()));
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
		new Handler<Void>(new UpdateMyInfo(getUserId(), update.checkThis()))
				.execute();
		return Response.ok().build();
	}
	
	@Path("image")
	public ProfileImageResource getImage() {
		ProfileImageResource resource = getSubResource(ProfileImageResource.class);
		return resource;
		// return new ProfileImageResource();
	}

	public class ProfileImageResource extends ImageResource {

		@Override
		public String upload(InputStream istream, String filePath, URI urlPath,
				String contentType) {
			return new Handler<String>(new UploadMyImage(getUserId(),
					 istream, filePath, urlPath, contentType)).execute();
		}

	}
}
