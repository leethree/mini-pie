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
	
	public IllegalUser(String name){
		super();
		this.name=name;
	}
	
	public String getPassword(){
		UserStorage executor=new HibernateDAOFactory().getUserStorage();
		try {
			return executor.selectUserPassword(name);
		}catch (NotFoundException e){
			return null;
		}
		
	}
	public boolean isIllegalUser(){
		UserStorage executor=new HibernateDAOFactory().getUserStorage();
		try{
			executor.selectLegalUser(name, pwd);
		}catch (NotFoundException e){
			return true;
		}
		return false;
	}
	
}
