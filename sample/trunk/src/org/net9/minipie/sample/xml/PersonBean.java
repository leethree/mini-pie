/**
 * 
 */
package org.net9.minipie.sample.xml;

import java.util.ArrayList;
import java.util.Collection;

import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * @author LeeThree
 * 
 */
public class PersonBean extends Bean {
	
	public Collection<GenericBean> addresses = new ArrayList<GenericBean>();
	public Collection<GenericBean> phones = new ArrayList<GenericBean>();
	public Collection<GenericBean> emails = new ArrayList<GenericBean>();
	public Collection<GenericBean> urls = new ArrayList<GenericBean>();
	public Collection<GenericBean> ims = new ArrayList<GenericBean>();
	public Collection<TagBean> tags = new ArrayList<TagBean>();

	public PersonBean(Element ele) {
		beanName = ele.getName();
		id = Long.decode(ele.attributeValue("id"));

		for (Object iter : ele.attributes()) {
			Attribute attr = (Attribute) iter;
			putAttribute(attr);
		}
		for (Object iter : ele.elements()) {
			Element elem = (Element) iter;
			if (elem.getName().equals("address"))
				addresses.add(new GenericBean(elem));
			else if (elem.getName().equals("phone"))
				phones.add(new GenericBean(elem));
			else if (elem.getName().equals("email"))
				emails.add(new GenericBean(elem));
			else if (elem.getName().equals("url"))
				urls.add(new GenericBean(elem));
			else if (elem.getName().equals("im"))
				ims.add(new GenericBean(elem));
			else if (elem.getName().equals("tag"))
				tags.add(new TagBean(elem));
			else if (elem.isTextOnly())
				putElement(elem);
		}

	}

}
