package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.logic.storage.UserStorage;

public interface UserDAO extends GenericDAO<User, Long>, UserStorage {
	Long add(String name, String pwd, String email);

	Long addAddr(Long userId, Object... value);

	Long addAddtional(Long contactId, Object... value);

	Long addEmail(Long contactId, Object... value);

	Long addIM(Long contactId, Object... value);

	Long addTel(Long contactId, Object... value);

	Long addURL(Long contactId, Object... value);

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

	List<Object[]> search(Object... criteria);

	List<Object[]> selectAddr(Long contactId);

	List<Object[]> selectAddtional(Long contactId);

	List<Object[]> selectBasicInfo(Long contactId);

	List<Object[]> selectEmail(Long contactId);

	List<Object[]> selectIM(Long contactId);

	List<Object[]> selectTel(Long contactId);

	List<Object[]> selectURL(Long contactId);
}
