package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.Contact;
import org.net9.minipie.server.db.entity.constant.Permission;

public interface ContactDAO extends GenericDAO<Contact, Long> {
	Long addUserContact(Long userId, String name);
	Long addGroupContact(Long groupId, String name);
	Long addUserShadow(Long userId,Long targeted);
	Long addAddr(Long contactId, Object[] value);
	Long addAddtional(Long contactId, Object[] value);
	Long addEmail(Long contactId, Object[] value);
	Long addIM(Long contactId, Object[] value);
	Long addTel(Long contactId, Object[] value);
	Long addURL(Long contactId, Object[] value);
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
	List<Object[]> selectAddr(Long contactId);
	List<Object[]> selectAddtional(Long contactId);
	List<Object[]> selectBasicInfo(Long contactId);
	List<Object[]> selectEmail(Long contactId);
	List<Object[]> selectIM(Long contactId);
	List<Object[]> selectTel(Long contactId);
	List<Object[]> selectURL(Long contactId);
	List<Object[]> selectSharedContact(Long ownerId, Permission perm);
	List<Object[]> selectShadow(Long ownerId, Long shadowOf);
	List<Object[]> selectOwnerContact(Long ownerId);
	List<Object[]> selectGroupContact(Long groupId);
	void del(Long contactId);
}
