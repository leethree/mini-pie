/**
 * PhonebookGroupResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.QuitGroup;
import org.net9.minipie.server.logic.operation.group.admin.EditGroupInfo;

/**
 * @author LeeThree
 * 
 */
public class PhonebookGroupResource extends BaseResource {
	private long groupId;

	/**
	 * Constructor
	 */
	public PhonebookGroupResource() {
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@PUT
	public Response put(@FormParam("field") String field,
			@FormParam("value") String value) {
		if (field != null && !field.isEmpty() && value != null
				&& !value.isEmpty()) {
			try {
				new Handler<Void>(new EditGroupInfo(getUserId(), groupId,
						value, InfoField.value(field))).execute();
			} catch (DataFormatException e) {
				throw new InvalidRequestException(e);
			}
			return Response.ok().build();
		}
		throw new InvalidRequestException(
				"No valid parameter provided: field and value expected.");
	}

	@DELETE
	public Response delete() {
		new Handler<Void>(new QuitGroup(getUserId(), groupId)).execute();
		return Response.ok().build();
	}

	@Path("user")
	public PhonebookGroupUserResource getGroupUsers() {
		PhonebookGroupUserResource user = getSubResource(PhonebookGroupUserResource.class);
		user.setGroupId(groupId);
		return user;
	}

	@Path("contact")
	public PhonebookGroupContactResource getGroupContacts() {
		PhonebookGroupContactResource contact = getSubResource(PhonebookGroupContactResource.class);
		contact.setGroupId(groupId);
		return contact;
	}
}
