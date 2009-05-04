/**
 * MinimalContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

/**
 * @author Seastar
 * 
 */
public class MinimalContact {
	private long id;
	private String nickName;
	private String image;

	public MinimalContact() {
	}

	public MinimalContact(long id, String name, String image) {
		setId(id);
		setNickName(name);
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
	public String getNickName() {
		return nickName;
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
	public void setNickName(String name) {
		if (nickName == null) {
			return;
		}
		nickName = nickName.trim();
		this.nickName = name;
	}

}
