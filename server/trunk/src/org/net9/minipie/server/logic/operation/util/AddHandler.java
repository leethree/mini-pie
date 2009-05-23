/**
 * AddHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.tools
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
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 *
 */
public class AddHandler extends UpdateHandler{
	//private long id;
	/**
	 * Constructor
	 * @param successor
	 * @param dt
	 * @throws UpdateException 
	 */
	protected AddHandler(Update dt,ContactStorage executor) throws UpdateException {
		super(new DelHandler(dt,executor), dt,executor);
		//this.id=id;
	}
	
	public void handleUpdate() throws UpdateException{
		if (dt instanceof Add){
			Add newData=(Add)dt;
			if(newData.getInfo()==null) throw new UpdateException("No Entry to add");
			try {
				switch (newData.getType()) {
				case ADDRESS:
					AddressData addr = (AddressData) newData.getInfo();
					executor.addAddr(contactId, addr);
					break;
				case EMAIL:
					EmailData email = (EmailData) newData.getInfo();
					executor.addEmail(contactId, email);
					break;
				case IM:
					IMData im = (IMData) newData.getInfo();
					executor.addIM(contactId, im);
					break;
				case PHONE:
					PhoneNoData tel = (PhoneNoData) newData.getInfo();
					executor.addTel(contactId, tel);
					break;
				case URL:
					URLData url = (URLData) newData.getInfo();
					executor.addURL(contactId, url);
					break;
				default:
					throw new UpdateException("Can't add "+ newData.getType().toString());
				}
			} 
			catch (ClassCastException e) {
				throw new UpdateException("The info provided is not suitable:"
						+ newData.getType().toString()+"wanted");
			}
		}
		else{
			super.handleUpdate();
		}
	}
		
}