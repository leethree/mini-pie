/**
 * 
 */
package org.net9.minipie.sample.xml;

import org.dom4j.Element;

/**
 * @author LeeThree
 * 
 */
public class TagBean extends Bean {
	
	public String tagName;

	/**
	 * @param ele
	 */
	public TagBean(Element ele) {
		beanName = ele.getName();
		id = Long.decode(ele.attributeValue("id"));
		tagName = ele.getText();
	}

}
