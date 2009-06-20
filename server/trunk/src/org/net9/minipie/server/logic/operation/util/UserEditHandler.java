/**
 * UserEditHandler.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import org.net9.minipie.server.data.api.Edit;
import org.net9.minipie.server.data.api.Update;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Birthdate;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.exception.UpdateException;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class UserEditHandler extends UpdateHandler {
	private long userId;
	/**
	 * Constructor
	 * @param dt
	 * @param executor
	 * @param id
	 * @throws UpdateException
	 */
	protected UserEditHandler(Update dt, UserStorage executor, long id)
			throws UpdateException {
		super(new UserDelHandler(dt,executor,id),dt, executor);
		this.userId=id;
	}

	public void handleUpdate() throws UpdateException, DataFormatException{
		if(dt instanceof Edit){
			Edit newData = (Edit) dt;
			try {
				switch (newData.getType()) {
				case ADDRESS:
					if(userExecutor.findAddressOwner(newData.getId())!=userId)
						throw new PermissionDeniedException("this is not your address info");
					AddressData address = new AddressData();
					switch (newData.getInfoField()) {
					case VALUE:
						address.setValue(newData.getValue());
						userExecutor.editAddr(newData.getId(), InfoField.VALUE,
								address.getValue());
						break;
					case ZIPCODE:
						address.setZipcode(newData.getValue());
						userExecutor.editAddr(newData.getId(), InfoField.ZIPCODE,
								address.getZipcode());
						break;
					case TYPE:	
						address.setType(newData.getValue());
						userExecutor.editAddr(newData.getId(), InfoField.TYPE,
								address.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							address.setPrimary(false);
						else if (newData.getValue().equals("true"))
							address.setPrimary(true);
						else
							address.setPrimary(false);
						userExecutor.editAddr(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(address.isPrimary()));
						break;
					case PERMISSION:
						userExecutor.editAddr(newData.getId(), InfoField.PERMISSION
								, Permission.value( newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in AddressData");
					}
					break;
				case EMAIL:
					if(userExecutor.findEmailOwner(newData.getId())!=userId)
						throw new PermissionDeniedException("this is not your email info");
					EmailData email = new EmailData();
					switch (newData.getInfoField()) {
					case VALUE:
						email.setValue(newData.getValue());
						userExecutor.editEmail(newData.getId(), InfoField.VALUE,
								email.getValue());
						break;
					case TYPE:
						email.setType(newData.getValue());
						userExecutor.editEmail(newData.getId(), InfoField.TYPE,
								email.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							email.setPrimary(false);
						else if (newData.getValue().equals("true"))
							email.setPrimary(true);
						else
							email.setPrimary(false);
						userExecutor.editEmail(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(email.isPrimary()));
						break;
					case PERMISSION:
						userExecutor.editEmail(newData.getId(), InfoField.PERMISSION
								, Permission.value( newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in EmailData");
					}
					break;
				case IM:
					if(userExecutor.findIMOwner(newData.getId())!=userId)
						throw new PermissionDeniedException("this is not your im info");
					IMData im = new IMData();
					switch (newData.getInfoField()) {
					case VALUE:
						im.setValue(newData.getValue());
						userExecutor.editIM(newData.getId(), InfoField.VALUE,
								im.getValue());
						break;
					case TYPE:
						im.setType(newData.getValue());
						userExecutor.editIM(newData.getId(), InfoField.TYPE,
								im.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							im.setPrimary(false);
						else if (newData.getValue().equals("true"))
							im.setPrimary(true);
						else
							im.setPrimary(false);
						userExecutor.editIM(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(im.isPrimary()));
						break;
					case PERMISSION:
						userExecutor.editIM(newData.getId(), InfoField.PERMISSION
								, Permission.value( newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in IMData");
					}
					break;
				case PHONE:
					if(userExecutor.findTelOwner(newData.getId())!=userId)
						throw new PermissionDeniedException("this is not your phone info");
					PhoneNoData phone = new PhoneNoData();
					switch (newData.getInfoField()) {
					case VALUE:
						phone.setValue(newData.getValue());
						userExecutor.editTel(newData.getId(), InfoField.VALUE,
								phone.getValue());
						break;
					case TYPE:
						phone.setType(newData.getValue());
						userExecutor.editTel(newData.getId(), InfoField.TYPE,
								phone.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							phone.setPrimary(false);
						else if (newData.getValue().equals("true"))
							phone.setPrimary(true);
						else
							phone.setPrimary(false);
						userExecutor.editTel(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(phone.isPrimary()));
						break;
					case PERMISSION:
						userExecutor.editTel(newData.getId(), InfoField.PERMISSION
								, Permission.value( newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in PhoneNumberData");
					}
					break;
				case URL:
					if(userExecutor.findURLOwner(newData.getId())!=userId)
						throw new PermissionDeniedException("this is not your url info");
					URLData url = new URLData();
					switch (newData.getInfoField()) {
					case VALUE:
						url.setValue(newData.getValue());
						userExecutor.editURL(newData.getId(), InfoField.VALUE,
								url.getValue());
						break;
					case TYPE:
						url.setType(newData.getValue());
						userExecutor.editURL(newData.getId(), InfoField.TYPE,
								url.getType());
						break;
					case PRIMARY:
						if (newData.getValue() == null)
							url.setPrimary(false);
						else if (newData.getValue().equals("true"))
							url.setPrimary(true);
						else
							url.setPrimary(false);
						userExecutor.editURL(newData.getId(), InfoField.PRIMARY,
							Boolean.valueOf(url.isPrimary()));
						break;
					case PERMISSION:
						userExecutor.editURL(newData.getId(), InfoField.PERMISSION
								, Permission.value( newData.getValue()));
						break;
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in URLData");
					}
					break;
				case BASIC:
					UserEntity user = new UserEntity();
					switch (newData.getInfoField()) {
					case NAME:
						user.setName(newData.getValue());
						userExecutor.editBasicInfo(userId, InfoField.NAME,
								user.getName());
						break;
					case NICKNAME:
						user.setNickName(newData.getValue());
						userExecutor.editBasicInfo(userId, InfoField.NICKNAME,
								user.getNickName());
						break;
					case DISPLAYNAME:
						user.setDisplayname(newData.getValue());
						userExecutor.editBasicInfo(userId, InfoField.DISPLAYNAME,
								user.getDisplayname());
						break;
					case REGISTEREMAIL:
						user.setRegisteredEmail(newData.getValue());
						userExecutor.editBasicInfo(userId, InfoField.REGISTEREMAIL, 
								user.getRegisteredEmail());
						break;
//					case PASSWORD:
//						user.setPassword(newData.getValue());
//						userExecutor.editBasicInfo(userId, InfoField.PASSWORD, 
//								user.getPassword());
//						break;
					case BIRTHDAY:
						if (newData.getValue() != null)
							user.setBirthday(new Birthdate(newData.getValue()));
						userExecutor.editBasicInfo(userId, InfoField.BIRTHDAY,
								user.getBirthday().toString());
						break;
					case BIRTHDAYPERMISSION:
						if(newData.getValue()!=null)
						userExecutor.editBasicInfo(userId, InfoField.BIRTHDAYPERMISSION
								, Permission.value( newData.getValue()));
						break;
					case BIRTHYEARPERMISSION:
						if(newData.getValue()!=null)
						userExecutor.editBasicInfo(userId, InfoField.BIRTHYEARPERMISSION
								, Permission.value( newData.getValue()));
						break;
					case GENDER:
						if (newData.getValue() != null)
							user.setGender(Gender.value(newData.getValue()));
						userExecutor.editBasicInfo(userId, InfoField.GENDER,
								user.getGender());
						break;
					case GENDERPERMISSION:
						if(newData.getValue()!=null)
						userExecutor.editBasicInfo(userId, InfoField.GENDERPERMISSION
								, Permission.value( newData.getValue()));
						break;
					case NOTE:
						user.setNotes(newData.getValue());
						userExecutor.editBasicInfo(userId, InfoField.NOTE,
								user.getNotes());
						break;
					
					default:
						throw new UpdateException("No Field "+newData.getInfoField().toString()
								+" in UserInfo");
					}
					break;
				default:
					throw new UpdateException("Unsupported InfoType: "+ dt.getType().toString());
				}
			} catch (ClassCastException e) {
				e.printStackTrace();
				throw new UpdateException("Wrong Attribute at "+ dt.getType().toString()+
						" "+ newData.getInfoField().toString());
			}
		}
		else{
			super.handleUpdate();
		}
	}
}
