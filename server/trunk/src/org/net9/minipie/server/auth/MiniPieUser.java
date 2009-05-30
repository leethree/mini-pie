/**
 * MiniPieUser.java
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
public class MiniPieUser implements Principal, java.io.Serializable {

	private String name;
	private long id;

	public MiniPieUser(String name, long id) {
		if (name == null || id <= 0)
			throw new NullPointerException("illegal null input");

		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public String toString() {
		return ("MiniPieUser:  " + name);
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!(o instanceof MiniPieUser))
			return false;
		MiniPieUser that = (MiniPieUser) o;
		if (this.getName().equals(that.getName()))
			return true;
		return false;
	}

	public int hashCode() {
		return name.hashCode();
	}
}
