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
import org.net9.minipie.server.exception.DataFormatException;
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
	protected AddHandler(Update dt,ContactStorage executor,long id) throws UpdateException {
		super(new DelHandler(dt,executor,id), dt,executor,id);
		//this.id=id;
	}
	
	public void handleUpdate() throws UpdateException, DataFormatException{
		if (dt instanceof Add){
			Add newData=(Add)dt;
			if(newData.getInfo()==null) throw new UpdateException("No Entry to add");
			try {
				switch (newData.getType()) {
				case ADDRESS:
					AddressData addr = (AddressData) newData.getInfo();
					contactExecutor.addAddr(contactId, addr);
					break;
				case EMAIL:
					EmailData email = (EmailData) newData.getInfo();
					contactExecutor.addEmail(contactId, email);
					break;
				case IM:
					IMData im = (IMData) newData.getInfo();
					contactExecutor.addIM(contactId, im);
					break;
				case PHONE:
					PhoneNoData tel = (PhoneNoData) newData.getInfo();
					contactExecutor.addTel(contactId, tel);
					break;
				case URL:
					URLData url = (URLData) newData.getInfo();
					contactExecutor.addURL(contactId, url);
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