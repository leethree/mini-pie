/**
 * EditTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.Edit;
import org.net9.minipie.server.logic.operation.account.UpdateMyInfo;
import org.net9.minipie.server.logic.operation.contact.UpdateMyContact;

/**
 * @author Seastar
 *
 */
public class EditTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Edit data=new Edit();
		data.setId(3L);
		data.setType("im");
		data.setField("type");
		data.setValue("bl");
//		try{
//			new UpdateMyInfo(3L,data).execute();
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		try{
			new UpdateMyContact(1L,2L,data).execute();
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
