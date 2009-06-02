/**
 * ContactStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.data.storage.BasicContact;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.Query;

/**
 * @author Seastar
 *
 */
public interface ContactStorage {
	Long addUserContact(Long userId, String name);
	Long addShadow(Long userId,Long targetId);
	Long addAddr(Long contactId, AddressData addressData);
	//Long addAddtional(Long contactId, Object...value);
	Long addEmail(Long contactId, EmailData emailData);
	Long addIM(Long contactId, IMData imData);
	Long addTel(Long contactId, PhoneNoData phoneNoData);
	Long addURL(Long contactId, URLData urlData);
	void delAddr(Long id);
	//void delAddtional(Long id);
	void delEmail(Long id);
	void delIM(Long id);
	void delTel(Long id);
	void delURL(Long id);
	void editAddr(Long id, InfoField attribute, Object value);
	//void editAdditional(Long id, String attribute, Object value);
	void editBasicInfo(Long id, InfoField attribute, Object value);
	void editEmail(Long id, InfoField attribute, Object value);
	void editIM(Long id, InfoField attribute, Object value);
	void editTel(Long id, InfoField attribute, Object value);
	void editURL(Long id, InfoField attribute, Object value);
	Collection<AddressData> selectAddr(Long contactId);
	//Collection<Object[]> selectAddtional(Long contactId);
	BasicContact selectBasicInfo(Long contactId);
	Collection<EmailData> selectEmail(Long contactId);
	Collection<IMData> selectIM(Long contactId);
	Collection<PhoneNoData> selectTel(Long contactId);
	Collection<URLData> selectURL(Long contactId);
	Collection<CommonListEntry> selectOwnerContact(Long ownerId);
	BasicContact selectShadowOf(Long ownerId,Long shadowOf);
	void del(Long contactId);
	Long findAddressOwner(Long addrId);
	Long findEmailOwner(Long emailId);
	Long findIMOwner(Long imId);
	Long findTelOwner(Long telId);
	Long findURLOwner(Long urlId);
	Void setPermission(Permission perm,Long contactId);
	//Collection<BasicUser> searchAllUser(Collection<Query> query); 
}
