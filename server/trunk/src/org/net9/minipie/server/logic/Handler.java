/**
 * Handler.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.logic.operation.Command;

/**
 * @author Seastar
 *
 */
public class Handler<T> {
	private Command<T> aCommand;
	/**
	 * Constructor
	 */
	public Handler() {
		// TODO Auto-generated constructor stub
	}
	
	public Handler(Command<T> com){
		setCommand(com);
	}
	
	/**
	 * @param command the aCommand to set
	 */
	public void setCommand(Command<T> command) {
		aCommand = command;
	}
	public T excute(){
		aCommand.excute();
		return aCommand.getReturn();
	}
}

