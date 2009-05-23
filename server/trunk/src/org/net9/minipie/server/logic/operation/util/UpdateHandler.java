/**
 * UpdateHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.tools
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.ContactStorage;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class UpdateHandler{
	protected UpdateHandler successor;
	protected Update dt;
	protected ContactStorage contactExecutor;
	protected UserStorage userExecutor;
	protected Long contactId;
	//protected long userId;
	/**
	 * Constructor
	 * @param successor
	 * @param dt
	 * @throws UpdateException 
	 */
	protected UpdateHandler(UpdateHandler successor, Update dt,ContactStorage executor,long contactId) throws UpdateException {
		super();
		this.successor = successor;
		this.contactExecutor=executor;
		this.contactId=contactId;
		if(dt.getType()==null)
			throw new UpdateException("Unknow Updata Type");
		this.dt = dt;
		
	}
	protected UpdateHandler(UpdateHandler successor, Update dt,UserStorage executor) throws UpdateException {
		super();
		this.successor = successor;
		this.userExecutor=executor;
		if(dt.getType()==null)
			throw new UpdateException("Unknow Updata Type");
		this.dt = dt;		
	}
	public UpdateHandler(Update dt,ContactStorage executor,long id) throws UpdateException{
		super();
		this.contactId=id;
		this.successor=new AddHandler(dt,executor,contactId);
	}
	
	public UpdateHandler(Update dt,UserStorage executor,long id) throws UpdateException{
		super();
		//this.userId=id;
		this.successor=new UserAddHandler(dt,executor,id);
	} 
	public void handleUpdate() throws UpdateException, DataFormatException{
		if(successor!=null)
			successor.handleUpdate();
		else
			throw new UpdateException("Unsupported Operation");
		return;
	}
}
