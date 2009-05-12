/**
 * MacroCommand.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;
import java.util.Vector;

import org.net9.minipie.server.data.api.ErrorReports;
import org.net9.minipie.server.data.api.ErrorReport;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.exception.UnknownServerException;

/**
 * @author Seastar
 *
 */
public class MacroCommand extends Command<ErrorReports> {
	private Collection<Command<Void>> commands;
	private ErrorReports errorReports;
	
	public MacroCommand(){
		commands=new Vector<Command<Void>>();
		errorReports=new ErrorReports();
	}
	
	public MacroCommand(Collection<Command<Void>> commands){
		this.commands=commands;
		errorReports=new ErrorReports();
	}
	
	public void addCommand(Command<Void> command){
		commands.add(command);
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public ErrorReports execute() {
		for (Command<Void> command : commands) {
			try {
				command.execute();
				errorReports.addStatus(new ErrorReport
						(ErrorReport.CommandStatus.SUCCESS,null));
			} catch (InvalidRequestException e) {
				errorReports.addStatus(new ErrorReport
						(ErrorReport.CommandStatus.INVALIDREQUEST,e.getMessage()));				
			} catch (NotFoundException e){
				errorReports.addStatus(new ErrorReport
						(ErrorReport.CommandStatus.NOTFOUND,e.getMessage()));
			} catch (UnknownServerException e){
				errorReports.addStatus(new ErrorReport
						(ErrorReport.CommandStatus.UNKOWNSERCERERROR,e.getMessage()));
			} catch (PermissionDeniedException e){
				errorReports.addStatus(new ErrorReport
						(ErrorReport.CommandStatus.PERMISSIONDENIED,e.getMessage()));
			}
		}
		return errorReports;
	}

}
