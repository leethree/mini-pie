/**
 * 
 */
package org.net9.minipie.sample.xml;

import com.ociweb.xml.WAX;

/**
 * @author LeeThree
 * 
 */
public class UpdateBean extends Bean {
	private enum Update {
		ADD, EDIT, DELETE
	};

	private Update update;

	// for ADD only
	private GenericBean bean;

	// ADD
	public UpdateBean(GenericBean bean) {
		update = Update.ADD;
		this.bean = bean;
	}

	// EDIT
	public UpdateBean(String type, String field, long id, String value) {
		update = Update.EDIT;
		set("type", type);
		set("field", field);
		if (id > 0)
			set("id", Long.toString(id));
		set("value", value);
	}

	// DELETE
	public UpdateBean(String type, long id) {
		update = Update.DELETE;
		set("type", type);
		set("id", Long.toString(id));
	}

	public void set(String key, String value) {
		properties.put(key, value);
	}

	public void toXML(WAX wax) {
		switch (update) {
		case ADD:
			wax.start("add").attr("type", bean.beanName);
			bean.toXML(wax);
			wax.end();
			break;
		case DELETE:
			wax.start("delete").attr("type", get("type"))
					.child("id", get("id")).end();
			break;
		case EDIT:
			wax.start("edit").attr("type", get("type")).attr("field",
					get("field"));
			if (get("id") != null)
				wax.child("id", get("id"));
			wax.child("value", get("value")).end();
			break;
		default:
			break;
		}
	}
}
