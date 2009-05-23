/**
 * Tag_ContactStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.BasicContact;


/**
 * @author Seastar
 *
 */
public interface Tag_ContactStorage {
	public void add(Long tagId,Long contactId);
	public void del(Long tagId,Long contactId);
	public Collection<TagEntry> selectTagsOfContact(Long contactId);
	//±Ì¡¨Ω”£∫contact
	public Collection<BasicContact> selectTaggedContact(Long tagId);
}
