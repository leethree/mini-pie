/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net9.arnetpie.connect;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.net9.arnetpie.connect.data.MinipieUserprofile;

/**
 *
 * @author Administrator
 */
public class UserprofileResource {

    private Client client;

    private WebResource resource;

    private String token;

    public UserprofileResource(String token) {
        client = new Client();
        resource = client.resource("http://minipie.net9.org:8080/Mini-Pie/services/profile");
        this.token = token;
    }

    public MinipieUserprofile getProfile() {
        return resource.accept(MediaType.APPLICATION_XML_TYPE)
                .header(HttpHeaders.AUTHORIZATION, token).get(MinipieUserprofile.class);
    }

}
