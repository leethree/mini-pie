/**
 * Add.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data2.api;

import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.data2.entity.Info;

/**
 * @author Seastar
 *
 */
public class Add extends Update {
	private Info info;
	/**
	 * Constructor
	 */
	public Add() {
	}
	public Add(InfoType type, Info info){
		super(type);
		setInfo(info);
	}
	/**
	 * @return the info
	 */
	public Info getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(Info info) {
		if (info==null){
			//TODO:Exception
		}
		this.info = info;
	}
}