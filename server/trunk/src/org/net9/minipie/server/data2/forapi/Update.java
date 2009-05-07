/**
 * Update.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.forapi;

import org.net9.minipie.server.data.constant.InfoType;

/**
 * @author Seastar
 * 
 */
public abstract class Update {
	protected InfoType type;

	/**
	 * Constructor
	 */
	public Update() {
	}

	/**
	 * Constructor
	 */
	public Update(InfoType type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public InfoType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(InfoType type) {
		this.type = type;
	}
}
