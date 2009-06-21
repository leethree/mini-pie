/**
 * UpdataTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import java.util.Collection;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.UserListEntry;
import org.net9.minipie.server.logic.operation.account.UpdateMyInfo;
import org.net9.minipie.server.logic.operation.contact.UpdateMyContact;
import org.net9.minipie.server.logic.operation.user.SearchAllUser;

/**
 * @author Seastar
 *
 */
public class DelTest {	
		public static void main(String avg[]) {
			Delete data=new Delete();
			data.setId(5L);
			data.setType("address");
			try{
				new UpdateMyContact(1L,4L,data).execute();
			}catch (Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
	
}
