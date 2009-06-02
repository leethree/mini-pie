package org.net9.minipie.server.api;

import javax.ws.rs.Path;

@Path("/phonebook")
public class PhonebookResources extends BaseResource{
	
	@Path("contact")
	public PhonebookContactListResource getContacts() {
		return getSubResource(PhonebookContactListResource.class);
	}
	
	@Path("user")
	public PhonebookUserListResource getUsers() {
		return getSubResource(PhonebookUserListResource.class);
	}
	
	@Path("tag")
	public PhonebookTagListResource getTags() {
		return getSubResource(PhonebookTagListResource.class);
	}
}