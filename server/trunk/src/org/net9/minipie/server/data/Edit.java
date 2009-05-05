/**
 * Edit.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.InfoField;
import org.net9.minipie.server.data.constant.InfoType;

/**
 * @author Seastar
 * 
 */
public class Edit extends Update {
	private InfoField field;
	private long id;
	private String value;

	/**
	 * Constructor
	 */
	public Edit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param field
	 * @param id
	 * @param value
	 */
	public Edit(InfoType type, InfoField field, long id, String value) {
		super(type);
		setField(field);
		setId(id);
		setValue(value);
	}

	/**
	 * @return the field
	 */
	public InfoField getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(InfoField field) {
		this.field = field;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
