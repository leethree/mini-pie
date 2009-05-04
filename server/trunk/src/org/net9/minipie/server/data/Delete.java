/**
 * Delete.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

/**
 * @author Seastar
 *
 */
public class Delete extends Update {
	private Long id;
	/**
	 * Constructor
	 * @param id
	 */
	public Delete(Long id) {
		super();
		setId(id);
	}

	/**
	 * Constructor
	 */
	public Delete() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		if(id<0){
			//TODO Exception
		}
		this.id = id;
	}
}
