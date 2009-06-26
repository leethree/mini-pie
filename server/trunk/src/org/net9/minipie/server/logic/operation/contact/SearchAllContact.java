/**
 * Search.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.QueryAnalyze;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 *
 */
public class SearchAllContact extends Command<Collection<ContactListEntry>>{
	
	private long userId;
	private String querys;
	
	public SearchAllContact(long userId,String querys){
		this.userId=userId;
		this.querys=querys;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<ContactListEntry> execute() {
		Collection<ContactListEntry> result = new Vector<ContactListEntry>();
		ContactStorage executor = getStorageFactory().getContactStorage();
		Collection<Query> qu = QueryAnalyze.analyzeQuary(querys);
		//qu.add(new Query(InfoType.BASIC,InfoField.OWNER_ID,String.valueOf(userId)));
		//qu.add(e);
		Collection<BasicContact> contacts;
		try {
			contacts=executor.searchAllContact(qu);
			for(BasicContact contact:contacts){
				if(contact.getEntity().getPermission()==Permission.TO_EVERYONE)
					result.add(new ContactListEntry(contact.getEntity()));				
			}
		} catch (NotFoundException e) {
			
		}
		return result;
	}
	
}
