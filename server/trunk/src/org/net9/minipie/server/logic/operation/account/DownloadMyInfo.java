/**
 * DownloadMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import org.net9.minipie.server.logic.operation.Command;

/**
 * @author Seastar
 *
 */
public class DownloadMyInfo extends Command<String>{
	private long userId;
	
	public DownloadMyInfo(long userId){
		this.userId=userId;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
