package org.net9.minipie.server.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.net9.minipie.server.auth.MiniPieUser;
import org.net9.minipie.server.exception.ServerErrorException;

import com.sun.jersey.api.core.ResourceContext;

@SuppressWarnings("unused")
@Path("/test")
public class ApiTest {

	@Context
	ResourceContext context;

	@Context
	SecurityContext security;

	@Context
	ServletContext servlet;

	@GET
	public Response getTest() {
		try {
			MiniPieUser user = (MiniPieUser) security.getUserPrincipal();
			String welcomeMsg = "Congratulations! You've been successfully authenticated as "
					+ user.getName() + " !\n";
			welcomeMsg += "Your ID is: " + user.getId();
			return Response.ok().type(MediaType.TEXT_PLAIN_TYPE).entity(
					welcomeMsg).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		}
	}

	@POST
	public Response postTest(@Context HttpServletRequest request) {
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setFileSizeMax(10240);
				FileItemIterator iter = upload.getItemIterator(request);
				// System.out.println(request.getContentLength());
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					if (!item.isFormField()) {
						String name = item.getFieldName();
						String filePath = servlet.getRealPath("/images");
						String fileName = item.getName();
						// System.out.println(filePath + ":" + fileName);
						File file = new File(filePath, fileName);
						InputStream istream = item.openStream();
						FileOutputStream ostream = new FileOutputStream(file);

						byte[] buffer = new byte[2048];
						int byteread;
						while ((byteread = istream.read(buffer)) != -1) {
							// bytesum += byteread; // 字节数 文件大小
							// System.out.println(bytesum);
							ostream.write(buffer, 0, byteread);
						}

						// int size = istream.available();
						// System.out.println(size);
						// while (size > 0) {
						// byte[] buf = new byte[size];
						// istream.read(buf);
						// ostream.write(buf);
						// size = istream.available();
						// System.out.println(size);
						// }
						// int bytesRead;
						// while ((bytesRead = istream.read(buf)) != -1) {
						// ostream.write(buf, 0, bytesRead);
						// }
						istream.close();
						// ostream.flush();
						ostream.close();
						return Response.created(
								new URI(
										"http://localhost:8088/Mini-Pie/images/"
												+ fileName)).build();
					}
				}
				return Response.noContent().build();
			}
		} catch (FileUploadIOException e) {
			e.printStackTrace();
			return Response.status(413).build();

		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}