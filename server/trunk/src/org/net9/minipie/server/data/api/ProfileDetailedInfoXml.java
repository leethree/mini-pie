/**
 * DetailedInfo.java
 *     in package: * org.net9.minipie.server.api.xml
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.api;

import org.net9.minipie.server.data.entity.Info;


/**
 * @author LeeThree
 * 
 */
public interface ProfileDetailedInfoXml {
	public Info getInfo();
	/**
	 * Check data consistency
	 * @return this
	 */
	public ProfileDetailedInfoXml checkThis();
}
