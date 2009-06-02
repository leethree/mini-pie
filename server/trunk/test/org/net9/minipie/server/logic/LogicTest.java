/**
 * LogicTest.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import java.net.URI;


/**
 * @author Seastar
 * 
 */
public class LogicTest {
	public static void main(String avg[]) {
		//long i = new Handler<Long>(new AddMyContact(111111L, "oo")).execute();
		//System.out.print(i);
		
		System.out.println(URI.create("http://localhost:8080/Mini-Pie/services/").resolve("/images"));
	}
}
