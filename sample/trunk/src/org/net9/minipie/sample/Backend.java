/**
 * 
 */
package org.net9.minipie.sample;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.net9.minipie.sample.exception.BackendConnectionException;
import org.net9.minipie.sample.exception.GenericException;
import org.net9.minipie.sample.exception.LoginFailedException;
import org.net9.minipie.sample.exception.NotFoundException;
import org.net9.minipie.sample.util.CredentialEncoder;
import org.net9.minipie.sample.xml.PersonBean;
import org.net9.minipie.sample.xml.TagBean;
import org.net9.minipie.sample.xml.UpdateBean;

import com.ociweb.xml.WAX;
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
	private static final String SERVICE_ROOT_URL = "http://59.66.131.116:8088/Mini-Pie/";
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

//	public String getServiceBaseUrl() {
//		return SERVICE_API_URL;
//	}

	public PersonBean getProfile() throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("profile");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBean(ele);
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
				list.add(new PersonBean(elem));
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

	public List<PersonBean> listUserContactsWithTagId(long tagid)
			throws GenericException, LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/tag/" + tagid + "/user");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			List<PersonBean> list = new ArrayList<PersonBean>();
			for (Object iter : ele.elements()) {
				Element elem = (Element) iter;
				list.add(new PersonBean(elem));
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
				list.add(new PersonBean(elem));
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

	public List<PersonBean> listContactsWithTagId(long tagid)
			throws GenericException, LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/tag/" + tagid + "/contact");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			List<PersonBean> list = new ArrayList<PersonBean>();
			for (Object iter : ele.elements()) {
				Element elem = (Element) iter;
				list.add(new PersonBean(elem));
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

	public List<TagBean> listTags() throws GenericException,
			LoginFailedException {
		try {
			InputStream stream = getXml("phonebook/tag");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			List<TagBean> list = new ArrayList<TagBean>();
			for (Object iter : ele.elements()) {
				Element elem = (Element) iter;
				list.add(new TagBean(elem));
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
			LoginFailedException, NotFoundException {
		try {
			InputStream stream = getXml("phonebook/user/" + id);
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBean(ele);
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			if (e.getResponse().getStatus() == 404)
				throw new NotFoundException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public PersonBean getContactById(long id) throws GenericException,
			LoginFailedException, NotFoundException {
		try {
			InputStream stream = getXml("phonebook/contact/" + id);
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBean(ele);
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			if (e.getResponse().getStatus() == 404)
				throw new NotFoundException();
			throw new GenericException(e);
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public TagBean getTagById(long id) throws GenericException,
			LoginFailedException, NotFoundException {
		List<TagBean> list = listTags();
		for (TagBean tag : list) {
			if (tag.id == id)
				return tag;
		}
		throw new NotFoundException();
	}

	public void updateProfile(UpdateBean bean) throws LoginFailedException,
			GenericException, NotFoundException {
		try {
			StringWriter writer = new StringWriter();
			WAX wax = new WAX(writer);
			wax.start("updates");
			bean.toXML(wax);
			wax.close();
			postXml("profile/", writer.toString());
		} catch (UniformInterfaceException e) {
			if (e.getResponse().getStatus() == 401)
				throw new LoginFailedException();
			if (e.getResponse().getStatus() == 404)
				throw new NotFoundException();
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

	private void postXml(String path, String content) {
		System.out.println(content);
		rootResource.path(path).type(MediaType.APPLICATION_XML_TYPE).header(
				AUTHENTICATION_HEADER, credential).entity(content).post();
	}
}
