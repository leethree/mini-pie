/**
 * IllegalUser.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.db.HibernateDAOFactory;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class IllegalUser {
	private String name;
	private String pwd;
	/**
	 * Constructor
	 * @param name
	 * @param pwd
	 */
	public IllegalUser(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}	
	
	public boolean isIllegalUser(){
		UserStorage executor=new HibernateDAOFactory().getUserStorage();
		try{
			executor.selectLegalUser(name, pwd);
		}catch (NotFoundException e){
			return false;
		}
		return true;
	}
	
}
