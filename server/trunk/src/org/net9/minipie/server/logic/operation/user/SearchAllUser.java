/**
 * ListAllUser.java
 *     in package: * org.net9.minipie.server.logic.operation.user
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.user;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.Query;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.data.field.AddAsContactPermission;

/**
 * @author Seastar
 * 
 */
public class SearchAllUser extends Command<Collection<UserListEntry>> {
	private long userId;
	private String querys;

	/**
	 * Constructor
	 * 
	 * @param userId
	 * @param querys
	 */
	public SearchAllUser(long userId, String querys) {
		super();
		this.userId = userId;
		this.querys = querys;
	}

	@Override
	public Collection<UserListEntry> execute() {
		Collection<UserListEntry> result = new Vector<UserListEntry>();
		UserStorage executor = getStorageFactory().getUserStorage();
		Collection<Query> qu = analyzeQuary();
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

	/**
	 * @return
	 */
	private Collection<Query> analyzeQuary() {
		Vector<Query> result = new Vector<Query>();
		try {
			querys.replaceAll("\\s+", " ");
			String[] t = querys.split(",");
			for (int i = 0; i < t.length; ++i) {
				t[i]=t[i].trim();
				int j = t[i].indexOf(":");
				//System.out.println(j + "  " + t[i]);
				if (j == -1)
					result.add(new Query(InfoType.BASIC, InfoField.NAME, t[i]));
				else {
					if (j == t[i].length() - 1) {
						try {
							t[i] = t[i].replace(":", "").toLowerCase();
							InfoType type = InfoType.value(t[i]);
							if (type != InfoType.BASIC) {
								result.add(new Query(type, InfoField.VALUE,
										t[i + 1]));
							}
							++i;
						} catch (DataFormatException e) {

						}
						try {
							InfoField field = InfoField.value(t[i]);
							if (field == InfoField.BIRTHDAY
									|| field == InfoField.DISPLAYNAME
									|| field == InfoField.NICKNAME
									|| field == InfoField.NOTE) {
								result.add(new Query(InfoType.BASIC, field,
										t[i + 1]));
							}
							++i;
						} catch (DataFormatException e) {

						}
					} else {
						String tt[] = t[i].split(":");
						try {
							tt[0] = tt[0].replace(":", "").toLowerCase();
							InfoType type = InfoType.value(tt[0]);
							if (type != InfoType.BASIC) {
								result.add(new Query(type, InfoField.VALUE,
										tt[1]));
							}
						} catch (DataFormatException e) {

						}
						try {
							InfoField field = InfoField.value(tt[0]);
							if (field == InfoField.BIRTHDAY
									|| field == InfoField.DISPLAYNAME
									|| field == InfoField.NICKNAME
									|| field == InfoField.NOTE) {
								result.add(new Query(InfoType.BASIC, field,
										tt[1]));
							}

						} catch (DataFormatException e) {

						}
					}

				}
			}
		} catch (Exception e) {

		}
		return result;
	}

}
