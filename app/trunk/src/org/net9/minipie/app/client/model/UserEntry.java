/**
 * 
 */
package org.net9.minipie.app.client.model;

import org.net9.minipie.app.client.data.PersonBean;

/**
 * @author LeeThree
 *
 */
@SuppressWarnings("serial")
public class UserEntry extends PersonEntry {

	/**
	 * @param bean
	 */
	public UserEntry(PersonBean bean) {
		super("UserContact", bean);
		set("icon", "user");
		set("category", "User Contact");
	}

}
