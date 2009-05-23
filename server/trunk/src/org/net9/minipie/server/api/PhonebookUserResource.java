/**
 * PhonebookUserResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author LeeThree
 *
 */
public class PhonebookUserResource {
	@SuppressWarnings("unused")
	@Context
	private UriInfo uriInfo;
	
	private long userId;
	
	/**
	 * Constructor
	 */
	public PhonebookUserResource() {
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
