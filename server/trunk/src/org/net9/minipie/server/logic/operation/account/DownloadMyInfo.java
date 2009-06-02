/**
 * DownloadMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class DownloadMyInfo extends Command<String>{
	private long userId;
	private String filePath;
	private String urlPath;
	private String fileName;
	
	public DownloadMyInfo(long userId,String filePath,String urlPath){
		this.userId=userId;
		this.filePath=filePath;
		this.urlPath=urlPath;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		UserStorage executor = getStorageFactory().getUserStorage();	
		
		return null;
	}

}
