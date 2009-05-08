/**
 * ContactStorage.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

import java.util.Collection;
import org.net9.minipie.server.data2.storage.BasicContact;
import org.net9.minipie.server.data2.storage.ContactListEntry;
import org.net9.minipie.server.data2.entity.AddressData;
import org.net9.minipie.server.data2.entity.EmailData;
import org.net9.minipie.server.data2.entity.IMData;
import org.net9.minipie.server.data2.entity.PhoneNoData;
import org.net9.minipie.server.data2.entity.URLData;

/**
 * @author Seastar
 *
 */
public interface ContactStorage {
	Long addUserContact(Long userId, String name);
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
	void editAddr(Long id, String attribute, Object value);
	//void editAdditional(Long id, String attribute, Object value);
	void editBasicInfo(Long id, String attribute, Object value);
	void editEmail(Long id, String attribute, Object value);
	void editIM(Long id, String attribute, Object value);
	void editTel(Long id, String attribute, Object value);
	void editURL(Long id, String attribute, Object value);
	Collection<AddressData> selectAddr(Long contactId);
	//Collection<Object[]> selectAddtional(Long contactId);
	BasicContact selectBasicInfo(Long contactId);
	Collection<EmailData> selectEmail(Long contactId);
	Collection<IMData> selectIM(Long contactId);
	Collection<PhoneNoData> selectTel(Long contactId);
	Collection<URLData> selectURL(Long contactId);
	Collection<ContactListEntry> selectOwnerContact(Long ownerId);
	void del(Long contactId);
}
