/**
 * ContactResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.net9.minipie.server.api.xml.AddXml;
import org.net9.minipie.server.api.xml.DeleteXml;
import org.net9.minipie.server.api.xml.EditXml;
import org.net9.minipie.server.api.xml.UpdateListXml;
import org.net9.minipie.server.api.xml.UpdateXml;
import org.net9.minipie.server.data.Add;
import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.Delete;
import org.net9.minipie.server.data.Edit;
import org.net9.minipie.server.data.PhoneNoData;
import org.net9.minipie.server.data.constant.InfoField;
import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author LeeThree
 * 
 */
@Path("/contact")
public class ContactResource {
	@Context
	private UriInfo uriInfo;

	/**
	 * Constructor
	 */
	public ContactResource() {
	}

	@GET
	@Produces( { "application/xml", "application/json" })
	public UpdateListXml get() {
		uriInfo.getAbsolutePath();
		AddressData test = new AddressData(20, "sdaf", "siou", false, "dfg",
				"02145", Permission.TO_EVERYONE);
		Add add = new Add(InfoType.ADDRESS, test);
		PhoneNoData test2 = new PhoneNoData(23, "+8615901029944", "home", true,
				Permission.TO_SELF);
		Add add2 = new Add(InfoType.PHONE, test2);
		Delete delete = new Delete(InfoType.PHONE, 87);
		Edit edit = new Edit(InfoType.URL, InfoField.NOTE, 87, "sdfsdf");
		Collection<UpdateXml> col = new ArrayList<UpdateXml>();
		col.add(new AddXml(add));
		col.add(new DeleteXml(delete));
		col.add(new AddXml(add2));
		col.add(new EditXml(edit));
		UpdateListXml list = new UpdateListXml();
		list.setUpdates(col);
		return list;
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public void post(UpdateListXml list) {
		for (UpdateXml update : list.getUpdates()) {
			System.out.println(update);
		}
		// System.out.println(add.getType());
		// System.out.println(add.getEntity().getInfo());
		// System.out.println(edit.getValue());
	}

}