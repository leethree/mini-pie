/**
 * TagDAO.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.TagData;
import org.net9.minipie.server.db.entity.Tag;

/**
 * @author Riversand
 *
 */
public interface TagDAO extends GenericDAO<Tag, Long> {
	public Long addTag(Long userId,String tagName);
	public void editTag(Long tagId,String newName);
	public void removeTag(Long tagId);
	public Long selectId(Long userId,String tagName);
	public TagData selectTag(Long tagId);
	public Collection<TagEntry> selectAllTags(Long userId);
}
