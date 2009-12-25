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
import org.net9.arnetpie.exception.ServerErrorException;

/**
 *
 * @author SL
 */
@Provider
public class ExceptionMapper500 implements ExceptionMapper<ServerErrorException> {

    @Override
    public Response toResponse(ServerErrorException exception) {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
