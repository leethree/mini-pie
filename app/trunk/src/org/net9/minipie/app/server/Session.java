/**
 * 
 */
package org.net9.minipie.app.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.xml.TagBean;
import org.net9.minipie.app.client.xml.PersonBean;

/**
 * @author LeeThree
 * 
 */
public class Session {

	private Backend backend;
	private PersonBean profile;
	private Map<Long, PersonBean> userContacts;
	private Collection<PersonBean> userContactList;
	private Map<Long, PersonBean> contacts;
	private Collection<PersonBean> contactList;
	private Collection<TagBean> tagList;

	public Session(String username, String password) throws GenericException {
		backend = new Backend(username, password);
		userContacts = new HashMap<Long, PersonBean>();
		contacts = new HashMap<Long, PersonBean>();
		initialize();
	}

	public PersonBean getProfile() {
		return profile;
	}

	public Collection<PersonBean> listUserContacts() {
		return userContactList;
	}

	public Collection<PersonBean> listContacts() {
		return contactList;
	}

	public Collection<TagBean> listTags() {
		return tagList;
	}
	
	public PersonBean viewUserContact(long userId){
		PersonBean user = userContacts.get(userId);
		if (user == null){}
			// TODO get user
		return user;
	}

	private void initialize() throws GenericException {
		profile = backend.getProfile();
		// TODO
	}
}
