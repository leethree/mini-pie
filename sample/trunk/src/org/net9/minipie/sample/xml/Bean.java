/**
 * 
 */
package org.net9.minipie.sample.xml;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * @author LeeThree
 * 
 */
public abstract class Bean{
	
	public long id;
	public String beanName;

	public Map<String, String> properties = new HashMap<String, String>();

	public String get(String key) {
		return properties.get(key);
	}

	protected void putElement(Element ele) {
		properties.put(ele.getName(), ele.getText());
	}

	protected void putAttribute(Attribute attr) {
		properties.put(attr.getName(), attr.getText());
	}
	
}
