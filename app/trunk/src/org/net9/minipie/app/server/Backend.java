/**
 * 
 */
package org.net9.minipie.app.server;

import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.net9.minipie.app.client.exception.GenericException;
import org.net9.minipie.app.client.xml.PersonBean;
import org.net9.minipie.app.server.util.CredentialEncoder;
import org.net9.minipie.app.server.xml.PersonBeanImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author LeeThree
 * 
 */
public class Backend {
	private static final String AUTHENTICATION_HEADER = "Authorization";
	private static final String SERVICE_ROOT_URL = "http://li3.net9.org:8088/Mini-Pie/services/";
	private Client client;
	private WebResource rootResource;
	private String credential;

	public Backend(String username, String password) {
		credential = "Basic "
				+ CredentialEncoder.encodeBasic(username, password);
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		rootResource = client.resource(SERVICE_ROOT_URL);
		//getProfile();
	}

	public PersonBean getProfile() throws GenericException {
		try {
			InputStream stream = getXml("profile");
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBeanImpl(ele).getBean();
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	public PersonBean getUserById(long id) throws GenericException {
		try {
			InputStream stream = getXml("phonebook/user/" + id);
			Document doc = new SAXReader().read(stream);
			Element ele = doc.getRootElement();
			return new PersonBeanImpl(ele).getBean();
		} catch (Exception e) {
			throw new GenericException(e);
		}
	}

	private InputStream getXml(String path) {
		return rootResource.path(path).accept(MediaType.APPLICATION_XML_TYPE)
				.header(AUTHENTICATION_HEADER, credential).get(
						InputStream.class);
	}
}
