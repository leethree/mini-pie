/**
 * NotifacationType.java
 *     in package: * org.net9.minipie.server.data.field
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

/**
 * @author Seastar
 * 
 */

public enum NotificationType {
	RELATIONSHIP("relationship"), CONTACT_APPLICATION("contact_application"), MEMBERSHIP_APPLICATION(
			"membership_application"), MEMBERSHIP_INVITATION(
			"membership_invitation");
	private String type;

	private NotificationType(String type) {
		this.type = type;
	}

	public String toString() {
		return this.type;
	}
}
