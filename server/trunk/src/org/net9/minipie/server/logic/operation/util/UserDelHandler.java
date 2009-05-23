/**
 * UserDelHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class UserDelHandler extends UpdateHandler{
	private long userId;
	/**
	 * Constructor
	 * @param dt
	 * @param executor
	 * @param id
	 * @throws UpdateException
	 */
	public UserDelHandler(Update dt, UserStorage executor, long id)
			throws UpdateException {
		super(null,dt, executor);
		this.userId=id;
	}

	public void handleUpdate() throws UpdateException, DataFormatException{
		if(dt instanceof Delete){
			Delete newData = (Delete) dt;
			
			switch (newData.getType()) {
				case ADDRESS:
					if(userExecutor.findAddressOwner(newData.getId())==userId)			
						userExecutor.delAddr(newData.getId());
					else
						throw new PermissionDeniedException("this is not your address info");
					break;
				case EMAIL:
					if(userExecutor.findEmailOwner(newData.getId())==userId)			
						userExecutor.delEmail(newData.getId());
					else
						throw new PermissionDeniedException("this is not your email info");
					break;
				case IM:
					if(userExecutor.findIMOwner(newData.getId())==userId)			
						userExecutor.delIM(newData.getId());
					else
						throw new PermissionDeniedException("this is not your im info");
					break;
				case PHONE:
					if(userExecutor.findTelOwner(newData.getId())==userId)			
						userExecutor.delTel(newData.getId());
					else
						throw new PermissionDeniedException("this is not your phone info");
					break;
				case URL:
					if(userExecutor.findURLOwner(newData.getId())==userId)			
						userExecutor.delURL(newData.getId());
					else
						throw new PermissionDeniedException("this is not your url info");
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
