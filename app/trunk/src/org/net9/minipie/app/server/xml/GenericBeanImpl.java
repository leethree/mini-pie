/**
 * 
 */
package org.net9.minipie.app.server.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.net9.minipie.app.client.xml.Bean;
import org.net9.minipie.app.client.xml.GenericBean;

/**
 * @author LeeThree
 * 
 */
public class GenericBeanImpl extends BeanImpl {

	private GenericBean bean = new GenericBean();

	/**
	 * @param ele
	 */
	protected GenericBeanImpl(Element ele) {
		getBean().beanName = ele.getName();
		getBean().id = Long.decode(ele.attributeValue("id"));

		for (Object iter : ele.attributes()) {
			Attribute attr = (Attribute) iter;
			putAttribute(attr);
		}
		for (Object iter : ele.elements()) {
			Element elem = (Element) iter;
			if (elem.isTextOnly())
				putElement(elem);
		}
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.app.server.xml.BeanImpl#getBean()
	 */
	@Override
	public GenericBean getBean() {
		return bean;
	}

}
