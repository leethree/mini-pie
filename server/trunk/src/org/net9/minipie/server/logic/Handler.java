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
	private Command<T> command;
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
		this.command = command;
	}
	public T excute(){
		return command.excute();
	}
}

