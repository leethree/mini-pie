package org.net9.minipie.server.db.dao;

import java.util.List;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.db.entity.User;
import org.net9.minipie.server.logic.storage.UserStorage;

public interface UserDAO extends GenericDAO<User, Long>, UserStorage {
	Long add(String name, String displayName, String pwd, String email);

	Long addAddr(Long userId, AddressData value);

	Long addAddtional(Long contactId, Object... value);

	Long addEmail(Long contactId, EmailData value);

	Long addIM(Long contactId, IMData value);

	Long addTel(Long contactId, PhoneNoData value);

	Long addURL(Long contactId, URLData value);

	void delAddr(Long id);

	void delAddtional(Long id);

	void delEmail(Long id);

	void delIM(Long id);

	void delTel(Long id);

	void delURL(Long id);

	void editAddr(Long id, InfoField attribute, Object value);

	void editAdditional(Long id, InfoField attribute, Object value);

	void editBasicInfo(Long id, InfoField attribute, Object value);

	void editEmail(Long id, InfoField attribute, Object value);

	void editIM(Long id, InfoField attribute, Object value);

	void editTel(Long id, InfoField attribute, Object value);

	void editURL(Long id, InfoField attribute, Object value);

	//List<Object[]> search(Object... criteria);

	List<AddressData> selectAddr(Long userId);

	//List<Object[]> selectAddtional(Long contactId);

	BasicUser selectBasicInfo(Long userId);

	List<EmailData> selectEmail(Long userId);

	List<IMData> selectIM(Long userId);

	List<PhoneNoData> selectTel(Long userId);

	List<URLData> selectURL(Long userId);
}
