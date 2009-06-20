/**
 * User_userDAO.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;
import java.util.Map;

import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.UserRelation;
import org.net9.minipie.server.db.entity.User2User;
import org.net9.minipie.server.db.entity.User2User.Id;

/**
 * @author Riversand
 *
 */
public interface User_UserDAO extends GenericDAO<User2User, Id> {
	public void add(Long userId1, Long userId2);
	public void del(Long userId1, Long userId2);
	public void edit(Long userId1, Long userId2, InfoField attribute, Object value);
	public String selectRelationship(Long userId1, Long userId2);
	public Collection<CommonListEntry> selectSharedUser(Long userId, Permission permission);
	public Collection<UserRelation> selectMyUserContact(Long id);
	public Map<Long, Long> getRelations();
}
