/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author SL
 */
@Path("password")
public class PasswordResource extends BaseResource {

    @GET
    @Path("verify")
    public void verifyPassword() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public void setPassword(@FormParam("password") String password) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
