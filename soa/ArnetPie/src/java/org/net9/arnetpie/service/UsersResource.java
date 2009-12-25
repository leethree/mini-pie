/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author SL
 */
@Path("users")
public class UsersResource extends BaseResource {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String registerUser(@FormParam("username") String name, @FormParam("email") String email, @FormParam("password") String password) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces("text/plain")
    public String getUserId(@QueryParam("username") String name) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Path("{id}")
    public UserResource getUserResource(@PathParam("id") String id) {
        return UserResource.getInstance(id);
    }

    @Path("myself")
    public MyResource getMyProfile() {
        return getSubResource(MyResource.class);
    }
}
