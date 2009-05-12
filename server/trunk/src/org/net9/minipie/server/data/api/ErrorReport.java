/**
 * ErrorReport.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

/**
 * @author Seastar
 *
 */
public class ErrorReport{
	CommandStatus status;
	String errorMsg;
	/**
	 * Constructor
	 * @param status
	 * @param errorMsg
	 */
	public ErrorReport(CommandStatus status, String errorMsg) {
		this.status = status;
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @return the status
	 */
	public CommandStatus getStatus() {
		return status;
	}
	
	public enum CommandStatus{
		SUCCESS,INVALIDREQUEST,NOTFOUND,PERMISSIONDENIED,UNKOWNSERCERERROR
	}
}
