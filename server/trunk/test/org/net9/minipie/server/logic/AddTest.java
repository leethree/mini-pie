/**
 * AddTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.data.api.Add;
import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.EmailXml;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.logic.operation.account.UpdateMyInfo;
import org.net9.minipie.server.logic.operation.contact.UpdateMyContact;
import org.net9.minipie.server.logic.operation.user.UpdateUserShadow;

/**
 * @author Seastar
 *
 */
public class AddTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Add data=new Add();
		EmailXml em=new EmailXml();
		em.setPrimary("true");
		em.setType("mm");
		em.setValue("mm.m@www.orz.com");
		
		data.setDetail(em);
		data.setType("email");
//		try{
//			new UpdateMyContact(1L,2L,data).execute();
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		try{
//			new UpdateMyInfo(1L,data).execute();
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		try{
			new UpdateUserShadow(1L,5L,data).execute();
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
