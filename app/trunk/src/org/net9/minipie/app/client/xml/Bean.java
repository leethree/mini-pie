/**
 * 
 */
package org.net9.minipie.app.client.xml;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public abstract class Bean implements Serializable {

	public long id;
	public String beanName;

	public Map<String, String> properties = new HashMap<String, String>();

	public Bean() {
	}

	public String get(String key) {
		return properties.get(key);
	}

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
}
