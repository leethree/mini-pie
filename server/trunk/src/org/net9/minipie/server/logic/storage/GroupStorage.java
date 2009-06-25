/**
 * GroupHandler.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.GroupEntity;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.Query;

/**
 * @author Seastar
 *
 */
public interface GroupStorage {
	public Long createGroup(String name,Long userId);
	public void editGroup(Long groupId,InfoField attr,Object value); //two item: name and description
	public void changePermission(Long groupId,Permission Perm);
	public void disband(Long groupId);
	public GroupEntity selectGroup(Long groupId);
	public Collection<GroupEntity> searchGroup(Collection<Query> query);
}
