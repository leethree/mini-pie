package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.storage.BasicUser;
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

	//List<Object[]> search(Object... criteria);

	List<AddressData> selectAddr(Long contactId);

	//List<Object[]> selectAddtional(Long contactId);

	List<BasicUser> selectBasicInfo(Long contactId);

	List<EmailData> selectEmail(Long contactId);

	List<IMData> selectIM(Long contactId);

	List<PhoneNoData> selectTel(Long contactId);

	List<URLData> selectURL(Long contactId);
}
