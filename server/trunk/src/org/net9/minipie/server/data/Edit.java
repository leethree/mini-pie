/**
 * Edit.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.constant.InfoField;

/**
 * @author Seastar
 * 
 */
public class Edit extends Update {
	private InfoField field;
	private Long id;
	private String value;

	
	public Edit() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 * @param field
	 * @param id
	 * @param value
	 */
	/**
	 * Constructor
	 */
	public Edit(InfoField field, Long id, String value) {
		super();
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
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
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
