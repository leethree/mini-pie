/**
 * Add.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.InfoType;

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
		// TODO Auto-generated constructor stub
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
