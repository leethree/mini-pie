/**
 * ServerErrorMapper.java
 *     in package: * org.net9.minipie.server.api.exception
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author LeeThree
 * 
 */
@Provider
public class ServerErrorMapper implements
		ExceptionMapper<ServerErrorException> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(ServerErrorException ex) {
		return Response.serverError().entity(
				"Service is Temporarily Unavailable").type(
				MediaType.TEXT_PLAIN_TYPE).build();
		//return Response.serverError().build();
	}
}
