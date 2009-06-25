/**
 * UploadTest2.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.net9.minipie.server.logic.operation.account.UploadMyInfo;

/**
 * @author Seastar
 *
 */
public class UploadTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name="H:/Win7_Doc/My Document/Work Data/java program/test.txt";
		String path="H:/Win7_Doc/My Document/Work Data/java program/";
		try {
			String s=new UploadMyInfo(2L,new FileInputStream(new File(name)),path).execute();
			System.out.println(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
