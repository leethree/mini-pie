package org.net9.minipie.server.api;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.sun.jersey.api.core.ResourceContext;

@Path("/phonebook")
public class PhonebookResources {
	@Context
    protected ResourceContext resourceContext;
	
	@Path("contact")
	public PhonebookContactList getContacts() {
		return resourceContext.getResource(PhonebookContactList.class);
	}
}