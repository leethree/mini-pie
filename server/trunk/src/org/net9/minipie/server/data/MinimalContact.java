/**
 * MinimalContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

/**
 * @author Seastar, Riversand
 * 
 */
public class MinimalContact {
	private long id;
	private String name;
	private String image;

	public MinimalContact() {
	}

	public MinimalContact(long id, String name, String image) {
		setId(id);
		setName(name);
		setImage(image);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		if (id < 0) {
			// TODO:Exception
		}
		this.id = id;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		if (image == null) {
			return;
		}
		image = image.trim().toLowerCase();
		this.image = image;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if(name==null){
			return;
		}
		name = name.trim();
		this.name = name;
	}

}
