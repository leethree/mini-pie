/**
 * 
 */
package org.net9.minipie.app.server;

import java.util.List;
import java.util.UUID;

import org.net9.minipie.app.client.data.GenericBean;
import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.data.TagBean;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;

/**
 * @author LeeThree
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			List<PersonBean> list = new Backend("Philip", "123456").listUserContacts();
			for (PersonBean personBean : list) {
				System.out.println(personBean.id);
				System.out.println(personBean.beanName);
				System.out.println(personBean.get("name"));
			}
		} catch (GenericException e) {
			e.printStackTrace();
		} catch (LoginFailedException e) {
			e.printStackTrace();
		}
	}

}
