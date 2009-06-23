/**
 * BrowseUserResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.CompleteUser;
import org.net9.minipie.server.data.api.ContactList;
import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.api.UserList;
import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.contact.ListSharedContact;
import org.net9.minipie.server.logic.operation.user.ListSharedUserContact;
import org.net9.minipie.server.logic.operation.user.ViewSharedUserContact;

/**
 * @author LeeThree
 * 
 */
public class BrowseUserResource extends BaseResource {

	private long userId;

	/**
	 * Constructor
	 */
	public BrowseUserResource() {
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
	public CompleteUser get() {
		return new Handler<CompleteUser>(new ViewSharedUserContact(getUserId(),
				userId)).execute();
	}

	@GET
	@Path("user")
	@Produces( { "application/xml", "application/json" })
	public UserList getSharedUsers() {
		return new UserList(new Handler<Collection<UserListEntry>>(
				new ListSharedUserContact(getUserId(), userId)).execute(),
				getResourceUrl());
		// TODO uri
	}

	@GET
	@Path("contact")
	@Produces( { "application/xml", "application/json" })
	public ContactList getSharedContacts() {
		return new ContactList(new Handler<Collection<ContactListEntry>>(
				new ListSharedContact(getUserId(), userId)).execute(),
				getResourceUrl());
		// TODO uri
	}
}
