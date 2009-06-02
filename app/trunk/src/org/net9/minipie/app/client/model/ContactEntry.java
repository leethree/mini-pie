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
public class ContactEntry extends PersonEntry {
	/**
	 * @param bean
	 */
	public ContactEntry(PersonBean bean) {
		super("Contact", bean);
		set("icon", "icon-contact");
		set("category", "Contact");
	}
}
