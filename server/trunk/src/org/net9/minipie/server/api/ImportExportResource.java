/**
 * ImportExportResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.account.DownloadMyInfo;
import org.net9.minipie.server.logic.operation.account.UploadMyInfo;

/**
 * @author LeeThree
 * 
 */
//TODO @Path("/")
public class ImportExportResource extends BaseResource {

	private static final int SIZE_LIMIT = 524288;

	@GET
	@Path("export")
	public Response export() {
		String filename = new Handler<String>(new DownloadMyInfo(getUserId(),
				getDownloadRealPath())).execute();
		return Response.temporaryRedirect(
				getDownloadPathUrl().resolve(filename)).build();
	}
	
	@GET
	@Path("import")
	public Response get() {
		return Response.ok().type(MediaType.TEXT_HTML_TYPE).entity(UPLOAD_HTML)
				.build();
	}

	@POST
	@Path("import")
	public Response importt(@Context HttpServletRequest request) {
		try {
			String returnedFileName = null;
			if (ServletFileUpload.isMultipartContent(request)) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setFileSizeMax(SIZE_LIMIT); // 512KB
				if (request.getContentLength() > SIZE_LIMIT)
					return Response.status(413).build();
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();

					if (!item.isFormField()) {

						if (!item.getContentType().equalsIgnoreCase(
								"text/plain"))
							return Response.notAcceptable(
									Variant.mediaTypes(
											MediaType.TEXT_PLAIN_TYPE).build())
									.build();
						InputStream istream = item.openStream();

						returnedFileName = new Handler<String>(
								new UploadMyInfo(getUserId(), istream,
										getDownloadRealPath())).execute();

						istream.close();
						return Response.ok(returnedFileName).build();
					}
				}
				return Response.noContent().build();
			}
			return Response.notAcceptable(
					Variant.mediaTypes(MediaType.TEXT_PLAIN_TYPE).build())
					.build();
		} catch (ServerErrorException e) {
			if (e.getCause() instanceof FileUploadIOException)
				return Response.status(413).build();
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private static final String UPLOAD_HTML = "<html><body><form method='post'"
		+ " enctype='multipart/form-data'><input type='file' name='fileupload'/>"
		+ "<input type='submit' value='upload' /></form></body></html>";
}
