/**
 * UpdateHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.tools
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 *
 */
public class UpdateHandler{
	protected UpdateHandler successor;
	protected Update dt;
	protected ContactStorage executor;
	/**
	 * Constructor
	 * @param successor
	 * @param dt
	 * @throws UpdateException 
	 */
	protected UpdateHandler(UpdateHandler successor, Update dt,ContactStorage executor) throws UpdateException {
		super();
		this.successor = successor;
		this.executor=executor;

		if(dt.getType()==null)
			throw new UpdateException("Unknow Updata Type");
		this.dt = dt;
		
	}
	public UpdateHandler(Update dt,ContactStorage executor,long id) throws UpdateException{
		super();
		this.successor=new AddHandler(dt,id,executor);
	}
	
	public void handleUpdate() throws UpdateException{
		if(successor!=null)
			successor.handleUpdate();
		else
			throw new UpdateException("Unsupported Operation");
		return;
	}
}
