/**
 * LogicTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.logic.operation.account.DownloadMyInfo;

/**
 * @author Seastar
 * 
 */
public class DownloadTest {
	public static void main(String avg[]) {
		String name = new Handler<String>(new DownloadMyInfo(3L,"H:/Win7_Doc/My Document/Work Data/java program/pro")).execute();
		System.out.print(name);
	}
}
