/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net9.arnetpie.connect.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "profile")
public class MinipieUserprofile {

    @XmlAttribute(name = "id", required = true)
    public long id;

    @XmlElement(name = "name")
    public String name;

    @XmlElement(name = "birthday")
    public String birthday;

    @XmlElement(name = "gender")
    public String gender;
    
    @XmlElement(name = "register_email")
    public String registeremail;

    @XmlElement(name = "username")
    public String username;

    public MinipieUserprofile() {

    }

}
