/**
 * 
 */
package org.net9.minipie.app.server;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.exception.BackendConnectionException;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.exception.LoginFailedException;
import org.net9.minipie.app.server.util.CredentialEncoder;
import org.net9.minipie.app.server.xml.PersonBeanImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author LeeThree
 * 
 */
public class Backend {
	private static final String AUTHENTICATION_HEADER = "Authorization";
	private static final String SERVICE_ROOT_URL = "http://li3.net9.org:8088/Mini-Pie/";
	private static final String SERVICE_API_URL = SERVICE_ROOT_URL
			+ "services/";
	private Client client;
	private WebResource rootResource;
	private String credential;

	public Backend(String username, String password) {
		credential = "Basic "
				+ CredentialEncoder.encodeBasic(username, password);
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		rootResource = client.resource(SERVICE_API_URL);
	}

	public PersonBean getProfile() throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("profile");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBeanImpl(ele).getBean();
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public List<PersonBean> listUserContacts() throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/user");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			List<PersonBean> list = new ArrayList<PersonBean>();
			for (Object iter : ele.elements()) {
				Element elem = (Element) iter;
				list.add(new PersonBeanImpl(elem).getBean());
			}
			return list;
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public List<PersonBean> listContacts() throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/contact");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			List<PersonBean> list = new ArrayList<PersonBean>();
			for (Object iter : ele.elements()) {
				Element elem = (Element) iter;
				list.add(new PersonBeanImpl(elem).getBean());
			}
			return list;
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public PersonBean getUserById(long id) throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/user/" + id);
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBeanImpl(ele).getBean();
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public PersonBean getContactById(long id) throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/contact/" + id);
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBeanImpl(ele).getBean();
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public static void testConnection() throws GenericException,
			BackendConnectionException {
		try {
			Client.create().resource(SERVICE_ROOT_URL).get(String.class);
		} catch (UniformInterfaceException e) {
			throw new GenericException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BackendConnectionException();
		}
	}

	private InputStream getXml(String path) {
		return rootResource.path(path).accept(MediaType.APPLICATION_XML_TYPE)
				.header(AUTHENTICATION_HEADER, credential).get(
						InputStream.class);
	}
}
