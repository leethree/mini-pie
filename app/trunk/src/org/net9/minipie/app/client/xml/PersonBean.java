/**
 * 
 */
package org.net9.minipie.app.client.xml;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author LeeThree
 *
 */
@SuppressWarnings("serial")
public class PersonBean extends Bean {
	public Collection<GenericBean> addresses = new ArrayList<GenericBean>();
	public Collection<GenericBean> phones = new ArrayList<GenericBean>();
	public Collection<GenericBean> emails = new ArrayList<GenericBean>();
	public Collection<GenericBean> urls = new ArrayList<GenericBean>();
	public Collection<GenericBean> ims = new ArrayList<GenericBean>();
	public Collection<TagBean> tags = new ArrayList<TagBean>();

	public PersonBean() {
	}
}
