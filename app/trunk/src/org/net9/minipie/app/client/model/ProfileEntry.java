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
public class ProfileEntry extends PersonEntry{

	public ProfileEntry(PersonBean bean) {
		super("Profile", bean);
		set("icon", "user-suit");
		set("category", "Personal Profile");
		
		// TODO to-be removed
		set("name", bean.get("displayName"));
	}
}
