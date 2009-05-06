/**
 * AddNewContact.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

/**
 * @author Seastar
 * 
 */
public class AddNewContact implements Command<Long> {
	private String name;
	private long userId;

	/**
	 * Constructor
	 */
	public AddNewContact(String name, long id) {
		setName(name);
		setUserId(id);
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		if (userId < 0) {
			// TODO:Exception
		}
		this.userId = userId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null) {
			return;
		}
		name = name.trim();
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.Command#excute()
	 */
	public Long execute() {
		// TODO Auto-generated method stub
		
		System.out.print(name + userId);
		return null;
	}

}
