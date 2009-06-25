/**
 * TagStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntity;
import org.net9.minipie.server.data.storage.TagData;

/**
 * @author Seastar
 *
 */
public interface TagStorage {
	public Long addTag(Long userId,String tagName);
	public void editTag(Long tagId,String newName);
	public void removeTag(Long tagId);
	public Long selectId(Long userId,String tagName);
	public TagData selectTag(Long tagId);
	public Collection<TagEntity> selectAllTags(Long userId);
}
