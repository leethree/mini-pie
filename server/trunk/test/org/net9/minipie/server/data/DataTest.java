/**
 * DataTest.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("unused")
public class DataTest {
	public static void main(String[] args) {
		Relationships rels = new Relationships(" rose, sweetheasdrt");
		System.out.println(rels);
		System.out.println(rels.isEmpty());
	}
}
