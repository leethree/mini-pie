package org.net9.minipie.server.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.net9.minipie.server.api.xml.MyContactXml;
import org.net9.minipie.server.data.PersonalCompleteContact;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.ViewMyContact;

@Path("/test")
public class ApiTest {

	@GET
	@Produces( { "application/xml", "application/json" })
	public MyContactXml getTest() {
		return new MyContactXml(new Handler<PersonalCompleteContact>(
				new ViewMyContact(1L, 1L)).excute());
	}

	@POST
	public void postTest(@FormParam("test") String para) {
		System.out.println(para);
	}
}