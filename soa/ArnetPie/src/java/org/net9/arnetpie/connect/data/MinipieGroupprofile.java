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
@XmlRootElement(name = "group")
public class MinipieGroupprofile {

    @XmlAttribute(name = "id")
    public long id;

    @XmlElement(name = "name")
    public String name;

    @XmlElement(name = "description")
    public String description;

    @XmlElement(name = "creatorid")
    public String creatorid;

    public MinipieGroupprofile() {

    }
}
