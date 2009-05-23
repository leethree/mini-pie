/**
 * ListTags.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import java.util.Collection;

import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;

/**
 * @author Seastar
 *
 */
public class ListAllTags extends Command<Collection<TagEntry>> {
	private long userId;
	
	public ListAllTags(long userId){
		this.userId=userId;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<TagEntry> execute() {
		TagStorage executor=getStorageFactory().getTagStorage();		
		return executor.selectAllTags(userId);
	}

}
