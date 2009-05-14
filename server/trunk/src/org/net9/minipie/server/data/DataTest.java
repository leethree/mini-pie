/**
 * DataTest.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author LeeThree
 * 
 */
public class DataTest {
	public static void main(String[] args) {
		try {
			//UserEntity entity = new UserEntity();
			PhoneNoData phone = new PhoneNoData();
			phone.setValue("+(010)159-*&&^&102-9944_");
			System.out.println(phone.getValue());
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
