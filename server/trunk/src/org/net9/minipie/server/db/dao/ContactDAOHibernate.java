package org.net9.minipie.server.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.ContactAddress;
import org.net9.minipie.server.db.entity.ContactEmail;
import org.net9.minipie.server.db.entity.ContactIM;
import org.net9.minipie.server.db.entity.ContactPhoneNo;
import org.net9.minipie.server.db.entity.ContactURL;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.db.entity.constant.Bool;
import org.net9.minipie.server.db.entity.constant.Gender;
import org.net9.minipie.server.db.entity.constant.Permission;

public class ContactDAOHibernate extends GenericHibernateDAO<Contact, Long> implements
		ContactDAO {
		
	public Long addAddr(Long contactId, Object...value) {
		Contact contact = findById(contactId);
		ContactAddress contactAddr = new ContactAddress();
		int k = 0;
		for(Object obj: value){
			switch(k){
			case 0:
				if(obj!=null){
					String addr = (String) obj;
					contactAddr.setValue(addr);
				}
				break;
			case 1:
				if(obj!=null){
					String type = (String) obj;
					contactAddr.setType(type);
				}
				break;
			case 2:
				if(obj!=null){
					Bool primary = (Bool) obj;
					contactAddr.setPrimary(primary);
				}
				break;
			default:break;
			}
			k++;
		}
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

	public Long addAddtional(Long contactId, Object...value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addEmail(Long contactId, Object... value) {
		Contact contact = findById(contactId);
		ContactEmail contactEmail = new ContactEmail();
		int k = 0;
		for(Object obj: value){
			switch(k){
			case 0:
				if(obj!=null){
					String email = (String) obj;
					contactEmail.setValue(email);
				}
				break;
			case 1:
				if(obj!=null){
					String type = (String) obj;
					contactEmail.setType(type);
				}
				break;
			case 2:
				if(obj!=null){
					Bool primary = (Bool) obj;
					contactEmail.setPrimary(primary);
				}
				break;
			default:break;
			}
			k++;
		}
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

	public Long addIM(Long contactId, Object... value) {
		Contact contact = findById(contactId);
		ContactIM contactIM = new ContactIM();
		int k = 0;
		for(Object obj: value){
			switch(k){
			case 0:
				if(obj!=null){
					String im = (String) obj;
					contactIM.setValue(im);
				}
				break;
			case 1:
				if(obj!=null){
					String type = (String) obj;
					contactIM.setType(type);
				}
				break;
			case 2:
				if(obj!=null){
					Bool primary = (Bool) obj;
					contactIM.setPrimary(primary);
				}
				break;
			default:break;
			}
			k++;
		}
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

	public Long addTel(Long contactId, Object... value) {
		Contact contact = findById(contactId);
		ContactPhoneNo contactPhone = new ContactPhoneNo();
		int k = 0;
		for(Object obj: value){
			switch(k){
			case 0:
				if(obj!=null){
					String tel = (String) obj;
					contactPhone.setValue(tel);
				}
				break;
			case 1:
				if(obj!=null){
					String type = (String) obj;
					contactPhone.setType(type);
				}
				break;
			case 2:
				if(obj!=null){
					Bool primary = (Bool) obj;
					contactPhone.setPrimary(primary);
				}
				break;
			default:break;
			}
			k++;
		}
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

	public Long addURL(Long contactId, Object... value) {
		Contact contact = findById(contactId);
		ContactURL contactURL = new ContactURL();
		int k = 0;
		for(Object obj: value){
			switch(k){
			case 0:
				if(obj!=null){
					String url = (String) obj;
					contactURL.setValue(url);
				}
				break;
			case 1:
				if(obj!=null){
					String type = (String) obj;
					contactURL.setType(type);
				}
				break;
			case 2:
				if(obj!=null){
					Bool primary = (Bool) obj;
					contactURL.setPrimary(primary);
				}
				break;
			default:break;
			}
			k++;
		}
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
		Contact contact = findById(contactId);
		begin();
		makeTransient(contact);
		commit();
	}

	public void delAddr(Long id) {
		ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
		ContactAddress contactAddr = cadh.findById(id);
		cadh.begin();
		cadh.makeTransient(contactAddr);
		cadh.commit();
	}

	public void delAddtional(Long id) {
		// TODO Auto-generated method stub

	}

	public void delEmail(Long id) {
		ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
		ContactEmail contactEmail = cedh.findById(id);
		cedh.begin();
		cedh.makeTransient(contactEmail);
		cedh.commit();
	}

	public void delIM(Long id) {
		ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
		ContactIM contactIM = cidh.findById(id);
		cidh.begin();
		cidh.makeTransient(contactIM);
		cidh.commit();
	}

	public void delTel(Long id) {
		ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
		ContactPhoneNo contactPhone = cpdh.findById(id);
		cpdh.begin();
		cpdh.makeTransient(contactPhone);
		cpdh.commit();
	}

	public void delURL(Long id) {
		ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
		ContactURL contactURL = cudh.findById(id);
		cudh.begin();
		cudh.makeTransient(contactURL);
		cudh.commit();
	}

	public void editAdditional(Long id, String attribute, Object value) {
		// TODO Auto-generated method stub
	}

	public void editAddr(Long id, String attribute, Object value) {
		ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
		ContactAddress contactAddr = cadh.findById(id);
		if(attribute.equals("value")){
			String addr = (String) value;
			contactAddr.setValue(addr);
		}else if(attribute.equals("type")){
			String type = (String) value;
			contactAddr.setType(type);
		}else if(attribute.equals("primary")){
			Bool primary = (Bool) value;
			contactAddr.setPrimary(primary);
		}
		cadh.begin();
		cadh.makePersistent(contactAddr);
		cadh.commit();
	}

	public void editBasicInfo(Long id, String attribute, Object value) {
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Contact contact = cdh.findById(id);
		if(attribute.equals("name")){
			String name = (String) value;
			contact.setName(name);
		}else if(attribute.equals("image")){
			String image = (String) value;
			contact.setImage(image);
		}else if(attribute.equals("nickName")){
			String nickName = (String) value;
			contact.setNickName(nickName);
		}else if(attribute.equals("gender")){
			Gender gender = (Gender) value;
			contact.setGender(gender);
		}else if(attribute.equals("birthday")){
			Date birthday = (Date) value;
			contact.setBirthday(birthday);
		}else if(attribute.equals("notes")){
			String notes = (String) value;
			contact.setNotes(notes);
		}else if(attribute.equals("relationship")){
			String relationship = (String) value;
			contact.setRelationship(relationship);
		}
		cdh.begin();
		cdh.makePersistent(contact);
		cdh.commit();
	}

	public void editEmail(Long id, String attribute, Object value) {
		ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
		ContactEmail contactEmail = cedh.findById(id);
		if(attribute.equals("value")){
			String email = (String) value;
			contactEmail.setValue(email);
		}else if(attribute.equals("type")){
			String type = (String) value;
			contactEmail.setType(type);
		}else if(attribute.equals("primary")){
			Bool primary = (Bool) value;
			contactEmail.setPrimary(primary);
		}
		cedh.begin();
		cedh.makePersistent(contactEmail);
		cedh.commit();
	}

	public void editIM(Long id, String attribute, Object value) {
		ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
		ContactIM contactIM = cidh.findById(id);
		if(attribute.equals("value")){
			String im = (String) value;
			contactIM.setValue(im);
		}else if(attribute.equals("type")){
			String type = (String) value;
			contactIM.setType(type);
		}else if(attribute.equals("primary")){
			Bool primary = (Bool) value;
			contactIM.setPrimary(primary);
		}
		cidh.begin();
		cidh.makePersistent(contactIM);
		cidh.commit();
	}

	public void editTel(Long id, String attribute, Object value) {
		ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
		ContactPhoneNo contactPhone = cpdh.findById(id);
		if(attribute.equals("value")){
			String tel = (String) value;
			contactPhone.setValue(tel);
		}else if(attribute.equals("type")){
			String type = (String) value;
			contactPhone.setType(type);
		}else if(attribute.equals("primary")){
			Bool primary = (Bool) value;
			contactPhone.setPrimary(primary);
		}
		cpdh.begin();
		cpdh.makePersistent(contactPhone);
		cpdh.commit();
	}

	public void editURL(Long id, String attribute, Object value) {
		ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
		ContactURL contactURL = cudh.findById(id);
		if(attribute.equals("value")){
			String url = (String) value;
			contactURL.setValue(url);
		}else if(attribute.equals("type")){
			String type = (String) value;
			contactURL.setType(type);
		}else if(attribute.equals("primary")){
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

	@SuppressWarnings("unchecked")
	public List<Object[]> selectAddr(Long contactId) {
		ContactAddressDAOHibernate cadh = new ContactAddressDAOHibernate();
		Criteria crit = cadh.getSession().createCriteria(ContactAddress.class);
		crit.add(Restrictions.eq("id", contactId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("value"));
		projList.add(Projections.property("type"));
		projList.add(Projections.property("primary"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	public List<Object[]> selectAddtional(Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> selectBasicInfo(Long contactId) {
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Criteria crit = cdh.getSession().createCriteria(Contact.class);
		crit.add(Restrictions.eq("id", contactId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("name"));
		projList.add(Projections.property("image"));
		projList.add(Projections.property("nickName"));
		projList.add(Projections.property("gender"));
		projList.add(Projections.property("birthday"));
		projList.add(Projections.property("notes"));
		projList.add(Projections.property("relationship"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> selectEmail(Long contactId) {
		ContactEmailDAOHibernate cedh = new ContactEmailDAOHibernate();
		Criteria crit = cedh.getSession().createCriteria(ContactEmail.class);
		crit.add(Restrictions.eq("id", contactId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("value"));
		projList.add(Projections.property("type"));
		projList.add(Projections.property("primary"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	public List<Object[]> selectGroupContact(Long groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> selectIM(Long contactId) {
		ContactIMDAOHibernate cidh = new ContactIMDAOHibernate();
		Criteria crit = cidh.getSession().createCriteria(ContactIM.class);
		crit.add(Restrictions.eq("id", contactId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("value"));
		projList.add(Projections.property("type"));
		projList.add(Projections.property("primary"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> selectOwnerContact(Long ownerId) {
		ContactDAOHibernate cdh = new ContactDAOHibernate();
		Criteria crit = cdh.getSession().createCriteria(Contact.class);
		crit.add(Restrictions.eq("owner.id", ownerId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("name"));
		projList.add(Projections.property("image"));
		projList.add(Projections.property("nickName"));
		projList.add(Projections.property("gender"));
		projList.add(Projections.property("birthday"));
		projList.add(Projections.property("notes"));
		projList.add(Projections.property("relationship"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	public List<Object[]> selectShadow(Long ownerId, Long shadowOf) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> selectSharedContact(Long ownerId, Permission perm) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> selectTel(Long contactId) {
		ContactPhoneDAOHibernate cpdh = new ContactPhoneDAOHibernate();
		Criteria crit = cpdh.getSession().createCriteria(ContactPhoneNo.class);
		crit.add(Restrictions.eq("id", contactId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("value"));
		projList.add(Projections.property("type"));
		projList.add(Projections.property("primary"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> selectURL(Long contactId) {
		ContactURLDAOHibernate cudh = new ContactURLDAOHibernate();
		Criteria crit = cudh.getSession().createCriteria(ContactURL.class);
		crit.add(Restrictions.eq("id", contactId));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("value"));
		projList.add(Projections.property("type"));
		projList.add(Projections.property("primary"));
		crit.setProjection(projList);
		List result = crit.list();
		return result;
	}

	public void clear() {
		super.clear();
	}

	public List<Contact> findAll() {
		return super.findAll();
	}

	public List<Contact> findByExample(Contact exampleInstance, 
			String...excludeProperties) {
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

}
