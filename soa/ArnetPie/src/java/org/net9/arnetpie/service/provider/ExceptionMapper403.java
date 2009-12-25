/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.net9.arnetpie.service.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.net9.arnetpie.exception.ForbiddenException;

/**
 *
 * @author SL
 */
@Provider
public class ExceptionMapper403 implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException exception) {
        return Response.status(Status.FORBIDDEN).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
