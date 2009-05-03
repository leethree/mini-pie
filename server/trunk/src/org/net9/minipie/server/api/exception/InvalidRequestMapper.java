/**
 * InvalidRequestMapper.java
 *     in package: * org.net9.minipie.server.api.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.net9.minipie.server.exception.InvalidRequestException;

/**
 * @author LeeThree
 * 
 */
@Provider
public class InvalidRequestMapper implements
		ExceptionMapper<InvalidRequestException> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(InvalidRequestException ex) {
		return Response.status(Status.BAD_REQUEST).entity(ex.getMessage())
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}
}
