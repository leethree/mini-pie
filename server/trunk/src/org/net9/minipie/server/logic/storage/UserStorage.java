/**
 * UserStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.List;

import org.net9.minipie.server.data.constant.InfoField;
import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.storage.BasicUserInfo;


/**
 * @author Seastar
 *
 */
public interface UserStorage {
	Long add(String name, String pwd, String email);

	Long addAddr(Long userId, AddressData value);

	Long addEmail(Long userId, EmailData value);

	Long addIM(Long userId, IMData value);

	Long addTel(Long userId, PhoneNoData value);

	Long addURL(Long userId, URLData value);

	void delAddr(Long id);

	void delAddtional(Long id);

	void delEmail(Long id);

	void delIM(Long id);

	void delTel(Long id);

	void delURL(Long id);

	void editAddr(Long userId, InfoField attribute, Object value);

	//void editAdditional(Long userId, InfoField attribute, Object value);

	void editBasicInfo(Long userId, InfoField attribute, Object value);

	void editEmail(Long userId, InfoField attribute, Object value);

	void editIM(Long userId, InfoField attribute, Object value);

	void editTel(Long userId, InfoField attribute, Object value);

	void editURL(Long userId, InfoField attribute, Object value);

	//List<Object[]> search(Object... criteria);

	List<AddressData> selectAddr(Long userId);

	//List<> selectAddtional(Long userId);

	List<BasicUserInfo> selectBasicInfo(Long userId);

	List<EmailData> selectEmail(Long userId);

	List<IMData> selectIM(Long userId);

	List<PhoneNoData> selectTel(Long userId);

	List<URLData> selectURL(Long userId);
}
