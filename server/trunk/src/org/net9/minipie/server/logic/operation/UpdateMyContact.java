/**
 * UpdateContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;

import org.net9.minipie.server.data2.api.Add;
import org.net9.minipie.server.data2.api.Delete;
import org.net9.minipie.server.data2.api.Edit;
import org.net9.minipie.server.data2.api.Update;
import org.net9.minipie.server.data2.entity.AddressData;
import org.net9.minipie.server.data2.entity.EmailData;
import org.net9.minipie.server.data2.entity.IMData;
import org.net9.minipie.server.data2.entity.PhoneNoData;
import org.net9.minipie.server.data2.entity.URLData;
import org.net9.minipie.server.data2.entity.ContactEntity;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class UpdateMyContact extends Command<Void> {
	private Collection<Update> datas;
	private Long contactId;
	private Long userId;

	/**
	 * Constructor
	 * 
	 * @param contactid
	 * @param userId
	 */
	public UpdateMyContact(Long contactId, Long userId, Collection<Update> data) {
		super();
		setData(data);
		setContactId(contactId);
		setUserId(userId);
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setData(Collection<Update> data) {
		if (data == null) {
			throw new InvalidRequestException("No changed data");
		}
		this.datas = data;
	}

	/**
	 * @param contactid
	 *            the contactId to set
	 */
	public void setContactId(Long contactId) {
		if (contactId < 0) {
			throw new InvalidRequestException("id is illegal");
		}
		this.contactId = contactId;
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
		ContactStorage executor = getStorageFactory().getContactStorage();
		ContactEntity contact = executor.selectBasicInfo(contactId).getEntity();
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		} else {
			for (Update data : datas) {
				if (data instanceof Add) {
					Add newData = (Add) data;
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
							// TODO:what should I do?
						}
					} catch (ClassCastException e) {
						// TODO:what should I do?
						// throw new InvalidRequestException("trans error");
					}
				} else if (data instanceof Edit) {
					Edit newData = (Edit) data;
					try {
						switch (newData.getType()) {
						case ADDRESS:
							switch (newData.getField()) {
							case VALUE:
								executor.editAddr(newData.getId(), "value",
										newData.getValue());
								break;
							case PRIMARY:
								executor.editAddr(newData.getId(), "primary",
										Boolean.valueOf(newData.getValue()));
								break;
							case ZIPCODE:
								executor.editAddr(newData.getId(), "zipcode",
										newData.getValue());
								break;
							case TYPE:
								executor.editAddr(newData.getId(), "type",
										newData.getValue());
								break;

							default:
								// TODO:what should I do?
							}
							break;
						case EMAIL:
							switch (newData.getField()) {
							case VALUE:
								executor.editEmail(newData.getId(), "value",
										newData.getValue());
								break;
							case PRIMARY:
								executor.editEmail(newData.getId(), "primary",
										Boolean.valueOf(newData.getValue()));
								break;
							case TYPE:
								executor.editEmail(newData.getId(), "type",
										newData.getValue());
								break;

							default:
								// TODO:what should I do?
							}
							break;
						case IM:
							switch (newData.getField()) {
							case VALUE:
								executor.editIM(newData.getId(), "value",
										newData.getValue());
								break;
							case PRIMARY:
								executor.editIM(newData.getId(), "primary",
										Boolean.valueOf(newData.getValue()));
								break;
							case TYPE:
								executor.editIM(newData.getId(), "type",
										newData.getValue());
								break;

							default:
								// TODO:what should I do?
							}
							break;
						case PHONE:
							switch (newData.getField()) {
							case VALUE:
								executor.editTel(newData.getId(), "value",
										newData.getValue());
								break;
							case PRIMARY:
								executor.editTel(newData.getId(), "primary",
										Boolean.valueOf(newData.getValue()));
								break;
							case TYPE:
								executor.editTel(newData.getId(), "type",
										newData.getValue());
								break;

							default:
								// TODO:what should I do?
							}
							break;
						case URL:
							switch (newData.getField()) {
							case VALUE:
								executor.editURL(newData.getId(), "value",
										newData.getValue());
								break;
							case PRIMARY:
								executor.editURL(newData.getId(), "primary",
										Boolean.valueOf(newData.getValue()));
								break;
							case TYPE:
								executor.editURL(newData.getId(), "type",
										newData.getValue());
								break;

							default:
								// TODO:what should I do?
							}
							break;
						case BASIC:
							switch (newData.getField()) {
							case NAME:
								executor.editBasicInfo(newData.getId(), "name",
										newData.getValue());
								break;
							case NICKNAME:
								executor.editBasicInfo(newData.getId(),
										"nickName", newData.getValue());
								break;
							case BIRTHDAY:
								executor.editBasicInfo(newData.getId(),
										"birthday", newData.getValue());
								break;
							case GENDER:
								executor.editBasicInfo(newData.getId(),
										"birthday", newData.getValue());
								break;
							case NOTE:
								executor.editBasicInfo(newData.getId(),
										"notes", newData.getValue());
								break;
							case RELATIONSHIP:
								executor.editBasicInfo(newData.getId(),
										"relationship", newData.getValue());
								break;
							default:
								// TODO:what should I do?
							}
							break;
						default:
							// TODO:what should I do?
						}
					} catch (ClassCastException e) {
						// TODO:what should I do?
						// throw new InvalidRequestException("trans error");
					}
				} else if (data instanceof Delete) {
					Delete newData = (Delete) data;
					switch (newData.getType()) {
					case ADDRESS:
						executor.delAddr(newData.getId());
						break;
					case EMAIL:
						executor.delEmail(newData.getId());
						break;
					case IM:
						executor.delIM(newData.getId());
						break;
					case PHONE:
						executor.delTel(newData.getId());
						break;
					case URL:
						executor.delURL(newData.getId());
						break;
					default:
						// TODO:what should I do?
					}
				} else {
					// TODO:what should I do?
				}
			}
		}
		return null;
	}
}
