/**
 * Handler.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.exception.UnknownServerException;
import org.net9.minipie.server.logic.operation.Command;

/**
 * @author Seastar
 * 
 */
public class Handler<R> {
	private Command<R> command;

	/**
	 * Constructor
	 */
	public Handler(Command<R> com) {
		setCommand(com);
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(Command<R> command) {
		this.command = command;
	}

	public R excute() {
		if(command==null){
			throw new UnknownServerException("Server error");
		}
		return command.execute();
	}
}
