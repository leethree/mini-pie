/**
 * EditHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Edit;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 *
 */
public class EditHandler extends UpdateHandler{

	/**
	 * Constructor
	 * @param successor
	 * @param dt
	 * @throws UpdateException 
	 */
	protected EditHandler(Update dt,ContactStorage executor) throws UpdateException {
		super(null, dt,executor);
	}
	
	public void handleUpdate() throws UpdateException{
		if(dt instanceof Edit){
			Edit newData = (Edit) dt;
			try {
				switch (newData.getType()) {
				case ADDRESS:
					if(executor.findAddressOwner(newData.getId())!=contactId)
						throw new InvalidRequestException("this is not your address info");
					switch (newData.getInfoField()) {
					case VALUE:
					case ZIPCODE:
					case TYPE:	
						executor.editAddr(newData.getId(), newData.getInfoField(),
								newData.getValue());
						break;
					case PRIMARY:
						executor.editAddr(newData.getId(),  newData.getInfoField(),
							Boolean.valueOf(newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in AddressData");
					}
					break;
				case EMAIL:
					if(executor.findAddressOwner(newData.getId())!=contactId)
						throw new InvalidRequestException("this is not your email info");
					switch (newData.getInfoField()) {
					case VALUE:					
					case TYPE:
						executor.editEmail(newData.getId(), newData.getInfoField(),
								newData.getValue());
						break;
					case PRIMARY:
						executor.editEmail(newData.getId(),  newData.getInfoField(),
							Boolean.valueOf(newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in EmailData");
					}
					break;
				case IM:
					if(executor.findAddressOwner(newData.getId())!=contactId)
						throw new InvalidRequestException("this is not your im info");
					switch (newData.getInfoField()) {
					case VALUE:					
					case TYPE:
						executor.editIM(newData.getId(), newData.getInfoField(),
								newData.getValue());
						break;
					case PRIMARY:
						executor.editIM(newData.getId(),  newData.getInfoField(),
							Boolean.valueOf(newData.getValue()));
						break;	
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in IMData");
					}
					break;
				case PHONE:
					if(executor.findAddressOwner(newData.getId())!=contactId)
						throw new InvalidRequestException("this is not your phone info");
					switch (newData.getInfoField()) {
					case VALUE:
					case TYPE:
						executor.editTel(newData.getId(), newData.getInfoField(),
								newData.getValue());
						break;
					case PRIMARY:
						executor.editTel(newData.getId(),  newData.getInfoField(),
							Boolean.valueOf(newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in PhoneNumberData");
					}
					break;
				case URL:
					if(executor.findAddressOwner(newData.getId())!=contactId)
						throw new InvalidRequestException("this is not your url info");
					switch (newData.getInfoField()) {
					case VALUE:
					case TYPE:
						executor.editURL(newData.getId(), newData.getInfoField(),
								newData.getValue());
						break;
					case PRIMARY:
						executor.editURL(newData.getId(),  newData.getInfoField(),
							Boolean.valueOf(newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in URLData");
					}
					break;
				case BASIC:
					switch (newData.getInfoField()) {
					case NAME:
					case NICKNAME:
					case BIRTHDAY:
					case GENDER:
					case NOTE:
					case RELATIONSHIP:
						executor.editBasicInfo(newData.getId(), newData.getInfoField(),
								newData.getValue());
						break;
										
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in ContactInfo");
					}
					break;
				default:
					throw new UpdateException("Unsupported InfoType: "+ dt.getType().toString());
				}
			} catch (ClassCastException e) {
				throw new UpdateException("Wrong Attribute at "+ dt.getType().toString()+
						" "+ newData.getInfoField().toString());
			}
		}
		else{
			super.handleUpdate();
		}
	}
}
