/**
 * 
 */
package org.net9.minipie.app.client.model;

import java.util.ArrayList;
import java.util.List;

import org.net9.minipie.app.client.data.GenericBean;
import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.data.TagBean;

/**
 * @author LeeThree
 * 
 */
@SuppressWarnings("serial")
public abstract class PersonEntry extends Entry {

	private PersonBean bean;

	public PersonEntry(String prefex, PersonBean bean) {
		super(prefex + "-" + bean.id);
		this.bean = bean;
		set("name", bean.get("name"));
		set("image", getImage());
		if (bean.get("rel") != null)
			set("rel", bean.get("rel"));
		else
			set("rel", "N/A");
		if (bean.tags.isEmpty())
			set("tags", "N/A");
		else {
			StringBuffer buffer = new StringBuffer();
			for (TagBean tag : bean.tags) {
				buffer.append(tag.tagName + " ");
			}
			set("tags", buffer.toString());
		}
	}

	public long getId() {
		return bean.id;
	}

	public String getName() {
		return get("name");
	}

	public String getImage() {
		String image = bean.get("image");
		if (image == null || image.isEmpty())
			return "resources/images/thumb.gif";
		return image;
	}

	public String getProperty(String key) {
		return bean.get(key);
	}

	public PersonBean getBean() {
		return bean;
	}

	public List<DetailData> getAllDetails() {
		List<DetailData> list = new ArrayList<DetailData>();
		for (GenericBean b : bean.addresses)
			list.add(new DetailData(b));
		for (GenericBean b : bean.emails)
			list.add(new DetailData(b));
		for (GenericBean b : bean.phones)
			list.add(new DetailData(b));
		for (GenericBean b : bean.ims)
			list.add(new DetailData(b));
		for (GenericBean b : bean.urls)
			list.add(new DetailData(b));
		return list;
	}
}
