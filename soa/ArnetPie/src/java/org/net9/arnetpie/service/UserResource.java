/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author SL
 */
public class UserResource extends BaseResource {

    private String id;

    private UserResource(String id) {
        this.id = id;
    }

    public static UserResource getInstance(String id) {
        return new UserResource(id);
    }

    @GET
    @Produces("application/xml")
    @Path("groups")
    public String getUserGroups() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces("application/xml")
    public String getUserProfile() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    @Path("email")
    public String setEmail(@FormParam("email") String email) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces("application/xml")
    @Path("favorite")
    public String getFavorites() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Produces("text/plain")
    @Path("favorite/pdf/{id}")
    public String removePDFFavorite(@PathParam("id") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Produces("text/plain")
    @Path("favorite/wikipage/{id}")
    public String removeWikiPageFavorite(@PathParam("id") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    @Path("favorite/pdf")
    public String collectPDF(@FormParam("pdf") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    @Path("favorite/wikipage")
    public String collectWikiPage(@FormParam("wikipage") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    @Path("subscription/pdf")
    public String subscribePDF(@FormParam("pdf") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    @Path("subscription/wikipage")
    public String subscribeWikiPage(@FormParam("wikipage") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Produces("text/plain")
    @Path("subscription/pdf/{id}")
    public String removePDFSubscription(@PathParam("id") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Produces("text/plain")
    @Path("subscription/wikipage/{id}")
    public String removeWikiPageSubscription(@PathParam("id") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
