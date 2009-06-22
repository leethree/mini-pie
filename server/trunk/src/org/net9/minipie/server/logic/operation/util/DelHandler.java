/**
 * DelHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Delete;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class DelHandler extends UpdateHandler {

	/**
	 * Constructor
	 * 
	 * @throws UpdateException
	 */
	protected DelHandler(Update dt, ContactStorage executor, long id)
			throws UpdateException {
		super(new EditHandler(dt, executor, id), dt, executor, id);
	}

	public void handleUpdate() throws UpdateException, DataFormatException {
		if (dt instanceof Delete) {
			Delete newData = (Delete) dt;
			try {
				switch (newData.getType()) {
				case ADDRESS:
					if (contactExecutor.findAddressOwner(newData.getId()) == contactId)
						contactExecutor.delAddr(newData.getId());
					else
						throw new PermissionDeniedException(
								"this is not your address info");
					break;
				case EMAIL:
					if (contactExecutor.findEmailOwner(newData.getId()) == contactId)
						contactExecutor.delEmail(newData.getId());
					else
						throw new PermissionDeniedException(
								"this is not your email info");
					break;
				case IM:
					if (contactExecutor.findIMOwner(newData.getId()) == contactId)
						contactExecutor.delIM(newData.getId());
					else
						throw new PermissionDeniedException(
								"this is not your im info");
					break;
				case PHONE:
					if (contactExecutor.findTelOwner(newData.getId()) == contactId)
						contactExecutor.delTel(newData.getId());
					else
						throw new PermissionDeniedException(
								"this is not your phone info");
					break;
				case URL:
					if (contactExecutor.findURLOwner(newData.getId()) == contactId)
						contactExecutor.delURL(newData.getId());
					else
						throw new PermissionDeniedException(
								"this is not your url info");
					break;
				default:
					throw new UpdateException("Can't delete "
							+ newData.getType().toString());
				}
			} catch (NotFoundException e) {
				throw new InvalidRequestException("no such "
						+ newData.getType().toString() + " with the id "
						+ newData.getId());
			}
		} else {
			super.handleUpdate();
		}
	}
}
