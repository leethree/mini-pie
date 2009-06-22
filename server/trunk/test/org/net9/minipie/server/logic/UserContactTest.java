/**
 * UserContactTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.logic.operation.contact.AddMyContact;
import org.net9.minipie.server.logic.operation.user.RemoveUserContact;

/**
 * @author Seastar
 *
 */
public class UserContactTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//long id=0;
		try{
			new RemoveUserContact(2L,6L).execute();
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(id);
	}

}
