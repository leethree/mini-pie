/**
 * PhonebookGroupListResource.java
 *     in package: * org.net9.minipie.server.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.net9.minipie.server.data.api.GroupList;
import org.net9.minipie.server.data.api.GroupListEntry;
import org.net9.minipie.server.logic.Handler;
import org.net9.minipie.server.logic.operation.group.ListMyGroups;

/**
 * @author LeeThree
 *
 */
public class PhonebookGroupListResource extends BaseResource {
	@GET
	@Produces( { "application/xml", "application/json" })
	public GroupList get() {
		return new GroupList(
				new Handler<Collection<GroupListEntry>>(
						new ListMyGroups(getUserId())).execute(), getResourceUrl());
	}
}
