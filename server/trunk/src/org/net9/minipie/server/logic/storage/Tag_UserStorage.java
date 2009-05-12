/**
 * Tag_UserStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.CommonListEntry;

/**
 * @author Seastar
 *
 */
public interface Tag_UserStorage {
	public void add(long tagId,long userId);
	public void del(long tagId,long userId);
	public Collection<TagEntry> selectTagsOfUser(long userId,long ownerId);
	public Collection<CommonListEntry> selectTaggedUser(long tagId);
}
