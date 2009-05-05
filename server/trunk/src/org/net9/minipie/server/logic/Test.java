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
		long i = new Handler<Long>(new AddMyContact("oo", 111111L)).excute();
		System.out.print(i);
	}
}
