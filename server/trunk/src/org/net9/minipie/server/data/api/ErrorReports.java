/**
 * ErrorReport.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import java.util.Collection;
import java.util.Vector;

/**
 * @author Seastar
 *
 */
public class ErrorReports {
	
	Collection<ErrorReport> err;
	int size;
	/**
	 * Constructor
	 * @param err
	 */
	public ErrorReports() {
		super();
		this.err = new Vector<ErrorReport>();
	}
	public void addStatus(ErrorReport e){
		this.err.add(e);
	}
	/**
	 * @return the err
	 */	
	public Collection<ErrorReport> getErr() {
		return err;
	}
	
	
}
