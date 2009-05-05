/**
 * AddNewContact.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import org.net9.minipie.server.db.HibernateDAOFactory;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class AddMyContact implements Command<Long> {
	private String name;
	private Long userId;

	/**
	 * Constructor
	 */
	public AddMyContact(String name, Long id) {
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
	 * @see org.net9.minipie.server.logic.Command#excute()
	 */
	public Long excute() {
		// TODO Auto-generated method stub
		//long returnValue;
		ContactStorage executor=new HibernateDAOFactory().getContactStorage();
		
		//System.out.print(name + userId);
		//try{
			return executor.addUserContact(userId, name);
		}
//		catch(Exception e){
//			throw new UnknownServerException("Database can't complete the mission");
//		}
//		return returnValue;
	
}


