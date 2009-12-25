/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net9.arnetpie.process;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import org.net9.arnetpie.connect.UserprofileResource;
import org.net9.arnetpie.connect.data.MinipieUserprofile;
import org.net9.arnetpie.data.Userprofile;

/**
 *
 * @author Administrator
 */
@Stateless
public class UserprofileController extends BaseController {

     public Userprofile getMyProfile(String token) {
         MinipieUserprofile mpup = new UserprofileResource(token).getProfile();
         
         Userprofile up = new Userprofile();
         up.setName(mpup.name);
         up.setId((int)mpup.id);
         up.setUsername(mpup.username);
         up.setEmail(mpup.registeremail);
         up.setGender(mpup.gender);
         up.setBirthdate(mpup.birthday);
         up.setOrganization("THU");
         up.setTitle("M.E.");
         up.setInterests("Computer Achitecture");

        return up;
     }

     public Userprofile getUserProfile(String token) {
         MinipieUserprofile mpup = new UserprofileResource(token).getProfile();

         Userprofile up = new Userprofile();
         up.setName(mpup.name);
         up.setId((int)mpup.id);
         up.setUsername(mpup.username);
         up.setGender(mpup.gender);
         up.setBirthdate(mpup.birthday);
         up.setOrganization("THU");
         up.setTitle("M.E.");
         up.setInterests("Computer Achitecture");

        return up;
     }
}
