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
import org.net9.arnetpie.exception.BadRequestException;

/**
 *
 * @author SL
 */
@Provider
public class ExceptionMapper400 implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException exception) {
        return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
