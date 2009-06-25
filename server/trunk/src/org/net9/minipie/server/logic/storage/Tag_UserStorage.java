/**
 * Tag_UserStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntity;
import org.net9.minipie.server.data.storage.BasicUser;

/**
 * @author Seastar
 *
 */
public interface Tag_UserStorage {
	public void add(Long tagId,Long userId);
	public void del(Long tagId,Long userId);
	public Collection<TagEntity> selectTagsOfUser(Long userId,Long ownerId);
	//��l�ӣ�user
	public Collection<BasicUser> selectTaggedUser(Long tagId);
}
