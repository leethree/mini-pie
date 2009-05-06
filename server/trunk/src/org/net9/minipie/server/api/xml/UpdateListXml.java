/**
 * UpdateListXml.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.api.xml;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.net9.minipie.server.data.Update;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "updates")
public class UpdateListXml {
	private Collection<UpdateXml> updates;

	/**
	 * Constructor
	 */
	public UpdateListXml() {
	}

	/**
	 * @return the updates
	 */
	@XmlElements( { @XmlElement(name = "add", type = AddXml.class),
			@XmlElement(name = "edit", type = EditXml.class),
			@XmlElement(name = "delete", type = DeleteXml.class) })
	public Collection<UpdateXml> getUpdates() {
		return updates;
	}

	/**
	 * @param updates
	 *            the updates to set
	 */
	public void setUpdates(Collection<UpdateXml> updates) {
		this.updates = updates;
	}

	@XmlTransient
	public Collection<Update> getEntity() {
		Collection<Update> collection = new ArrayList<Update>();
		for (UpdateXml updateXml : updates) {
			collection.add(updateXml.getUpdate());
		}
		return collection;
	}
}
