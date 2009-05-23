/**
 * EditHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Edit;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.Birthdate;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Relationships;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.PermissionDeniedException;
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
	protected EditHandler(Update dt,ContactStorage executor,long id) throws UpdateException {
		super(null, dt,executor,id);
	}
	
	public void handleUpdate() throws UpdateException, DataFormatException{
		if(dt instanceof Edit){
			Edit newData = (Edit) dt;
			try {
				switch (newData.getType()) {
				case ADDRESS:
					if(contactExecutor.findAddressOwner(newData.getId())!=contactId)
						throw new PermissionDeniedException("this is not your address info");
					AddressData address = new AddressData();
					switch (newData.getInfoField()) {
					case VALUE:
						address.setValue(newData.getValue());
						contactExecutor.editAddr(newData.getId(), InfoField.VALUE,
								address.getValue());
						break;
					case ZIPCODE:
						address.setZipcode(newData.getValue());
						contactExecutor.editAddr(newData.getId(), InfoField.ZIPCODE,
								address.getZipcode());
						break;
					case TYPE:	
						address.setType(newData.getValue());
						contactExecutor.editAddr(newData.getId(), InfoField.TYPE,
								address.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							address.setPrimary(false);
						else if (newData.getValue().equals("true"))
							address.setPrimary(true);
						else
							address.setPrimary(false);
						contactExecutor.editAddr(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(address.isPrimary()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in AddressData");
					}
					break;
				case EMAIL:
					if(contactExecutor.findEmailOwner(newData.getId())!=contactId)
						throw new PermissionDeniedException("this is not your email info");
					EmailData email = new EmailData();
					switch (newData.getInfoField()) {
					case VALUE:
						email.setValue(newData.getValue());
						contactExecutor.editEmail(newData.getId(), InfoField.VALUE,
								email.getValue());
						break;
					case TYPE:
						email.setType(newData.getValue());
						contactExecutor.editEmail(newData.getId(), InfoField.TYPE,
								email.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							email.setPrimary(false);
						else if (newData.getValue().equals("true"))
							email.setPrimary(true);
						else
							email.setPrimary(false);
						contactExecutor.editEmail(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(email.isPrimary()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in EmailData");
					}
					break;
				case IM:
					if(contactExecutor.findIMOwner(newData.getId())!=contactId)
						throw new PermissionDeniedException("this is not your im info");
					IMData im = new IMData();
					switch (newData.getInfoField()) {
					case VALUE:
						im.setValue(newData.getValue());
						contactExecutor.editIM(newData.getId(), InfoField.VALUE,
								im.getValue());
						break;
					case TYPE:
						im.setType(newData.getValue());
						contactExecutor.editIM(newData.getId(), InfoField.TYPE,
								im.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							im.setPrimary(false);
						else if (newData.getValue().equals("true"))
							im.setPrimary(true);
						else
							im.setPrimary(false);
						contactExecutor.editIM(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(im.isPrimary()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in IMData");
					}
					break;
				case PHONE:
					if(contactExecutor.findTelOwner(newData.getId())!=contactId)
						throw new PermissionDeniedException("this is not your phone info");
					PhoneNoData phone = new PhoneNoData();
					switch (newData.getInfoField()) {
					case VALUE:
						phone.setValue(newData.getValue());
						contactExecutor.editTel(newData.getId(), InfoField.VALUE,
								phone.getValue());
						break;
					case TYPE:
						phone.setType(newData.getValue());
						contactExecutor.editTel(newData.getId(), InfoField.TYPE,
								phone.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							phone.setPrimary(false);
						else if (newData.getValue().equals("true"))
							phone.setPrimary(true);
						else
							phone.setPrimary(false);
						contactExecutor.editTel(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(phone.isPrimary()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in PhoneNumberData");
					}
					break;
				case URL:
					if(contactExecutor.findURLOwner(newData.getId())!=contactId)
						throw new PermissionDeniedException("this is not your url info");
					URLData url = new URLData();
					switch (newData.getInfoField()) {
					case VALUE:
						url.setValue(newData.getValue());
						contactExecutor.editURL(newData.getId(), InfoField.VALUE,
								url.getValue());
						break;
					case TYPE:
						url.setType(newData.getValue());
						contactExecutor.editURL(newData.getId(), InfoField.TYPE,
								url.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							url.setPrimary(false);
						else if (newData.getValue().equals("true"))
							url.setPrimary(true);
						else
							url.setPrimary(false);
						contactExecutor.editURL(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(url.isPrimary()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in URLData");
					}
					break;
				case BASIC:
					ContactEntity contact = new ContactEntity();
					switch (newData.getInfoField()) {
					case NAME:
						contact.setName(newData.getValue());
						contactExecutor.editBasicInfo(contactId, InfoField.NAME,
								contact.getName());
						break;
					case NICKNAME:
						contact.setNickName(newData.getValue());
						contactExecutor.editBasicInfo(contactId, InfoField.NICKNAME,
								contact.getNickName());
						break;
					case BIRTHDAY:
						if (newData.getValue() != null)
							contact.setBirthday(new Birthdate(newData.getValue()));
						contactExecutor.editBasicInfo(contactId, InfoField.BIRTHDAY,
								contact.getBirthday().toString());
						break;
					case GENDER:
						if (newData.getValue() != null)
							contact.setGender(Gender.value(newData.getValue()));
						contactExecutor.editBasicInfo(contactId, InfoField.GENDER,
								contact.getGender());
						break;
					case NOTE:
						contact.setNotes(newData.getValue());
						contactExecutor.editBasicInfo(contactId, InfoField.NOTE,
								contact.getNotes());
						break;
					case RELATIONSHIP:
						contact.setRelationship(new Relationships(newData.getValue()));
						contactExecutor.editBasicInfo(contactId, InfoField.RELATIONSHIP,
								contact.getRelationship().toString());
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
				//e.printStackTrace();
				throw new UpdateException("Wrong Attribute at "+ dt.getType().toString()+
						" "+ newData.getInfoField().toString());
			}
		}
		else{
			super.handleUpdate();
		}
	}
}
