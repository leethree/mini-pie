/**
 * 
 */
package org.net9.minipie.sample;

import org.net9.minipie.sample.exception.GenericException;
import org.net9.minipie.sample.exception.LoginFailedException;
import org.net9.minipie.sample.xml.GenericBean;
import org.net9.minipie.sample.xml.UpdateBean;

import com.ociweb.xml.WAX;

/**
 * @author LeeThree
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Backend back = new Backend("leethree", "e9433h");
		GenericBean bean = new GenericBean("address");
		bean.set("type", "home");
		bean.set("value", "Chengdu");
		bean.set("zipcode", "510000");
		UpdateBean update = new UpdateBean("address", 24);
		try {
			back.updateProfile(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
