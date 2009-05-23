/**
 * UserAddHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Add;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class UserAddHandler extends UpdateHandler {
	private long userId;
	/**
	 * Constructor
	 * @param dt
	 * @param executor
	 * @param id
	 * @throws UpdateException
	 */
	protected UserAddHandler(Update dt, UserStorage executor, long id)
			throws UpdateException {
		super(new UserEditHandler(dt,executor,id),dt, executor);
		this.userId=id;
		
	}
	
	public void handleUpdate() throws UpdateException, DataFormatException{
		if (dt instanceof Add){
			Add newData=(Add)dt;
			if(newData.getInfo()==null) throw new UpdateException("No Entry to add");
			try {
				switch (newData.getType()) {
				case ADDRESS:
					AddressData addr = (AddressData) newData.getInfo();
					userExecutor.addAddr(userId, addr);
					break;
				case EMAIL:
					EmailData email = (EmailData) newData.getInfo();
					userExecutor.addEmail(userId, email);
					break;
				case IM:
					IMData im = (IMData) newData.getInfo();
					userExecutor.addIM(userId, im);
					break;
				case PHONE:
					PhoneNoData tel = (PhoneNoData) newData.getInfo();
					userExecutor.addTel(userId, tel);
					break;
				case URL:
					URLData url = (URLData) newData.getInfo();
					userExecutor.addURL(userId, url);
					break;
				default:
					throw new UpdateException("Can't add "+ newData.getType().toString());
				}
			} 
			catch (ClassCastException e) {
				throw new UpdateException("Unexpected detailed info type : \""
						+ newData.getType().toString()+"\" expected.");
			}
		}
		else{
			super.handleUpdate();
		}
	}
		
}
