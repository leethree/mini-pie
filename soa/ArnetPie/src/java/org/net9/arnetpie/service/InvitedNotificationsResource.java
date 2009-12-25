/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author SL
 */
public class InvitedNotificationsResource extends BaseResource {

    @GET
    @Produces("application/xml")
    public String getNotifications(@QueryParam("groupid") String group) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String addNotification(@FormParam("groupid") String group, @FormParam("userid") String user) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
