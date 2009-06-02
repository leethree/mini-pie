package org.net9.minipie.server.api;

import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.api.PhonebookCompleteContact;
import org.net9.minipie.server.data.api.StatusReportList;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.api.UpdateList;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.MacroCommand;
import org.net9.minipie.server.logic.operation.contact.DeleteMyContact;
import org.net9.minipie.server.logic.operation.contact.UpdateMyContact;
import org.net9.minipie.server.logic.operation.contact.UploadContactImage;
import org.net9.minipie.server.logic.operation.contact.ViewMyContact;

public class PhonebookContactResource extends BaseResource {

	private long contactId;

	/**
	 * Constructor
	 */
	public PhonebookContactResource() {
	}

	/**
	 * @param contactId
	 *            the contactId to set
	 */
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public PhonebookCompleteContact get() {
		return new Handler<PhonebookCompleteContact>(new ViewMyContact(
				getUserId(), contactId)).execute();
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
				macro.addCommand(new UpdateMyContact(getUserId(), contactId,
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
		new Handler<Void>(new UpdateMyContact(getUserId(), contactId, update
				.checkThis())).execute();
		return Response.ok().build();
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new DeleteMyContact(getUserId(), contactId))
				.execute();
		return Response.ok().build();
	}

	@Path("image")
	public ContactImageResource getImage() {
		ContactImageResource resource = getSubResource(ContactImageResource.class);
		resource.setContactId(contactId);
		return resource;
		// return new ProfileImageResource();
	}

	public class ContactImageResource extends ImageResource {
		private long contactId;

		/**
		 * @param contactId
		 *            the contactId to set
		 */
		public void setContactId(long contactId) {
			this.contactId = contactId;
		}

		@Override
		public String upload(InputStream istream, String filePath, URI urlPath,
				String contentType) {
			return new Handler<String>(new UploadContactImage(getUserId(),
					contactId, istream, filePath, urlPath, contentType)).execute();
		}

	}
}