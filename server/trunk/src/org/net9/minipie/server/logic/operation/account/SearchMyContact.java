/**
 * Search.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.QueryAnalyze;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class SearchMyContact extends Command<Collection<ContactListEntry>>{
	
	private long userId;
	private String querys;
	
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<ContactListEntry> execute() {
		Collection<ContactListEntry> result = new Vector<ContactListEntry>();
		ContactStorage executor = getStorageFactory().getContactStorage();
		Collection<Query> qu = QueryAnalyze.analyzeQuary(querys);
		qu.add(new Query(InfoField,));
		Collection<BasicContact> contacts;
		try {
			contacts=executor.searchAllContact(qu, null);
			for(BasicContact contact:contacts){
				
			}
		} catch (NotFoundException e) {
			
		}
		return result;
	}
	// TODO
}
