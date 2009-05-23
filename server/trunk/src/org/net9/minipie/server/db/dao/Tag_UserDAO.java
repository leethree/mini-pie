/**
 * Tag_UserDAO.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.db.entity.Tag2User;
import org.net9.minipie.server.db.entity.Tag2User.Id;

/**
 * @author Riversand
 *
 */
public interface Tag_UserDAO extends GenericDAO<Tag2User, Id> {
	public void add(Long tagId,Long userId);
	public void del(Long tagId,Long userId);
	public Collection<TagEntry> selectTagsOfUser(Long userId,Long ownerId);
	//±Ì¡¨Ω”£∫user
	public Collection<BasicUser> selectTaggedUser(Long tagId);
}
