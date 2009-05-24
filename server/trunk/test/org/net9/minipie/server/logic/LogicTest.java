/**
 * LogicTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.logic.operation.contact.AddMyContact;

/**
 * @author Seastar
 * 
 */
public class LogicTest {
	public static void main(String avg[]) {
		long i = new Handler<Long>(new AddMyContact(111111L, "oo")).execute();
		System.out.print(i);
	}
}
