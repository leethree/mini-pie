/**
 * UpdateInfo.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.UpdateType;

/**
 * @author Seastar
 *
 */
public class UpdateInfo <T extends Info>{
	private UpdateType type;
	private T info;
	
	public UpdateInfo(){
		
	}
	public UpdateInfo(UpdateType type,T info){
		setType(type);
		setInfo(info);
	}
	/**
	 * @param type the type to set
	 */
	public void setType(UpdateType type) {
		this.type = type;
	}
	/**
	 * @return the type
	 */
	public UpdateType getType() {
		return type;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(T info) {
		this.info = info;
	}
	/**
	 * @return the info
	 */
	public T getInfo() {
		return info;
	}
	/**
	 * @return the info
	 */
	/**
	public Info getInfo() {
		switch(info.getInfoType()){
			case ADDRESS:
				return (AddressData)info.getInfo();
				//break;
			case BASIC:
				return (BasicContact)info.getInfo();
			case EMAIL:
				return (EmailData)info.getInfo();
			case IM:
				return (IMData)info.getInfo();
			case PHONE:
				return (PhoneNoData)info.getInfo();
			case URL:
				return (URLData)info.getInfo();
			default:
				//TODO:Exception
		}
		return null;
	}
	*/
}