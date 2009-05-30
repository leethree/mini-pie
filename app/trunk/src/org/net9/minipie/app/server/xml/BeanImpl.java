/**
 * 
 */
package org.net9.minipie.app.server.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.net9.minipie.app.client.xml.Bean;

/**
 * @author LeeThree
 * 
 */
public abstract class BeanImpl{
	
//	protected Bean bean;
//
//	protected BeanImpl(Element ele) {
//		getBean().beanName = ele.getName();
//		getBean().id = Long.decode(ele.attributeValue("id"));
//	}

	protected void putElement(Element ele) {
		getBean().properties.put(ele.getName(), ele.getText());
	}

	protected void putAttribute(Attribute attr) {
		getBean().properties.put(attr.getName(), attr.getText());
	}
	
	/**
	 * @return the bean
	 */
	public abstract Bean getBean();

}
