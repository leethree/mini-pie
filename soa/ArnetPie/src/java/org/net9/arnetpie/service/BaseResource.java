/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service;

import com.sun.jersey.api.core.ResourceContext;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import org.net9.arnetpie.exception.UnauthorizedException;

/**
 *
 * @author SL
 */
public abstract class BaseResource {

    @Context
    private HttpHeaders header;
    @Context
    private ResourceContext resource;
    @Context
    private UriInfo context;

    protected String getAuthorization() {
        List<String> auths = header.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (auths == null || auths.size() <= 0)
            throw new UnauthorizedException();
        else
            return auths.get(0);
    }

    protected List<String> getHost() {
        return header.getRequestHeader(HttpHeaders.HOST);
    }

    protected <T extends BaseResource> T getSubResource(Class<T> clazz) {
        return resource.getResource(clazz);
    }
}
