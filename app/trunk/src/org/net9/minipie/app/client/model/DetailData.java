/**
 * 
 */
package org.net9.minipie.app.client.model;

import org.net9.minipie.app.client.data.GenericBean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public class DetailData extends BaseModelData {

	public DetailData(GenericBean bean) {

		for (String key : bean.properties.keySet()) {
			this.set(key, bean.get(key));
		}
		this.set("primary", false);
		if (bean.get("primary") != null && bean.get("primary").equals("true"))
			this.set("primary", true);
		if (bean.get("formatted") != null)
			this.set("value", bean.get("formatted"));
		this.set("detail", bean.beanName);
	}
}
