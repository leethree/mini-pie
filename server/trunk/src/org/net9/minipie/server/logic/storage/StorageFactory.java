/**
 * StorageFactory.java
 *     in package: * org.net9.minipie.server.logic.storage
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.storage;

/**
 * @author LeeThree
 * 
 */
public interface StorageFactory {
	public ContactStorage getContactStorage();

	public UserStorage getUserStorage();
}
