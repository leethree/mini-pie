/**
 * BrowseContactResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.CompleteContact;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.contact.ViewSharedContact;

/**
 * @author LeeThree
 *
 */
public class BrowseContactResource extends BaseResource{
	
	private long contactId;

	/**
	 * Constructor
	 */
	public BrowseContactResource() {
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
	public CompleteContact get() {
		return new Handler<CompleteContact>(new ViewSharedContact(
				getUserId(), contactId)).execute();
	}
}
