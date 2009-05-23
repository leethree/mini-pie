/**
 * Tag.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Seastar
 * 
 */
public class TagEntry {
	private long id;
	private String name;

	/**
	 * Constructor
	 */
	public TagEntry() {
	}

	public TagEntry(long id, String name) throws DataFormatException {
		setId(id);
		setName(name);
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
	 * @throws DataFormatException 
	 */
	public void setId(long id) throws DataFormatException {
		this.id = Formatter.checkId(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @throws DataFormatException 
	 */
	public void setName(String name) throws DataFormatException {
		if (name == null)
			throw new DataFormatException("Tag name should not be null.");
		this.name = Formatter.removeSpace(name);
	}
}
