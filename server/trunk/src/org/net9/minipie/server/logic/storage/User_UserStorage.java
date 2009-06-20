/**
 * User_UserStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;
import java.util.Map;

import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.UserRelation;

/**
 * @author Seastar
 *
 */
public interface User_UserStorage {
	public void add(Long userId1,Long userId2);
	public void del(Long userId1,Long userId2);
	public void edit(Long userId1,Long userId2,InfoField attribute,Object value);
	public String selectRelationship(Long userId1,Long userId2);
	public Permission selectPermission(Long userId1,Long userId2);
	//��l��:user
	public Collection<CommonListEntry> selectSharedUser(Long userId,Permission permission); 
	public Collection<UserRelation> selectMyUserContact(Long id);
	//public Collection<Long> selectSameContact(Long userId1,Long userId2);
	public Map<Long, Long> getRelations();
	
}
