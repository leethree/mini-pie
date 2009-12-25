/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author SL
 */
@Path("groups")
public class GroupsResource extends BaseResource {

    /**
     * Creates a new instance of GroupsResource
     */
    public GroupsResource() {
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String createGroup(@FormParam("name") String name) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces("application/xml")
    public String findGroupByName(@QueryParam("groupname") String group) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Produces("text/plain")
    @Path("{id}")
    public String disbandGroup(@PathParam("id") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Path("{id}")
    public GroupResource getGroupResource(@PathParam("id") String id) {
        return GroupResource.getInstance(id);
    }
}
