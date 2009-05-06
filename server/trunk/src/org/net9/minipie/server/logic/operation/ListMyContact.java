/**
 * ListMyContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;

import org.net9.minipie.server.data.MinimalContact;
import org.net9.minipie.server.db.HibernateDAOFactory;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class ListMyContact implements Command<Collection<MinimalContact>> {
	private Long userId;
	/**
	 * Constructor
	 */
	public ListMyContact(Long userId) {
		setUserId(userId);
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#excute()
	 */
	public Collection<MinimalContact> execute() {
		ContactStorage executor=new HibernateDAOFactory().getContactStorage();
		return executor.selectOwnerContact(userId);
	}
}
