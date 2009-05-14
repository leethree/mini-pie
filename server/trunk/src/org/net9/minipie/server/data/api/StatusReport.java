/**
 * StatusReport.java
 *     in package: * org.net9.minipie.server.data.api
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
@XmlType(name = "response")
public class StatusReport {
	CommandStatus status;
	String message;

	/**
	 * Constructor
	 */
	public StatusReport() {
		status = CommandStatus.SUCCESS;
		message = null;
	}

	/**
	 * Constructor
	 * 
	 * @param status
	 * @param errorMsg
	 */
	public StatusReport(CommandStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the errorMsg
	 */
	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}

	/**
	 * @return the status
	 */
	@XmlElement(name = "status")
	public int getStatus() {
		switch (status) {
		case SUCCESS:
			return Status.OK.getStatusCode();
		case INVALIDREQUEST:
			return Status.BAD_REQUEST.getStatusCode();
		case NOTFOUND:
			return Status.NOT_FOUND.getStatusCode();
		case PERMISSIONDENIED:
			return Status.FORBIDDEN.getStatusCode();
		case UNKOWNSERCERERROR:
			return Status.INTERNAL_SERVER_ERROR.getStatusCode();
		default:
			throw new ServerErrorException("Unexpected Command Status.");
		}
	}

	public enum CommandStatus {
		SUCCESS, INVALIDREQUEST, NOTFOUND, PERMISSIONDENIED, UNKOWNSERCERERROR
	}
}
