/**
 * 
 */
package org.net9.minipie.app.server;

import java.util.UUID;

import org.net9.minipie.app.client.xml.GenericBean;
import org.net9.minipie.app.client.xml.TagBean;
import org.net9.minipie.app.client.xml.PersonBean;

/**
 * @author LeeThree
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Backend back = new Backend("PhillipR", "328werD");
//		PersonBean bean = back.getUserById(2).getBean();
//		System.out.println(bean.getId());
//		System.out.println(bean.get("name"));
//		System.out.println(bean.get("rel"));
//		System.out.println(bean.get("image"));
//		for (GenericBean addBean : bean.getAddresses()) {
//			System.out.println(addBean.getId());
//			System.out.println(addBean.get("formatted"));
//			System.out.println(addBean.get("primary"));
//		}
//		for (TagBean tagBean : bean.getTags()) {
//			System.out.println(tagBean.getId());
//			System.out.println(tagBean.getName());
//		}
		UUID uid = UUID.randomUUID();
		System.out.println(uid.toString());
	}

}
