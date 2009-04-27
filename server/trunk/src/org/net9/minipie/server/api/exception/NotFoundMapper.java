/**
 * NotFoundMapper.java
 *     in package: * org.net9.minipie.server.api.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.net9.minipie.server.exception.NotFoundException;

/**
 * @author LeeThree
 * 
 */
@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(NotFoundException ex) {
		return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).type(
				MediaType.TEXT_PLAIN_TYPE).build();
	}
}
