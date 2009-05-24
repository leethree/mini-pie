/**
 * StorageFactory.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

/**
 * @author LeeThree,Seastar
 * 
 */
public interface StorageFactory {
	public ContactStorage getContactStorage();
	public TagStorage getTagStorage();
	public User_UserStorage getUser_UserStorage();
	public UserStorage getUserStorage();
	public Tag_UserStorage getTag_UserStorage();
	public Tag_ContactStorage getTag_ContactStorage();
	public NotificationStorage getNotifacationStorage();
	public Group_UserStorage getGroup_UserStorage();
	public GroupStorage getGroupStorage();
}
