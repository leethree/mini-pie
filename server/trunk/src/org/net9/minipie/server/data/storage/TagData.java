/**
 * Tag.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

/**
 * @author Seastar
 *
 */
/**
 * Tag.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */

/**
 * @author Seastar
 * 
 */
public class TagData {
	private long id;
	private String name;
	private long ownerId;

	/**
	 * Constructor
	 */
	public TagData(long id, String name, long ownerId) {
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the ownerId
	 */
	public long getOwnerId() {
		return ownerId;
	}
}
