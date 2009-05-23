/**
 * AddTag.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;

/**
 * @author Seastar
 *
 */
public class CreateTag extends Command<Long> {
	private long userId;
	private String tagName;
	
	public CreateTag(long userId,String tagName){
		this.userId=userId;
		this.tagName=Formatter.compact(tagName);
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Long execute() {
		TagStorage executor=getStorageFactory().getTagStorage();
		try{
			executor.selectId(userId, tagName);
			throw new InvalidRequestException("the tag already exist");
		}catch (NotFoundException e){
			//well done!
		}				
		return executor.addTag(userId, tagName);
	}

}
