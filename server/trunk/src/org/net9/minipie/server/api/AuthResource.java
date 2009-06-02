/**
 * AuthResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author LeeThree
 * 
 */
@Path("/auth")
public class AuthResource extends BaseResource {

	@GET
	public Response getTest() {
		try {
			String welcomeMsg = "Congratulations! You've been successfully authenticated !\n";
			welcomeMsg += "Your ID is: " + getUserId() + "\n";
			return Response.ok().type(MediaType.TEXT_PLAIN_TYPE).entity(
					welcomeMsg).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
	}
}
