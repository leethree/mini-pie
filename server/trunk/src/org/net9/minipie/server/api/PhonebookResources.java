package org.net9.minipie.server.api;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.sun.jersey.api.core.ResourceContext;

@Path("/phonebook")
public class PhonebookResources {
	@Context
    protected ResourceContext resourceContext;
	
	@Path("contact")
	public PhonebookContactListResource getContacts() {
		return resourceContext.getResource(PhonebookContactListResource.class);
	}
	
	@Path("user")
	public PhonebookUserListResource getUsers() {
		return resourceContext.getResource(PhonebookUserListResource.class);
	}
	
	@Path("tag")
	public PhonebookTagListResource getTags() {
		return resourceContext.getResource(PhonebookTagListResource.class);
	}
}