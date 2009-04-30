package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.data.constant.Permission;
import org.net9.minipie.server.data.AddressData;
import org.net9.minipie.server.data.BasicContact;
import org.net9.minipie.server.data.EmailData;
import org.net9.minipie.server.data.IMData;
import org.net9.minipie.server.data.PhoneNoData;
import org.net9.minipie.server.data.URLData;

public interface ContactDAO extends GenericDAO<Contact, Long> {
	Long addUserContact(Long userId, String name);
	Long addGroupContact(Long groupId, String name);
	Long addUserShadow(Long userId,Long targeted);
	Long addAddr(Long contactId, AddressData addressData);
	Long addAddtional(Long contactId, Object...value);
	Long addEmail(Long contactId, EmailData emailData);
	Long addIM(Long contactId, IMData imData);
	Long addTel(Long contactId, PhoneNoData phoneNoData);
	Long addURL(Long contactId, URLData urlData);
	void delAddr(Long id);
	void delAddtional(Long id);
	void delEmail(Long id);
	void delIM(Long id);
	void delTel(Long id);
	void delURL(Long id);
	void editAddr(Long id, String attribute, Object value);
	void editAdditional(Long id, String attribute, Object value);
	void editBasicInfo(Long id, String attribute, Object value);
	void editEmail(Long id, String attribute, Object value);
	void editIM(Long id, String attribute, Object value);
	void editTel(Long id, String attribute, Object value);
	void editURL(Long id, String attribute, Object value);
	List<Object[]> search(Object...criteria);
	List<AddressData> selectAddr(Long contactId);
	List<Object[]> selectAddtional(Long contactId);
	List<BasicContact> selectBasicInfo(Long contactId);
	List<EmailData> selectEmail(Long contactId);
	List<IMData> selectIM(Long contactId);
	List<PhoneNoData> selectTel(Long contactId);
	List<URLData> selectURL(Long contactId);
	List<Object[]> selectSharedContact(Long ownerId, Permission perm);
	List<Object[]> selectShadow(Long ownerId, Long shadowOf);
	List<Object[]> selectOwnerContact(Long ownerId);
	List<Object[]> selectGroupContact(Long groupId);
	void del(Long contactId);
}
