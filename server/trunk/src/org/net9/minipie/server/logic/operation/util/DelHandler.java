/**
 * DelHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 *
 */
public class DelHandler extends UpdateHandler{

	/**
	 * Constructor
	 * @throws UpdateException 
	 */
	protected DelHandler(Update dt,ContactStorage executor) throws UpdateException {
		super(new EditHandler(dt,executor), dt,executor);
	}
	
	public void handleUpdate() throws UpdateException{
		if(dt instanceof Delete){
			Delete newData = (Delete) dt;
			
			switch (newData.getType()) {
				case ADDRESS:
					//if(executor.selectAddr(newData.getId()))
					executor.delAddr(newData.getId());
					break;
				case EMAIL:
					executor.delEmail(newData.getId());
					break;
				case IM:
					executor.delIM(newData.getId());
					break;
				case PHONE:
					executor.delTel(newData.getId());
					break;
				case URL:
					executor.delURL(newData.getId());
					break;
				default:
					throw new UpdateException("Can't delete "+ newData.getType().toString());
			}
		}
		else{
			super.handleUpdate();
		}
	}
}
