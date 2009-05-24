/**
 * Group_UserDAO.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;
import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.db.entity.Group2User;
import org.net9.minipie.server.db.entity.Group2User.Id;

/**
 * @author Riversand
 *
 */
public interface Group_UserDAO extends GenericDAO<Group2User, Id> {
	public void add(Long groupId,Long userId);
	public void remove(Long groupId,Long userId);
	public void setAdmin(Long groupId,Long userId,Boolean isAdmin);
	//public Collection<CommonListEntry> selectAdmin(Long groupId);
	public Collection<CommonListEntry> selectMember(Long groupId,Boolean isAdim);	//join the user table
	public Collection<GroupEntry> selectGroup(Long userId);
}
