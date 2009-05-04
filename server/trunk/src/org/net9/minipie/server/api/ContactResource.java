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
import org.net9.minipie.server.api.xml.AddressXml;
import org.net9.minipie.server.data.Add;
import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.PhoneNoData;
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
	public Collection<AddXml> get() {
		AddressData test = new AddressData(20, "sdaf", "siou", false, "dfg",
				"02145", Permission.TO_EVERYONE);
		Add add = new Add(InfoType.ADDRESS, test);
		PhoneNoData test2 = new PhoneNoData(20,"sad","home",false,Permission.TO_SELF);
		Add add2 = new Add(InfoType.PHONE, test2);
		Collection<AddXml> col = new ArrayList<AddXml>();
		col.add(new AddXml(add));
		col.add(new AddXml(add2));
		return col;
	}

	@POST
	@Consumes( { "application/xml", "application/json" })
	public void post(AddressXml contact) {
		System.out.println(contact);
	}

}