/**
 * UpdateContact.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.Collection;

import org.net9.minipie.server.data.Add;
import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.BasicContact;
import org.net9.minipie.server.data.Delete;
import org.net9.minipie.server.data.Edit;
import org.net9.minipie.server.data.EmailData;
import org.net9.minipie.server.data.IMData;
import org.net9.minipie.server.data.PhoneNoData;
import org.net9.minipie.server.data.URLData;
import org.net9.minipie.server.data.Update;
import org.net9.minipie.server.db.HibernateDAOFactory;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class UpdateMyContact implements Command<Void> {
	private Collection<Update> datas;
	private Long contactId;
	private Long userId;
	
	/**
	 * Constructor
	 * @param contactid
	 * @param userId
	 */
	public UpdateMyContact(Long contactId, Long userId,Collection<Update> data) {
		super();
		setData(data);
		setContactId(contactId);
		setUserId(userId);
	}

	/**
	 * @param date the date to set
	 */
	public void setData(Collection<Update> data) {
		if (data==null){
			throw new InvalidRequestException("No changed data");
		}
		this.datas = data;
	}
	/**
	 * @param contactid the contactId to set
	 */
	public void setContactId(Long contactId) {
		if(contactId<0){
			throw new InvalidRequestException("id is illegal");
		}
		this.contactId = contactId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#excute()
	 */
	public Void excute() {
		ContactStorage executor=new HibernateDAOFactory().getContactStorage();
		BasicContact contact=executor.selectBasicInfo(contactId);
		if(contact.getOwnerId()!=userId){
			throw new PermissionDeniedException("This contact doesn't belong to the user");
		}
		else if(contact.getShadowOf()==0){
			throw new NotFoundException("No contact found");
		}
		else {
			for (Update data:datas){
				if(data instanceof Add){
					Add newData=(Add)data;
					try{
						switch(newData.getType()){
							case ADDRESS:
								AddressData addr=(AddressData)newData.getInfo();
								executor.addAddr(contactId, addr);
								break;
							case EMAIL:
								EmailData email=(EmailData)newData.getInfo();
								executor.addEmail(contactId, email);
								break;
							case IM:
								IMData im=(IMData)newData.getInfo();
								executor.addIM(contactId, im);
								break;
							case PHONE:
								PhoneNoData tel=(PhoneNoData)newData.getInfo();
								executor.addTel(contactId, tel);
								break;
							case URL:
								URLData url=(URLData)newData.getInfo();
								executor.addURL(contactId, url);
								break;
							default:
								//TODO:what should I do?
						}
					}
					catch(ClassCastException e){
						//TODO:what should I do?
						//throw new InvalidRequestException("trans error");
					}
				}
				else if(data instanceof Edit){
					Edit newData=(Edit)data;
					try{
						switch(newData.getType()){
							case ADDRESS:
								
								break;
							case EMAIL:
								
								break;
							case IM:
								
								break;
							case PHONE:
								
								break;
							case URL:
								
								break;
							default:
								//TODO:what should I do?
						}
					}
					catch(ClassCastException e){
						//TODO:what should I do?
						//throw new InvalidRequestException("trans error");
					}
				}
				else if(data instanceof Delete){
					Delete newData=(Delete)data;				
						switch(newData.getType()){
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
								//TODO:what should I do?
						}										
				}
				else{
					//TODO:what should I do?
				}
			}
		}
		return null;
	}
}


