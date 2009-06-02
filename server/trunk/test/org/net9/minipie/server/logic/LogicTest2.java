/**
 * LogicTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.logic.operation.account.DownloadMyInfo;
import org.net9.minipie.server.logic.operation.contact.AddMyContact;

/**
 * @author Seastar
 * 
 */
public class LogicTest {
	public static void main(String avg[]) {
		String name = new Handler<String>(new DownloadMyInfo(1L,"H:/Win7_Doc/My Document/Work Data/java program/pro")).execute();
		System.out.print(name);
	}
}
