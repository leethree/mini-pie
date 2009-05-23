/**
 * UpdateMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.operation.util.UpdateHandler;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class UpdateMyInfo extends Command<Void> {
	private Update datas;	
	private Long userId;

	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public UpdateMyInfo(Long userId, Update data) {
		super();
		setData(data);
		
		setUserId(userId);
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setData(Update data) {
		if (data == null) {
			throw new InvalidRequestException("No changed data");
		}
		this.datas = data;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#excute()
	 */
	public Void execute() {
		UserStorage executor = getStorageFactory().getUserStorage();

			try {
				new UpdateHandler(datas,executor,userId).handleUpdate();
			} catch (UpdateException e) {
				throw new InvalidRequestException(e.getMessage());
			} catch (DataFormatException e) {
				throw new InvalidRequestException(e);
			}							
		
		return null;
	}
}
