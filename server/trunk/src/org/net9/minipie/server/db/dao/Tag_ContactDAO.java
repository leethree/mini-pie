/**
 * Tag_ContactDAO.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.db.entity.Tag2Contact;
import org.net9.minipie.server.db.entity.Tag2Contact.Id;

/**
 * @author Riversand
 *
 */
public interface Tag_ContactDAO extends GenericDAO<Tag2Contact, Id> {
	public void add(Long tagId,Long contactId);
	public void del(Long tagId,Long contactId);
	public Collection<TagEntry> selectTagsOfContact(Long contactId);
	//�����ӣ�contact
	public Collection<CommonListEntry> selectTaggedContact(Long tagId);
}
