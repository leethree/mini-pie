/**
 * AddNewContact.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class AddMyContact extends Command<Long> {
	private String name;
	private Long userId;

	/**
	 * Constructor
	 */
	public AddMyContact(Long id, String name) {
		super();
		setName(name);
		setUserId(id);
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null) {
			throw new InvalidRequestException("Contact name can not be null");
		}
		name = name.trim();
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.Command#execute()
	 */
	public Long execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		return executor.addUserContact(userId, name);
	}

}
