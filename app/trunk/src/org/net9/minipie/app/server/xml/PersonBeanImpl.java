/**
 * 
 */
package org.net9.minipie.app.server.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.net9.minipie.app.client.xml.PersonBean;

/**
 * @author LeeThree
 * 
 */
public class PersonBeanImpl extends BeanImpl {

	private PersonBean bean = new PersonBean();

	public PersonBeanImpl(Element ele) {
		getBean().beanName = ele.getName();
		getBean().id = Long.decode(ele.attributeValue("id"));

		for (Object iter : ele.attributes()) {
			Attribute attr = (Attribute) iter;
			putAttribute(attr);
		}
		for (Object iter : ele.elements()) {
			Element elem = (Element) iter;
			if (elem.getName().equals("address"))
				bean.addresses.add(new GenericBeanImpl(elem).getBean());
			else if (elem.getName().equals("phone"))
				bean.phones.add(new GenericBeanImpl(elem).getBean());
			else if (elem.getName().equals("email"))
				bean.emails.add(new GenericBeanImpl(elem).getBean());
			else if (elem.getName().equals("url"))
				bean.urls.add(new GenericBeanImpl(elem).getBean());
			else if (elem.getName().equals("im"))
				bean.ims.add(new GenericBeanImpl(elem).getBean());
			else if (elem.getName().equals("tag"))
				bean.tags.add(new TagBeanImpl(elem).getBean());
			else if (elem.isTextOnly())
				putElement(elem);
		}

	}

	@Override
	public PersonBean getBean() {
		return bean;
	}
}
