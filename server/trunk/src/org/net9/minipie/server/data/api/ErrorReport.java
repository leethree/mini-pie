/**
 * ErrorReport.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;

/**
 * @author Seastar
 *
 */
public class ErrorReport {
	Collection<String> err;
	/**
	 * Constructor
	 * @param err
	 */
	public ErrorReport(Collection<String> err) {
		super();
		this.err = err;
	}
	/**
	 * @return the err
	 */
	
	public Collection<String> getErr() {
		return err;
	}
}
