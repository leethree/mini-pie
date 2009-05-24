/**
 * GroupHandler.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import org.net9.minipie.server.data.entity.GroupEntry;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;

/**
 * @author Seastar
 *
 */
public interface GroupStorage {
	public Long createGroup(String name,Long userId);
	public void editGroup(Long groupId,InfoField attr,String value); //two item: name and description
	public void changePermission(Long groupId,Permission Perm);
	public void dispand(Long groupId);
	public GroupEntry selectGroup(Long groupId);
}
