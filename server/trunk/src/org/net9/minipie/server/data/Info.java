/**
 * Info.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.InfoType;

/**
 * @author Seastar
 *
 */
public abstract class Info {
	private InfoType type;
	//private Info info;
	/**
	 * @return the type
	 */
	public InfoType getInfoType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setInfoType(InfoType type) {
		this.type = type;
	}
	public abstract Info getInfo();
}