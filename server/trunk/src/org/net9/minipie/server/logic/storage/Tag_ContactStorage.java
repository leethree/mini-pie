/**
 * Tag_ContactStorage.java
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
public interface Tag_ContactStorage {
	public void add(long tagId,long contactId);
	public void del(long tagId,long contactId);
	public Collection<TagEntry> selectTagsOfContact(long contactId,long ownerId);
	//±Ì¡¨Ω”£∫contact
	public Collection<CommonListEntry> selectTaggedContact(long tagId);
}
