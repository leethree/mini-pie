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
import org.net9.minipie.server.data.storage.User_User;

/**
 * @author Seastar
 *
 */
public interface User_UserStorage {
	public void add(Long userId1,Long userId2);
	public void del(Long userId1,Long userId2);
	public void edit(Long userId1,Long userId2,InfoField attribute,Object value);
	public String selectRelationship(Long userId1,Long userId2);
	//��l��:user
	public Collection<CommonListEntry> selectSharedUser(Long userId,Permission permission); 
	public Collection<User_User> selectMyUserContact(Long id);
	
}
