package org.net9.minipie.server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.Edit;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.api.UpdateList;

@SuppressWarnings("unused")
@Path("/test")
public class ApiTest {

	@GET
	@Produces( { "application/xml", "application/json" })
	public void getTest() {
//		return new MyContactXml(new Handler<PersonalCompleteContact>(
//				new ViewMyContact(1L, 1L)).excute());
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public void postTest(UpdateList list) {
		for (Update update : list.getUpdates()) {
			System.out.println(update);
			System.out.println(update.getType());
			//System.out.println(((Edit)update).getInfoField());
		}
	}
}