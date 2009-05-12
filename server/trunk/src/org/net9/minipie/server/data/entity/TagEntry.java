/**
 * Tag.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.entity;

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
		// TODO Auto-generated constructor stub
	}
	public TagEntry(long id,String name){
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		if(id<0){
			//TODO:Exception
		}
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(name==null){
			return;
		}
		this.name = name;
	}
}
