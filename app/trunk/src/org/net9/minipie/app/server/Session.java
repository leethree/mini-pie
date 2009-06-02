/**
 * 
 */
package org.net9.minipie.app.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.data.TagBean;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;

/**
 * @author LeeThree
 * 
 */
public class Session {

	private Backend backend;
	private PersonBean profile;
	private Map<Long, PersonBean> userContacts;
	private List<PersonBean> userContactList;
	private Map<Long, PersonBean> contacts;
	private List<PersonBean> contactList;
	private List<TagBean> tagList;

	public Session(String username, String password) throws GenericException,
			LoginFailedException {
		backend = new Backend(username, password);
		userContacts = new HashMap<Long, PersonBean>();
		contacts = new HashMap<Long, PersonBean>();
		initialize();
	}

	public PersonBean getProfile() {
		return profile;
	}

	public List<PersonBean> listUserContacts() {
		return userContactList;
	}

	public List<PersonBean> listContacts() {
		return contactList;
	}

	public List<TagBean> listTags() {
		return tagList;
	}

	public PersonBean viewUserContact(long userId) throws GenericException, LoginFailedException {
		PersonBean user = userContacts.get(userId);
		if (user == null) {
			user = backend.getUserById(userId);
			userContacts.put(userId, user);
		}
		return user;
	}
	
	public PersonBean viewContact(long contactId) throws GenericException, LoginFailedException {
		PersonBean contact = contacts.get(contactId);
		if (contact == null) {
			contact = backend.getContactById(contactId);
			contacts.put(contactId, contact);
		}
		return contact;
	}

	private void initialize() throws GenericException, LoginFailedException {
		profile = backend.getProfile();
		userContactList = backend.listUserContacts();
		contactList = backend.listContacts();
		// TODO
	}
}
