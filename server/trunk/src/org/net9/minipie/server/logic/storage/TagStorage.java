/**
 * TagStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;

/**
 * @author Seastar
 *
 */
public interface TagStorage {
	public long addTag(long userId,String tagName);
	public void editTag(long tagId,String newName);
	public void removeTag(long tagId);
	public long selectId(long userId,String tagName);
	public String selectName(long tagId);
	public Collection<TagEntry> selectAllTags(long userId);
}
