/**
 * UserAuth.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class UserAuth {
	private String name;
	private String pwd;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param pwd
	 */
	public UserAuth(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}

	/**
	 * Return the userId of corresponding user, -1 if illegal
	 * 
	 * @return
	 */
	public long getUserId() {
		return new Handler<Long>(new CheckUser()).execute();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	
	private class CheckUser extends Command<Long>{

		/**
		 * Constructor
		 */
		public CheckUser() {
			
		}
		/* (non-Javadoc)
		 * @see org.net9.minipie.server.logic.operation.Command#execute()
		 */
		@Override
		public Long execute() {
			UserStorage executor = getStorageFactory().getUserStorage();
			try {
				return executor.selectLegalUser(name, pwd);
			} catch (NotFoundException e) {
				return -1L;
			}
		}
		
	}
}
