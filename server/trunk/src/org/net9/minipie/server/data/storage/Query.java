/**
 * Query.java
 *     in package: * org.net9.minipie.server.data.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.storage;

import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.InfoType;

/**
 * @author Seastar
 *
 */
public class Query {
	private InfoType type;
	private InfoField field;
	private String value;
	/**
	 * Constructor
	 * @param type
	 * @param field
	 * @param value
	 */
	public Query(InfoType type, InfoField field, String value) {
		super();
		this.type = type;
		this.field = field;
		this.value = value;
	}
	
	/**
	 * @return the field
	 */
	public InfoField getField() {
		return field;
	}
	/**
	 * @return the type
	 */
	public InfoType getType() {
		return type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	public String toString(){
		return type.toString()+" "+field.toString()+" "+value;
	}
}
