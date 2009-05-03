/**
 * Tag.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

/**
 * @author Seastar
 *
 */
public class Tag {
	private Long id;
	private String name;
	/**
	 * Constructor
	 */
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	public Tag(Long id,String name){
		setId(id);
		setName(name);
	}
	/**
	 * @return the id
	 */
	public Long getId() {
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
	public void setId(Long id) {
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