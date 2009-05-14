/**
 * User_UserStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;

/**
 * @author Seastar
 *
 */
public interface User_UserStorage {
	public void add(long userId1,long userId2);
	public void del(long userId1,long userId2);
	public void edit(long userId1,long userId2,InfoField attribute,Object value);
	public String selectRelationship(long userId1,long userId2);
	//��l��:user
	public Collection<CommonListEntry> selectSharedUser(long userId,Permission permission); 
	
}
