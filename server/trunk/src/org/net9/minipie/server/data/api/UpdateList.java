/**
 * UpdateList.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author LeeThree
 * 
 */
@XmlRootElement(name = "updates")
public class UpdateList {
	private Collection<Update> updates;

	/**
	 * Constructor
	 */
	public UpdateList() {
	}

	/**
	 * @return the updates
	 */
	public Collection<Update> getUpdates() {
		return updates;
	}

	/**
	 * @param updates
	 *            the updates to set
	 */
	@XmlElements( { @XmlElement(name = "add", type = Add.class),
			@XmlElement(name = "edit", type = Edit.class),
			@XmlElement(name = "delete", type = Delete.class) })
	public void setUpdates(Collection<Update> updates) {
		this.updates = updates;
	}
}
