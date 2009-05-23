/**
 * ListTags.java
 *     in package: * org.net9.minipie.server.logic.operation.tag
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.tag;

import java.util.ArrayList;
import java.util.Collection;

import org.net9.minipie.server.data.api.TagXml;
import org.net9.minipie.server.data.entity.TagEntry;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.TagStorage;

/**
 * @author Seastar
 * 
 */
public class ListAllTags extends Command<Collection<TagXml>> {
	private long userId;

	public ListAllTags(long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Collection<TagXml> execute() {
		TagStorage executor = getStorageFactory().getTagStorage();
		Collection<TagEntry> list = executor.selectAllTags(userId);
		Collection<TagXml> collection = new ArrayList<TagXml>();
		for (TagEntry tagEntry : list) {
			collection.add(new TagXml(tagEntry));
		}
		return collection;
	}

}
