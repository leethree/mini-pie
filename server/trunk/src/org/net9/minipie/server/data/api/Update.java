/**
 * Update.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
public abstract class Update {
	private InfoType type;

	/**
	 * Constructor
	 */
	public Update() {
	}

	/**
	 * Constructor
	 */
	public Update(InfoType type) {
		setType(type);
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
	protected void setType(InfoType type) {
		if (type == null)
			throw new ServerErrorException("Information field should not be null.");
		this.type = type;
	}
	
	public abstract Update checkThis() ; 
}
