/**
 * Delete.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.api;

import org.net9.minipie.server.data.constant.InfoType;

/**
 * @author Seastar
 * 
 */
public class Delete extends Update {
	private long id;

	/**
	 * Constructor
	 */
	public Delete() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public Delete(InfoType type, long id) {
		super(type);
		setId(id);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		if (id < 0) {
			// TODO Exception
		}
		this.id = id;
	}
}
