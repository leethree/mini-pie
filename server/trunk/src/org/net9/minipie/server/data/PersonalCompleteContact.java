/**
 * PersonalCompleteContact.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import java.util.Collection;
import org.net9.minipie.server.data.constant.Permission;

/**
 * @author Seastar
 *
 */
public class PersonalCompleteContact {
	private Collection<Tag> tags;
	private Permission perm;
	private CompleteContact completeContact;
	/**
	 * Constructor
	 */
	public PersonalCompleteContact() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the completeContact
	 */
	public CompleteContact getCompleteContact() {
		return completeContact;
	}
	/**
	 * @return the perm
	 */
	public Permission getPerm() {
		return perm;
	}
	/**
	 * @return the tags
	 */
	public Collection<Tag> getTags() {
		return tags;
	}
	/**
	 * @param completeContact the completeContact to set
	 */
	public void setCompleteContact(CompleteContact completeContact) {
		this.completeContact = completeContact;
	}
	/**
	 * @param perm the perm to set
	 */
	public void setPerm(Permission perm) {
		this.perm = perm;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
}
