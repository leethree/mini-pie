/**
 * 
 */
package org.net9.minipie.app.server.xml;

import org.dom4j.Element;
import org.net9.minipie.app.client.xml.Bean;
import org.net9.minipie.app.client.xml.TagBean;

/**
 * @author LeeThree
 * 
 */
public class TagBeanImpl extends BeanImpl {

	private TagBean bean = new TagBean();

	/**
	 * @param ele
	 */
	protected TagBeanImpl(Element ele) {
		getBean().beanName = ele.getName();
		getBean().id = Long.decode(ele.attributeValue("id"));
		bean.tagName = ele.getText();
	}

	@Override
	public TagBean getBean() {
		return bean;
	}

}
