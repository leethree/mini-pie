/**
 * UserStorage.java
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
import org.net9.minipie.server.data.field.AddAsContactPermission;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.storage.BasicUser;
import org.net9.minipie.server.data.storage.CommonListEntry;
import org.net9.minipie.server.data.storage.Query;


/**
 * @author Seastar
 *
 */
public interface UserStorage {
	Long add(String name,String displayName ,String pwd, String email);

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

	void editBasicInfo(Long Id, InfoField attribute, Object value);

	void editEmail(Long Id, InfoField attribute, Object value);

	void editIM(Long Id, InfoField attribute, Object value);

	void editTel(Long Id, InfoField attribute, Object value);

	void editURL(Long Id, InfoField attribute, Object value);

	//List<Object[]> search(Object... criteria);

	Collection<AddressData> selectAddr(Long userId);

	//List<> selectAddtional(Long userId);

	BasicUser selectBasicInfo(Long userId);

	Collection<EmailData> selectEmail(Long userId);

	Collection<IMData> selectIM(Long userId);

	Collection<PhoneNoData> selectTel(Long userId);

	Collection<URLData> selectURL(Long userId);
		
	Long findAddressOwner(Long addrId);
	Long findEmailOwner(Long emailId);
	Long findIMOwner(Long imId);
	Long findTelOwner(Long telId);
	Long findURLOwner(Long urlId);
	//Void setPermission(Permission perm,Long userId);
	Void setAddAsContactPermission(AddAsContactPermission perm,Long userId);
	Long selectLegalUser(String name,String password);
	//String selectUserPassword(String name);
	Collection<CommonListEntry>  searchMyUserOrContact(Long userid,Collection<Query> query);
	Collection<BasicUser> searchAllUser(Collection<Query> query); 
}
