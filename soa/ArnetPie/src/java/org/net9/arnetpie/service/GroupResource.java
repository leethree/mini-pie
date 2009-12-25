/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author SL
 */
public class GroupResource {

    private String id;

    /**
     * Creates a new instance of GroupResource
     */
    private GroupResource(String id) {
        this.id = id;
    }

    /**
     * Creates a new instance of GroupResource
     */
    public static GroupResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of GroupResource class.
        return new GroupResource(id);
    }

    @GET
    @Produces("text/plain")
    @Path("admin")
    public String getGroupAdministrator() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    @Path("admin")
    public String setGroupAdministrator(@FormParam("adminid") String id) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces("application/xml")
    @Path("profile")
    public String getGroupInfo() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("profile")
    public String setGroupInfo(String profile) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces("application/xml")
    @Path("members")
    public String getGroupMembers() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Produces("text/plain")
    @Path("members/{userid}")
    public String dismissMember(@PathParam("userid") String user) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
