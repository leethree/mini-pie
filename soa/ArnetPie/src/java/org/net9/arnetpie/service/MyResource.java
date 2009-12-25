/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import org.net9.arnetpie.data.Userprofile;
import org.net9.arnetpie.process.UserprofileController;

/**
 *
 * @author SL
 */
@Stateless
public class MyResource extends BaseResource {

    @EJB UserprofileController ctrl;

    @GET
    @Produces("application/xml")
    public Userprofile getMyProfile() {
        // TODO
        //throw new UnsupportedOperationException();
        return ctrl.getMyProfile(getAuthorization());
    }

    @PUT
    @Consumes("application/xml")
    public void updateMyProfile() {
        // TODO
        throw new UnsupportedOperationException();
    }
}
