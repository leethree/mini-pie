/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author SL
 */
@Path("notifications")
public class NotificationsResource extends BaseResource {

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public NotificationResource getNotificationResource(@PathParam("id") String id) {
        return NotificationResource.getInstance(id);
    }

    /**
     *
     * @return
     */
    @Path("pending")
    public PendingNotificationsResource getPendingNotificationsResource() {
        return getSubResource(PendingNotificationsResource.class);
    }

    /**
     *
     * @return
     */
    @Path("invited")
    public InvitedNotificationsResource getInvitedNotificationsResource() {
        return getSubResource(InvitedNotificationsResource.class);
    }
}
