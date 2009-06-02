/**
 * ImageResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

/**
 * @author LeeThree
 * 
 */
public abstract class ImageResource extends BaseResource {

	private static final int SIZE_LIMIT = 524288;

	public abstract String upload(InputStream istream, String filePath,
			URI urlPath, String contentType);

	@GET
	public Response get() {
		return Response.ok().type(MediaType.TEXT_HTML_TYPE).entity(UPLOAD_HTML)
				.build();
	}

	@POST
	public Response post(@Context HttpServletRequest request) {
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

						String contentType = checkContentType(item
								.getContentType());
						if (contentType == null)
							return Response.notAcceptable(VARIANTS).build();
						String filePath = getImageRealPath();
						// String fileName = item.getName();
						// File file = new File(filePath, fileName);
						InputStream istream = item.openStream();

						returnedFileName = upload(istream, filePath,
								getImagePathUrl(), contentType);

						istream.close();
						return Response.created(
								getImagePathUrl().resolve(returnedFileName))
								.build();
					}
				}
				return Response.noContent().build();
			}
			return Response.notAcceptable(VARIANTS).build();
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

	private String checkContentType(String contentType) {
		if (contentType.equals("image/png"))
			return "png";
		else if (contentType.equals("image/jpg")
				|| contentType.equals("image/jpeg"))
			return "jpg";
		else if (contentType.equals("image/gif"))
			return "gif";
		else
			return null;
	}

	private static final String UPLOAD_HTML = "<html><body><form method='post'"
			+ " enctype='multipart/form-data'><input type='file' name='fileupload'/>"
			+ "<input type='submit' value='upload' /></form></body></html>";

	private static final List<Variant> VARIANTS = Variant.mediaTypes(
			MediaType.valueOf("image/png")).mediaTypes(
			MediaType.valueOf("image/jpg")).mediaTypes(
			MediaType.valueOf("image/gif")).build();
}
