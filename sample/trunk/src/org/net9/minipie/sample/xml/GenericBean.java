/**
 * 
 */
package org.net9.minipie.sample.xml;

import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.ociweb.xml.WAX;

/**
 * @author LeeThree
 * 
 */
public class GenericBean extends Bean {

	public GenericBean(String name) {
		beanName = name;
	}
	
	/**
	 * @param ele
	 */
	public GenericBean(Element ele) {
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

	public void set(String key, String value) {
		properties.put(key, value);
	}
	
	public void toXML(WAX wax) {
		wax.start(beanName);
		for (Entry<String, String> property : properties.entrySet()) {
			wax.child(property.getKey(), property.getValue());
		}
		wax.end();
	}
}
