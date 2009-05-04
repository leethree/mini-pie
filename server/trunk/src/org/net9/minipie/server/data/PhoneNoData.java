/**
 * ContactPhoneNoData.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.InfoType;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Riversand
 *
 */
public class PhoneNoData extends Info{
	private long id;
	private String value;
	private String type;
	private boolean primary;
	/* the following field is only referenced by user */
	private Permission perm;
	/**
	 * Constructor
	 */
	public PhoneNoData() {
		setInfoType(InfoType.PHONE);
	}
	public PhoneNoData(long id, String value, String type, boolean primary, Permission perm){
		setInfoType(InfoType.PHONE);
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setPerm(perm);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		if(value==null){
			this.value = null;
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
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.data.Info#getInfo()
	 */
	@Override
	public Info getInfo() {
		// TODO Auto-generated method stub
		return this;
	}

	
}
