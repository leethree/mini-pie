/**
 * Command.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

/**
 * @author Seastar
 *
 */
public interface Command<T> {
//	protected T returnValue;
//	public T getReturn(){
//		return returnValue;
//	}
	public T excute();
}
