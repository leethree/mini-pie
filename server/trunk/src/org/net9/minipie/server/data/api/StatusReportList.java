/**
 * StatusReportList.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Seastar
 * 
 */
@XmlRootElement(name = "multistatus")
public class StatusReportList {
	private Collection<StatusReport> statuses;

	public static final int MULTI_STATUS = 207;

	/**
	 * Constructor
	 * 
	 * @param statuses
	 */
	public StatusReportList() {
		this.statuses = new Vector<StatusReport>();
	}

	public void addStatus(StatusReport e) {
		this.statuses.add(e);
	}

	/**
	 * @return the err
	 */
	@XmlElement(name = "response")
	public Collection<StatusReport> getStatuses() {
		return statuses;
	}

}
