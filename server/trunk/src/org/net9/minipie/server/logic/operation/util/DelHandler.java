/**
 * DelHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.exception.InvalidRequestException;
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
					if(executor.findAddressOwner(newData.getId())==contactId)			
						executor.delAddr(newData.getId());
					else
						throw new InvalidRequestException("this is not your address info");
					break;
				case EMAIL:
					if(executor.findAddressOwner(newData.getId())==contactId)			
						executor.delEmail(newData.getId());
					else
						throw new InvalidRequestException("this is not your email info");
					break;
				case IM:
					if(executor.findAddressOwner(newData.getId())==contactId)			
						executor.delIM(newData.getId());
					else
						throw new InvalidRequestException("this is not your im info");
					break;
				case PHONE:
					if(executor.findAddressOwner(newData.getId())==contactId)			
						executor.delTel(newData.getId());
					else
						throw new InvalidRequestException("this is not your phone info");
					break;
				case URL:
					if(executor.findAddressOwner(newData.getId())==contactId)			
						executor.delURL(newData.getId());
					else
						throw new InvalidRequestException("this is not your url info");
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
