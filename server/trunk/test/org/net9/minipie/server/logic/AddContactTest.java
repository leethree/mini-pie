/**
 * AddContactTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.logic.operation.contact.AddMyContact;
import org.net9.minipie.server.logic.operation.contact.UpdateMyContact;

/**
 * @author Seastar
 *
 */
public class AddContactTest {
	public static void main(String avg[]) {
		long id=0;
		try{
			id=new AddMyContact(1L,"hou%%jie").execute();
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(id);
	}
}
