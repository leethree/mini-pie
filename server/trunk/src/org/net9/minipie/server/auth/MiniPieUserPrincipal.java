/**
 * MiniPieUserPrincipal.java
 *     in package: * org.net9.minipie.server.auth
 * by Mini-Pie Project
 */
package org.net9.minipie.server.auth;

/**
 * @author ºî½Ü
 *
 */

import java.security.Principal;
public class MiniPieUserPrincipal implements Principal, java.io.Serializable{
	
	private String name;

	
	public MiniPieUserPrincipal(String name) {
		if (name == null)
			throw new NullPointerException("illegal null input");

		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	
	public String toString() {
		return ("MiniPieUserPrincipal:  " + name);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this == o)
			return true;

		if (!(o instanceof MiniPieUserPrincipal))
			return false;
		MiniPieUserPrincipal that = (MiniPieUserPrincipal) o;

		if (this.getName().equals(that.getName()))
			return true;
		return false;
	}

	
	public int hashCode() {
		return name.hashCode();
	}
}
