/**
 * ContactPhoneNoData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.Bool;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Riversand
 *
 */
public class PhoneNoData {
	private Long id;
	private String value;
	private String type;
	private Bool primary;
	/* the following field is only referenced by user */
	private Permission perm;
	/**
	 * Constructor
	 */
	public PhoneNoData() {
	}
	public PhoneNoData(Long id, String value, String type, Bool primary, Permission perm){
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setPerm(perm);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Bool getPrimary() {
		return primary;
	}
	public void setPrimary(Bool primary) {
		this.primary = primary;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		if(value==null){
			return;
		}
		value = value.trim();
		value = value.replace('(', '-');
		value = value.replace(')', '-');
		int start = 0;
		while(value.charAt(start)!='+' && (value.charAt(start)<'0' || value.charAt(start)>'9')){
			start++;
		}
		if(start==value.length()){
			//TODO exception handling
		}
		String newValue = new String();
		for(int i=start; i<value.length(); i++){
			if((value.charAt(i)<'0' || value.charAt(i)>'9')&&value.charAt(i)!='-'){
				continue;
			}else if(i!=0 && value.charAt(i)=='+'){
				continue;
			}
			newValue.concat(String.valueOf(value.charAt(i)));			
		}
		this.value = value;
	}
	/**
	 * @param perm the perm to set
	 */
	public void setPerm(Permission perm) {
		this.perm = perm;
	}
	/**
	 * @return the perm
	 */
	public Permission getPerm() {
		return perm;
	}
}
