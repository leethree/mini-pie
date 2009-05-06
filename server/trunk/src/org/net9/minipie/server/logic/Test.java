/**
 * Test.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.logic.operation.AddMyContact;

/**
 * @author Seastar
 * 
 */
public class Test {
	void test() {
		long i = new Handler<Long>(new AddMyContact(111111L, "oo")).excute();
		System.out.print(i);
	}
}
