/**
 * ContactURLData.java
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
public class URLData {
	private Long id;	
	private String value;
	private String type;
	private Bool primary;
	/* the following field is only referenced by user */
	private Permission perm;
	/**
	 * Constructor
	 */
	public URLData() {
		
	}
	public URLData(Long id, String value, String type, Bool primary, Permission perm){
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
		value = value.toLowerCase();
		if(value.startsWith("http://")==false){
			//TODO exception handling
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
