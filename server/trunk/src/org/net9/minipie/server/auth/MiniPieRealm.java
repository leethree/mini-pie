/**
 * MiniPieRealm.java
 *     in package: * org.net9.minipie.server.auth
 * by Mini-Pie Project
 */
package org.net9.minipie.server.auth;

/**
 * @author ºî½Ü
 *
 */

import java.security.Principal;
import org.apache.catalina.realm.*;
import org.net9.minipie.server.logic.IllegalUser;

public class MiniPieRealm extends RealmBase{
	
	protected final String info = "org.net9.minipie.server.auth.MiniPieRealm/1.0";
	protected static final String name = "MiniPieRealm";
	
	public String getInfo(){
		return info;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(String username){
		if(username == null)
			return null;
		IllegalUser auth = new IllegalUser(username);
		String password = auth.getPassword();
		return password;
	}
	
	public Principal getPrincipal(String username){
		if(username == null)
			return null;
		IllegalUser auth = new IllegalUser(username);
		String password = auth.getPassword();
		if(password == null)
			return null;
		else
			return new GenericPrincipal(this, username, password);
	}
	
	public Principal authenticate(String username, String credentials){
		if(username == null || credentials == null)
			return null;
		IllegalUser auth = new IllegalUser(username, credentials);
		if(auth.isIllegalUser() == true)
			return null;
		else
			return new GenericPrincipal(this, username, credentials);
	}
}













