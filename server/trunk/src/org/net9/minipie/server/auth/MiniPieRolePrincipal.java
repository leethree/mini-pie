/**
 * MiniPieRolePrincipal.java
 *     in package: * org.net9.minipie.server.auth
 * by Mini-Pie Project
 */
package org.net9.minipie.server.auth;

import java.security.Principal;

/**
 * @author ºî½Ü
 *
 */
public class MiniPieRolePrincipal implements Principal, java.io.Serializable{
	
	private String role;

	public MiniPieRolePrincipal(String role) {
		if (role == null)
			throw new NullPointerException("illegal null input");

		this.role = new String(role);
	}

	public String getName() {
		return role;
	}


	public String toString() {
		return ("MiniPieUserPrincipal:  " + role);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this == o)
			return true;

		if (!(o instanceof MiniPieRolePrincipal))
			return false;
		MiniPieRolePrincipal that = (MiniPieRolePrincipal) o;

		if (this.getName().equals(that.getName()))
			return true;
		return false;
	}

	
	public int hashCode() {
		return role.hashCode();
	}
}
