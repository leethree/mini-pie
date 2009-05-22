/**
 * ContactListEntry.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.entity.CommonEntity;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public class CommonListEntry {
	private CommonEntity entity;

	public CommonListEntry(long id, String name, String image) throws DataFormatException {
		entity = new CommonEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setImage(image);
	}

	/**
	 * @return the entity
	 */
	public CommonEntity getEntity() {
		return entity;
	}

}
