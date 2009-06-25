/**
 * ListAllUser.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.QueryAnalyze;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class SearchAllUser extends Command<Collection<UserListEntry>> {
	//private long userId;
	private String querys;

	/**
	 * Constructor
	 * 
	 * @param userId
	 * @param querys
	 */
	public SearchAllUser(String querys) {
		super();
		//this.userId = userId;
		this.querys = querys;
	}

	@Override
	public Collection<UserListEntry> execute() {
		Collection<UserListEntry> result = new Vector<UserListEntry>();
		UserStorage executor = getStorageFactory().getUserStorage();
		Collection<Query> qu = QueryAnalyze.analyzeQuary(querys);
		Collection<BasicUser> users;
		try {
			users = executor.searchAllUser(qu);
			System.out.println(qu);
			for (BasicUser u : users) {
				if (u.getEntity().getAddAsContactPermission() != AddAsContactPermission.NO_ONE) {
					result.add(new UserListEntry(u.getEntity()));
				}
			}
		} catch (NotFoundException e) {

		}
		return result;
	}

	
}
