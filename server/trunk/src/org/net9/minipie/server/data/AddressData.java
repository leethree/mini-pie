/**
 * ContactAddressData.java
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
public class AddressData {
	private Long id;
	private String value;
	private String type;
	private Bool primary;
	/* the following fields are only referenced by user */
	private String formatted;                  
	private String zipcode;         
	private Permission perm;
	/**
	 * Constructor
	 */
	public AddressData() {
	}
	public AddressData(Long id, String value, String type, Bool primary, 
			String formatted, String zipcode, Permission perm){
		setId(id);
		setValue(value);
		setType(type);
		setPrimary(primary);
		setFormatted(formatted);
		setZipcode(zipcode);
		setPerm(perm);
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		if(value==null){
			return;
		}
		value = value.trim();
		this.value = value;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(Bool primary) {
		this.primary = primary;
	}
	/**
	 * @return the primary
	 */
	public Bool getPrimary() {
		return primary;
	}
	/**
	 * @param formatted the formatted to set
	 */
	public void setFormatted(String formatted) {
		if(formatted==null){
			return;
		}
		formatted = formatted.trim();
		this.formatted = formatted;
	}
	/**
	 * @return the formatted
	 */
	public String getFormatted() {
		return formatted;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		if(zipcode==null){
			return;
		}
		zipcode = zipcode.trim();
		zipcode = zipcode.replace('(', '-');
		zipcode = zipcode.replace(')', '-');
		this.zipcode = zipcode;
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
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
