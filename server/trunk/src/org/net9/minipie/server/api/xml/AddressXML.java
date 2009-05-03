/**
 * AddressXML.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

/**
 * @author LeeThree
 * 
 */
public class AddressXML {
	private int id;
	private String address;
	private String zip;

	/**
	 * Constructor
	 */
	public AddressXML() {

	}

	/**
	 * Constructor
	 */
	public AddressXML(int id, String address, String zip) {
		this.id = id;
		this.address = address;
		this.zip = zip;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
