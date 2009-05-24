/**
 * Group_UserStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.api.CommonListEntry;
import org.net9.minipie.server.data.entity.GroupEntry;

/**
 * @author Seastar
 *
 */
public interface Group_UserStorage {
	public void add(Long groupId,Long userId);
	public void remove(Long groupId,Long userId);
	public void setAdmin(Long groupId,Long userId,Boolean isAdmin);
	//public Collection<CommonListEntry> selectAdmin(Long groupId);
	public Collection<CommonListEntry> selectMember(Long groupId,Boolean isAdim);	//join the user table
	public Collection<GroupEntry> selectGroup(Long userId);
}
