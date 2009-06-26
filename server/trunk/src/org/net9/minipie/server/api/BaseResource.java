/**
 * BaseResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.net.URI;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.auth.MiniPieUser;

import com.sun.jersey.api.core.ResourceContext;

/**
 * @author LeeThree
 * 
 */
public abstract class BaseResource {

	private static final String IMAGE_FOLDER = "images/";
	private static final String DOWNLOAD_FOLDER = "download/";

	@Context
	private UriInfo uriInfo;

	@Context
	private SecurityContext security;

	@Context
	private ServletContext servlet;

	@Context
	private ResourceContext resource;

	/**
	 * returns the id of current authenticated user
	 * 
	 * @return user id
	 */
	protected long getUserId() {
		MiniPieUser user = (MiniPieUser) security.getUserPrincipal();
		return user.getId();
	}

	protected URI getServletBaseUrl() {
		return uriInfo.getBaseUri();
	}

	protected URI getResourceUrl() {
		return uriInfo.getAbsolutePath();
	}

	protected String getImageRealPath() {
		return servlet.getRealPath(IMAGE_FOLDER);
	}

	protected URI getImagePathUrl() {
		return getServerBaseUrl().resolve(IMAGE_FOLDER);
	}
	
	protected String getDownloadRealPath() {
		return servlet.getRealPath(DOWNLOAD_FOLDER);
	}

	protected URI getDownloadPathUrl() {
		return getServerBaseUrl().resolve(DOWNLOAD_FOLDER);
	}

	protected URI getServerBaseUrl() {
		return getServletBaseUrl().resolve(servlet.getContextPath() + "/");
	}

	public <T extends BaseResource> T getSubResource(Class<T> clazz) {
		return resource.getResource(clazz);
	}
}
