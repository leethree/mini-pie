/**
 * PhonebookTagListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.data.api.TagList;
import org.net9.minipie.server.data.api.TagXml;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.tag.CreateTag;
import org.net9.minipie.server.logic.operation.tag.ListAllTags;

import com.sun.jersey.api.core.ResourceContext;

/**
 * @author LeeThree
 *
 */
public class PhonebookTagListResource {
	@SuppressWarnings("unused")
	@Context
	private UriInfo uriInfo;
	@Context
	protected ResourceContext resourceContext;
	
	@GET
	@Produces( { "application/xml", "application/json" })
	public TagList get() {
		return new TagList(new Handler<Collection<TagXml>>(
				new ListAllTags(1L)).execute());
	}

	@POST
	public Response post(@FormParam("tagname") String tagName) {
		new Handler<Long>(new CreateTag(1L, tagName)).execute();
		return Response.created(null).build();
		// TODO uri
	}

	@Path("{id}")
	public PhonebookTagResource getTag(@PathParam("id") long id) {
		PhonebookTagResource tag = resourceContext
				.getResource(PhonebookTagResource.class);
		tag.setTagId(id);
		return tag;
	}
}
