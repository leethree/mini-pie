/**
 * BrowseResources.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.Path;

/**
 * @author LeeThree
 *
 */
@Path("/")
public class BrowseResources extends BaseResource {
	
	@Path("contact")
	public BrowseContactListResource getContacts() {
		return getSubResource(BrowseContactListResource.class);
	}
	
	@Path("user")
	public BrowseUserListResource getUsers() {
		return getSubResource(BrowseUserListResource.class);
	}
}
