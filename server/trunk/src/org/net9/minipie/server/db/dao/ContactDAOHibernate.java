package org.net9.minipie.server.db.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.constant.Gender;
import org.net9.minipie.server.data.constant.InfoField;
import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.ContactAddress;
import org.net9.minipie.server.db.entity.ContactEmail;
import org.net9.minipie.server.db.entity.ContactIM;
import org.net9.minipie.server.db.entity.ContactPhoneNo;
import org.net9.minipie.server.db.entity.ContactURL;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.enums.Bool;
import org.net9.minipie.server.exception.NotFoundException;

public class ContactDAOHibernate extends GenericHibernateDAO<Contact, Long>
		implements ContactDAO {

	public Long addAddr(Long contactId, AddressData addressData) {
		Contact contact = findById(contactId);
		ContactAddress contactAddr = new ContactAddress();
		contactAddr.setValue(addressData.getValue());
		contactAddr.setType(addressData.getType());
		if (addressData.isPrimary() == true)
			contactAddr.setPrimary(Bool.TRUE);
		else
			contactAddr.setPrimary(Bool.FALSE);
		contactAddr.setContact(contact);
		ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
		cadh.begin();
		Long id = cadh.makePersistent(contactAddr).getId();
		cadh.commit();
		begin();
		contact.getAddress().add(contactAddr);
		makePersistent(contact);
		commit();
		return id;
	}

	public Long addAddtional(Long contactId, Object... value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addEmail(Long contactId, EmailData emailData) {
		Contact contact = findById(contactId);
		ContactEmail contactEmail = new ContactEmail();
		contactEmail.setValue(emailData.getValue());
		contactEmail.setType(emailData.getType());
		if (emailData.isPrimary() == true)
			contactEmail.setPrimary(Bool.TRUE);
		else
			contactEmail.setPrimary(Bool.FALSE);
		contactEmail.setContact(contact);
		ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
		cedh.begin();
		Long id = cedh.makePersistent(contactEmail).getId();
		cedh.commit();
		begin();
		contact.getEmails().add(contactEmail);
		makePersistent(contact);
		commit();
		return id;
	}

	public Long addGroupContact(Long groupId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addIM(Long contactId, IMData imData) {
		Contact contact = findById(contactId);
		ContactIM contactIM = new ContactIM();
		contactIM.setValue(imData.getValue());
		contactIM.setType(imData.getType());
		if (imData.isPrimary() == true)
			contactIM.setPrimary(Bool.TRUE);
		else
			contactIM.setPrimary(Bool.FALSE);
		contactIM.setContact(contact);
		ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
		cidh.begin();
		Long id = cidh.makePersistent(contactIM).getId();
		cidh.commit();
		begin();
		contact.getIms().add(contactIM);
		makePersistent(contact);
		commit();
		return id;
	}

	public Long addTel(Long contactId, PhoneNoData phoneNoData) {
		Contact contact = findById(contactId);
		ContactPhoneNo contactPhone = new ContactPhoneNo();
		contactPhone.setValue(phoneNoData.getValue());
		contactPhone.setType(phoneNoData.getType());
		if (phoneNoData.isPrimary() == true)
			contactPhone.setPrimary(Bool.TRUE);
		else
			contactPhone.setPrimary(Bool.FALSE);
		contactPhone.setContact(contact);
		ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
		cpdh.begin();
		Long id = cpdh.makePersistent(contactPhone).getId();
		cpdh.commit();
		begin();
		contact.getPhones().add(contactPhone);
		makePersistent(contact);
		commit();
		return id;
	}

	public Long addURL(Long contactId, URLData urlData) {
		Contact contact = findById(contactId);
		ContactURL contactURL = new ContactURL();
		contactURL.setValue(urlData.getValue());
		contactURL.setType(urlData.getType());
		if (urlData.isPrimary() == true)
			contactURL.setPrimary(Bool.TRUE);
		else
			contactURL.setPrimary(Bool.FALSE);
		contactURL.setContact(contact);
		ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
		cudh.begin();
		Long id = cudh.makePersistent(contactURL).getId();
		cudh.commit();
		begin();
		contact.getUrls().add(contactURL);
		makePersistent(contact);
		commit();
		return id;
	}

	public Long addUserContact(Long userId, String name) {
		Contact newContact = new Contact();
		newContact.setName(name);
		UserDAOHibernate udh = new UserDAOHibernate();
		User user = udh.findById(userId);
		user.getContacts().add(newContact);
		newContact.setOwner(user);
		udh.begin();
		udh.makePersistent(user);
		udh.commit();
		begin();
		Long id = makePersistent(newContact).getId();
		commit();
		return id;
	}

	public Long addUserShadow(Long userId, Long targeted) {
		// TODO Auto-generated method stub
		return null;
	}

	public void del(Long contactId) {
		try{
			Contact contact = findById(contactId);
			begin();
			makeTransient(contact);
			commit();
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannot find item with given contact id");
		}
	}

	public void delAddr(Long id) {
		try{
			ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
			ContactAddress contactAddr = cadh.findById(id);
			cadh.begin();
			cadh.makeTransient(contactAddr);
			cadh.commit();
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find address item with give id");
		}
	}

	public void delAddtional(Long id) {
		// TODO Auto-generated method stub

	}

	public void delEmail(Long id) {
		try{
			ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
			ContactEmail contactEmail = cedh.findById(id);
			cedh.begin();
			cedh.makeTransient(contactEmail);
			cedh.commit();
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find email item with give id");
		}
	}

	public void delIM(Long id) {
		try{
			ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
			ContactIM contactIM = cidh.findById(id);
			cidh.begin();
			cidh.makeTransient(contactIM);
			cidh.commit();
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find im item with give id");
		}
	}

	public void delTel(Long id) {
		try{
			ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
			ContactPhoneNo contactPhone = cpdh.findById(id);
			cpdh.begin();
			cpdh.makeTransient(contactPhone);
			cpdh.commit();
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find phone item with give id");
		}
	}

	public void delURL(Long id) {
		try{
			ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
			ContactURL contactURL = cudh.findById(id);
			cudh.begin();
			cudh.makeTransient(contactURL);
			cudh.commit();
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find url item with give id");
		}
	}

	public void editAdditional(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
	}

	public void editAddr(Long id, String attribute, Object value) {
		ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
		ContactAddress contactAddr = null;
		try{
			contactAddr = cadh.findById(id);
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find address "+attribute+" item with give id");
		}
		if (attribute.equals("value")) {
			String addr = (String) value;
			contactAddr.setValue(addr);
		} else if (attribute.equals("type")) {
			String type = (String) value;
			contactAddr.setType(type);
		} else if (attribute.equals("primary")) {
			Bool primary = (Bool) value;
			contactAddr.setPrimary(primary);
		}
		cadh.begin();
		cadh.makePersistent(contactAddr);
		cadh.commit();
	}

	public void editBasicInfo(Long id, String attribute, Object value) {
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Contact contact = null;
		try{
			contact = cdh.findById(id);
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find basic info "+attribute+" item with give id");
		}
		if (attribute.equals("name")) {
			String name = (String) value;
			contact.setName(name);
		} else if (attribute.equals("image")) {
			String image = (String) value;
			contact.setImage(image);
		} else if (attribute.equals("nickName")) {
			String nickName = (String) value;
			contact.setNickName(nickName);
		} else if (attribute.equals("gender")) {
			Gender gender = (Gender) value;
			contact.setGender(gender);
		} else if (attribute.equals("birthday")) {
			String birthday = (String) value;
			contact.setBirthday(birthday);
		} else if (attribute.equals("notes")) {
			String notes = (String) value;
			contact.setNotes(notes);
		} else if (attribute.equals("relationship")) {
			String relationship = (String) value;
			contact.setRelationship(relationship);
		}
		cdh.begin();
		cdh.makePersistent(contact);
		cdh.commit();
	}

	public void editEmail(Long id, String attribute, Object value) {
		ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
		ContactEmail contactEmail = null;
		try{
			contactEmail = cedh.findById(id);
		}catch(HibernateException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find email "+attribute+" item with give id");
		}
		if (attribute.equals("value")) {
			String email = (String) value;
			contactEmail.setValue(email);
		} else if (attribute.equals("type")) {
			String type = (String) value;
			contactEmail.setType(type);
		} else if (attribute.equals("primary")) {
			Bool primary = (Bool) value;
			contactEmail.setPrimary(primary);
		}
		cedh.begin();
		cedh.makePersistent(contactEmail);
		cedh.commit();
	}

	public void editIM(Long id, String attribute, Object value) {
		ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
		ContactIM contactIM = null;
		try{
			contactIM = cidh.findById(id);
		}catch(HibernateException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find im "+attribute+" item with give id");
		}
		if (attribute.equals("value")) {
			String im = (String) value;
			contactIM.setValue(im);
		} else if (attribute.equals("type")) {
			String type = (String) value;
			contactIM.setType(type);
		} else if (attribute.equals("primary")) {
			Bool primary = (Bool) value;
			contactIM.setPrimary(primary);
		}
		cidh.begin();
		cidh.makePersistent(contactIM);
		cidh.commit();
	}

	public void editTel(Long id, String attribute, Object value) {
		ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
		ContactPhoneNo contactPhone = null; 
		try{
			contactPhone = cpdh.findById(id);
		}catch(HibernateException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find tel "+attribute+" item with give id");
		}
		if (attribute.equals("value")) {
			String tel = (String) value;
			contactPhone.setValue(tel);
		} else if (attribute.equals("type")) {
			String type = (String) value;
			contactPhone.setType(type);
		} else if (attribute.equals("primary")) {
			Bool primary = (Bool) value;
			contactPhone.setPrimary(primary);
		}
		cpdh.begin();
		cpdh.makePersistent(contactPhone);
		cpdh.commit();
	}

	public void editURL(Long id, String attribute, Object value) {
		ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
		ContactURL contactURL = null;
		try{
			contactURL = cudh.findById(id);
		}catch(HibernateException e){
			e.printStackTrace();
			throw new NotFoundException("Cannt find url "+attribute+" item with give id");
		}
		if (attribute.equals("value")) {
			String url = (String) value;
			contactURL.setValue(url);
		} else if (attribute.equals("type")) {
			String type = (String) value;
			contactURL.setType(type);
		} else if (attribute.equals("primary")) {
			Bool primary = (Bool) value;
			contactURL.setPrimary(primary);
		}
		cudh.begin();
		cudh.makePersistent(contactURL);
		cudh.commit();
	}

	public List<Object[]> search(Object... criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.net9.minipie.server.db.dao.ContactDAO#selectBasicInfo(java.lang.Long)
	 */
	public BasicContact selectBasicInfo(Long contactId) {
		try {
			ContactDAOHibernate cdh = new ContactDAOHibernate();
			Contact contact = cdh.findById(contactId);
			BasicContact basicContact = new BasicContact(contactId, contact.getName(), contact.getImage(), contact.getNickName(),
					contact.getGender(), contact.getBirthday(), contact.getNotes(), contact.getRelationship(),
					(contact.getOwner()!=null) ? contact.getOwner().getId(): 0, 
					(contact.getShadowOf()!=null) ? contact.getShadowOf().getId():0, 
					(contact.getGroup()!=null) ? contact.getGroup().getId():0, 
					(contact.getPermission()!=null)?contact.getPermission():null);
			return basicContact;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException("There's no contact with ID: "
					+ contactId);
		}
	}

	public List<AddressData> selectAddr(Long contactId) {
		try{
			ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
			Criterion criterion = Restrictions.eq("contact.id", contactId);
			List<ContactAddress> result = cadh.findByCriteria(criterion);
			List<AddressData> selectedResult = new ArrayList<AddressData>();
			Iterator<ContactAddress> iter = result.iterator();
			while (iter.hasNext()) {
				ContactAddress contactAddr = (ContactAddress) iter.next();
				AddressData addressData = new AddressData();
				addressData.setId(contactAddr.getId());
				addressData.setValue(contactAddr.getValue());
				addressData.setType(contactAddr.getType());
				if (contactAddr.getPrimary() == Bool.TRUE)
					addressData.setPrimary(true);
				else
					addressData.setPrimary(false);
				selectedResult.add(addressData);
			}
			return selectedResult;
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There's no contact with ID: "
					+ contactId);
		}
	}

	public List<Object[]> selectAddtional(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EmailData> selectEmail(Long contactId) {
		try{
			ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
			Criterion criterion = Restrictions.eq("contact.id", contactId);
			List<ContactEmail> result = cedh.findByCriteria(criterion);
			List<EmailData> selectedResult = new ArrayList<EmailData>();
			Iterator<ContactEmail> iter = result.iterator();
			while (iter.hasNext()) {
				ContactEmail contactEmail = (ContactEmail) iter.next();
				EmailData emailData = new EmailData();
				emailData.setId(contactEmail.getId());
				emailData.setValue(contactEmail.getValue());
				emailData.setType(contactEmail.getType());
				if (contactEmail.getPrimary() == Bool.TRUE)
					emailData.setPrimary(true);
				else
					emailData.setPrimary(false);
				selectedResult.add(emailData);
			}
			return selectedResult;
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There's no contact with ID: "
					+ contactId);
		}
	}

	public List<Object[]> selectGroupContact(Long groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IMData> selectIM(Long contactId) {
		try{
			ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
			Criterion criterion = Restrictions.eq("contact.id", contactId);
			List<ContactIM> result = cidh.findByCriteria(criterion);
			List<IMData> selectedResult = new ArrayList<IMData>();
			Iterator<ContactIM> iter = result.iterator();
			while (iter.hasNext()) {
				ContactIM contactIM = (ContactIM) iter.next();
				IMData imData = new IMData();
				imData.setId(contactIM.getId());
				imData.setValue(contactIM.getValue());
				imData.setType(contactIM.getType());
				if (contactIM.getPrimary() == Bool.TRUE)
					imData.setPrimary(true);
				else
					imData.setPrimary(false);
				selectedResult.add(imData);
			}
			return selectedResult;
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There's no contact with ID: "
					+ contactId);
		}
	}

	public List<CommonListEntry> selectOwnerContact(Long ownerId) {
		try{
			ContactDAOHibernate cdh = new ContactDAOHibernate();
			Criterion criterion = Restrictions.eq("owner.id", ownerId);
			List<Contact> result = cdh.findByCriteria(criterion);
			List<CommonListEntry> selectedResult = new ArrayList<CommonListEntry>();
			Iterator<Contact> iter = result.iterator();
			while (iter.hasNext()) {
				Contact contact = (Contact) iter.next();
				CommonListEntry minimalContact = new CommonListEntry(contact.getId(), 
						contact.getName(),contact.getImage());
				selectedResult.add(minimalContact);
			}
			return selectedResult;
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There's no user with ID: "
					+ ownerId);
		}
	}

	public List<Object[]> selectShadow(Long ownerId, Long shadowOf) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectSharedContact(Long ownerId, Permission perm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PhoneNoData> selectTel(Long contactId) {
		try{
			ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
			Criterion criterion = Restrictions.eq("contact.id", contactId);
			List<ContactPhoneNo> result = cpdh.findByCriteria(criterion);
			List<PhoneNoData> selectedResult = new ArrayList<PhoneNoData>();
			Iterator<ContactPhoneNo> iter = result.iterator();
			while (iter.hasNext()) {
				ContactPhoneNo contactPhone = (ContactPhoneNo) iter.next();
				PhoneNoData phoneData = new PhoneNoData();
				phoneData.setId(contactPhone.getId());
				phoneData.setValue(contactPhone.getValue());
				phoneData.setType(contactPhone.getType());
				if (contactPhone.getPrimary() == Bool.TRUE)
					phoneData.setPrimary(true);
				else
					phoneData.setPrimary(false);
				selectedResult.add(phoneData);
			}
			return selectedResult;
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There's no contact with ID: "
					+ contactId);
		}
	}

	public List<URLData> selectURL(Long contactId) {
		try{
			ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
			Criterion criterion = Restrictions.eq("contact.id", contactId);
			List<ContactURL> result = cudh.findByCriteria(criterion);
			List<URLData> selectedResult = new ArrayList<URLData>();
			Iterator<ContactURL> iter = result.iterator();
			while (iter.hasNext()) {
				ContactURL contactURL = (ContactURL) iter.next();
				URLData urlData = new URLData();
				urlData.setId(contactURL.getId());
				urlData.setValue(contactURL.getValue());
				urlData.setType(contactURL.getType());
				if (contactURL.getPrimary() == Bool.TRUE)
					urlData.setPrimary(true);
				else
					urlData.setPrimary(false);
				selectedResult.add(urlData);
			}
			return selectedResult;
		}catch(ObjectNotFoundException e){
			throw new NotFoundException("There's no contact with ID: "
					+ contactId);
		}
	}

	public void clear() {
		super.clear();
	}

	public List<Contact> findAll() {
		return super.findAll();
	}

	public List<Contact> findByExample(Contact exampleInstance,
			String... excludeProperties) {
		return super.findByExample(exampleInstance, excludeProperties);
	}

	public Contact findById(Long id) {
		return super.findById(id, false);
	}

	public void flush() {
		super.flush();
	}

	public Contact makePersistent(Contact entity) {
		return super.makePersistent(entity);
	}

	public void makeTransient(Contact entity) {
		super.makeTransient(entity);
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.ContactStorage#editAddr(java.lang.Long, org.net9.minipie.server.data.constant.InfoField, java.lang.Object)
	 */
	public void editAddr(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.ContactStorage#editBasicInfo(java.lang.Long, org.net9.minipie.server.data.constant.InfoField, java.lang.Object)
	 */
	public void editBasicInfo(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.ContactStorage#editEmail(java.lang.Long, org.net9.minipie.server.data.constant.InfoField, java.lang.Object)
	 */
	public void editEmail(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.ContactStorage#editIM(java.lang.Long, org.net9.minipie.server.data.constant.InfoField, java.lang.Object)
	 */
	public void editIM(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.ContactStorage#editTel(java.lang.Long, org.net9.minipie.server.data.constant.InfoField, java.lang.Object)
	 */
	public void editTel(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.storage.ContactStorage#editURL(java.lang.Long, org.net9.minipie.server.data.constant.InfoField, java.lang.Object)
	 */
	public void editURL(Long id, InfoField attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

}
