/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author SL
 */
public class NotificationResource extends BaseResource {

    private String id;

    /**
     * Creates a new instance of GroupResource
     */
    private NotificationResource(String id) {
        this.id = id;
    }

    /**
     * Creates a new instance of GroupResource
     */
    public static NotificationResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of GroupResource class.
        return new NotificationResource(id);
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void confirmNotification(@FormParam("confirm") String confirm) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
