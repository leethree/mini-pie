/**
 * MacroCommand.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.StatusReportList;
import org.net9.minipie.server.data.api.StatusReport;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.exception.ServerErrorException;

/**
 * @author Seastar
 * 
 */
public class MacroCommand extends Command<StatusReportList> {
	private Collection<Command<Void>> commands;
	private StatusReportList errorReports;

	public MacroCommand() {
		commands = new Vector<Command<Void>>();
		errorReports = new StatusReportList();
	}

	public MacroCommand(Collection<Command<Void>> commands) {
		this.commands = commands;
		errorReports = new StatusReportList();
	}

	public void addCommand(Command<Void> command) {
		commands.add(command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public StatusReportList execute() {
		for (Command<Void> command : commands) {
			try {
				command.execute();
				errorReports.addStatus(new StatusReport(
						StatusReport.CommandStatus.SUCCESS, null));
			} catch (InvalidRequestException e) {
				errorReports.addStatus(new StatusReport(
						StatusReport.CommandStatus.INVALIDREQUEST, e
								.getMessage()));
			} catch (NotFoundException e) {
				errorReports.addStatus(new StatusReport(
						StatusReport.CommandStatus.NOTFOUND, e.getMessage()));
			} catch (ServerErrorException e) {
				errorReports.addStatus(new StatusReport(
						StatusReport.CommandStatus.UNKOWNSERCERERROR, e
								.getMessage()));
			} catch (PermissionDeniedException e) {
				errorReports.addStatus(new StatusReport(
						StatusReport.CommandStatus.PERMISSIONDENIED, e
								.getMessage()));
			}
		}
		return errorReports;
	}

}
