/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net9.arnetpie.process;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.net9.arnetpie.data.Groupprofile;
import org.net9.arnetpie.data.Mail;
import org.net9.arnetpie.data.Notifications;
import org.net9.arnetpie.data.Pages;
import org.net9.arnetpie.data.Userprofile;

/**
 *
 * @author Administrator
 */
public class MyTest {

    public static void testWriteUserProfile(String location) {
        try {
            OutputStream os = new FileOutputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Userprofile.class);
            Marshaller mu = jc.createMarshaller();
            Userprofile up = new Userprofile();
            up.setId(1234);
            up.setUsername("reo");
            up.setName("Dante");
            up.setEmail("dante.nero@foxmail.com");
            up.setGender("male");
            up.setBirthdate("19880602");
            up.setOrganization("THU");
            up.setTitle("M.E.");
            up.setInterests("Computer Achitecture");
            mu.marshal(up, os);
            os.close();
        } catch (Exception e) {

        }
    }

    public static void testWriteGroupProfile(String location) {
        try {
            OutputStream os = new FileOutputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Groupprofile.class);
            Marshaller mu = jc.createMarshaller();
            Groupprofile gp = new Groupprofile();
            gp.setId(3721);
            gp.setName("Computer Science");
            gp.setInterests("Computer Science");
            gp.setDescription("Computer Science");
            gp.setAdminid(1234);
            gp.setMembernum(200);
            mu.marshal(gp, os);
            os.close();
        } catch (Exception e) {

        }
    }

    public static void testWritePages(String location) {
        try {
            OutputStream os = new FileOutputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Pages.class);
            Marshaller mu = jc.createMarshaller();
            Pages pa = new Pages();
            List<Pages.Pdf> pdf = pa.getPdf();
            List<Pages.WikiPage> wiki = pa.getWikiPage();
            Pages.Pdf pdf1 = new Pages.Pdf();
            Pages.Pdf pdf2 = new Pages.Pdf();
            Pages.WikiPage wiki1 = new Pages.WikiPage();
            Pages.WikiPage wiki2 = new Pages.WikiPage();
            pdf1.setId(123);
            pdf2.setId(456);
            wiki1.setTag("abcd");
            wiki2.setTag("efgh");
            pdf.add(pdf1);
            pdf.add(pdf2);
            wiki.add(wiki1);
            wiki.add(wiki2);
            mu.marshal(pa, os);
            os.close();
        } catch (Exception e) {

        }
    }

    public static void testWriteNotifications(String location) {
        try {
            OutputStream os = new FileOutputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Notifications.class);
            Marshaller mu = jc.createMarshaller();
            Notifications nos = new Notifications();
            List<Notifications.Notification> no = nos.getNotification();
            Notifications.Notification no1 = new Notifications.Notification();
            Notifications.Notification no2 = new Notifications.Notification();
            Notifications.Notification no3 = new Notifications.Notification();
            no1.setId(111);
            no1.setType("pendingmember");
            no1.setValue("Wants to join");
            no2.setId(222);
            no2.setType("invitation");
            no2.setValue("Wants to invite you");
            no3.setId(333);
            no3.setType("updatemsg");
            no3.setValue("Has updated");
            no.add(no1);
            no.add(no2);
            no.add(no3);
            mu.marshal(nos, os);
            os.close();
        } catch (Exception e) {

        }
    }

    public static void testWriteMail(String location) {
        try {
            OutputStream os = new FileOutputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Mail.class);
            Marshaller mu = jc.createMarshaller();
            Mail ma = new Mail();
            ma.setFrom("abc@gmail.com");
            ma.setTo("def@gmail.com");
            ma.setCc("hijk@gmail.com");
            ma.setBcc("lmn@gmail.com");
            ma.setSubject("Orz");
            ma.setContent("Admire");
            mu.marshal(ma, os);
            os.close();
        } catch (Exception e) {

        }
    }

    public static void testReadUserProfile(String location) {
        try {
            InputStream is = new FileInputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Userprofile.class);
            Unmarshaller um = jc.createUnmarshaller();
            Userprofile up = (Userprofile)um.unmarshal(is);
            is.close();
            System.out.println("************UserProfile2************");
            System.out.println(up.getId());
            System.out.println(up.getUsername());
            System.out.println(up.getName());
            System.out.println(up.getEmail());
            System.out.println(up.getGender());
            System.out.println(up.getBirthdate());
            System.out.println(up.getOrganization());
            System.out.println(up.getTitle());
            System.out.println(up.getInterests());
        } catch (Exception e) {

        }
    }

    public static void testReadGroupProfile(String location) {
        try {
            InputStream is = new FileInputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Groupprofile.class);
            Unmarshaller um = jc.createUnmarshaller();
            Groupprofile gp = (Groupprofile)um.unmarshal(is);
            is.close();
            System.out.println("************GroupProfile2************");
            System.out.println(gp.getId());
            System.out.println(gp.getName());
            System.out.println(gp.getInterests());
            System.out.println(gp.getDescription());
            System.out.println(gp.getAdminid());
            System.out.println(gp.getMembernum());
        } catch (Exception e) {

        }
    }

    public static void testReadPages(String location) {
        try {
            InputStream is = new FileInputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Pages.class);
            Unmarshaller um = jc.createUnmarshaller();
            Pages pa = (Pages)um.unmarshal(is);
            is.close();
            System.out.println("************Pages2************");
            List<Pages.Pdf> pdf = pa.getPdf();
            for (Pages.Pdf p: pdf) {
                System.out.println(p.getId());
            }
            List<Pages.WikiPage> wiki = pa.getWikiPage();
            for (Pages.WikiPage wi: wiki) {
                System.out.println(wi.getTag());
            }
        } catch (Exception e) {

        }
    }

    public static void testReadNotifications(String location) {
        try {
            InputStream is = new FileInputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Notifications.class);
            Unmarshaller um = jc.createUnmarshaller();
            Notifications nos = (Notifications)um.unmarshal(is);
            is.close();
            System.out.println("************Notifications2************");
            List<Notifications.Notification> no = nos.getNotification();
            for (Notifications.Notification n: no) {
                System.out.println(n.getId() + " " + n.getType() + " " + n.getValue());
            }
        } catch (Exception e) {

        }
    }

    public static void testReadMail(String location) {
        try {
            InputStream is = new FileInputStream(location);
            JAXBContext jc = JAXBContext.newInstance(Mail.class);
            Unmarshaller um = jc.createUnmarshaller();
            Mail ma = (Mail)um.unmarshal(is);
            is.close();
            System.out.println("************Mail2************");
            System.out.println(ma.getFrom());
            System.out.println(ma.getTo());
            System.out.println(ma.getCc());
            System.out.println(ma.getBcc());
            System.out.println(ma.getSubject());
            System.out.println(ma.getContent());
        } catch (Exception e) {

        }
    }

    public static void main(String args[]) {
        /*
        testWriteUserProfile("D:\\UserProfile1.xml");
        testWriteGroupProfile("D:\\GroupProfile1.xml");
        testWritePages("D:\\Pages1.xml");
        testWriteNotifications("D:\\Notifications1.xml");        
        testWriteMail("D:\\Mail1.xml");
            
        testReadUserProfile("D:\\UserProfile2.xml");
        testReadGroupProfile("D:\\GroupProfile2.xml");
        testReadPages("D:\\Pages2.xml");
        testReadNotifications("D:\\Notifications2.xml");           
        testReadMail("D:\\Mail2.xml");
         */

        //ClientConfig config = new DefaultClientConfig();
        Client client = new Client();
        WebResource rootResource = client.resource("http://minipie.net9.org:8080/Mini-Pie/services");
        String is = rootResource.path("profile").accept(MediaType.APPLICATION_XML_TYPE).get(String.class);
        System.out.println(is);

        try {
                     
        } catch (Exception e) {

        }
    }
}
