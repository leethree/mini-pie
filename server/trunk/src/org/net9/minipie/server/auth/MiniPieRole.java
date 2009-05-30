/**
 * MiniPieRole.java
 *     in package: * org.net9.minipie.server.auth.principal
 * by Mini-Pie Project
 */
package org.net9.minipie.server.auth;

import java.security.Principal;

/**
 * @author SoulCircus, LeeThree
 * 
 */
@SuppressWarnings("serial")
public class MiniPieRole implements Principal, java.io.Serializable {

	public static final MiniPieRole MINIPIE_USER = new MiniPieRole(
			"MiniPieUser");

	private String role;

	public MiniPieRole(String role) {
		if (role == null)
			throw new NullPointerException("illegal null input");

		this.role = new String(role);
	}

	public String getName() {
		return role;
	}

	public String toString() {
		return ("MiniPieRole:  " + role);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!(o instanceof MiniPieRole))
			return false;
		MiniPieRole that = (MiniPieRole) o;

		if (this.getName().equals(that.getName()))
			return true;
		return false;
	}

	public int hashCode() {
		return role.hashCode();
	}
}
