/**
 * 
 */
package org.net9.minipie.sample.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * @author LeeThree
 * 
 */
public class GenericBean extends Bean {

	/**
	 * @param ele
	 */
	protected GenericBean(Element ele) {
		beanName = ele.getName();
		id = Long.decode(ele.attributeValue("id"));

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

}
