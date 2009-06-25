/**
 * LogicTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import java.util.Collection;

import org.net9.minipie.server.data.api.ContactListEntry;
import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.logic.operation.account.DownloadMyInfo;
import org.net9.minipie.server.logic.operation.account.SearchMyContact;
import org.net9.minipie.server.logic.operation.contact.AddMyContact;
import org.net9.minipie.server.logic.operation.user.SearchAllUser;

/**
 * @author Seastar
 * 
 */
public class SearchTest {
	public static void main(String avg[]) {
		// Collection<UserListEntry> user =new
		// Handler<Collection<UserListEntry>>(new SearchAllUser("Wang
		// Ts'ai,email:my09@sina.com,phone: 11123" +
		// " ,birthday: 1993.4 ,gender:ssfds")).execute();
		// for(UserListEntry u:user)
		// System.out.println(u.getName());
		Collection<ContactListEntry> user = new Handler<Collection<ContactListEntry>>(
				new SearchMyContact(1L,
						"Tom,email:jerry@disney.land,phone: 11123"))
				.execute();
		for (ContactListEntry u : user)
			System.out.println(u.getName());
	}
}
