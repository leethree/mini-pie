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
public abstract class Command<T> {
	protected T returnValue;
	public T getReturn(){
		return returnValue;
	}
	public abstract void excute();
}
