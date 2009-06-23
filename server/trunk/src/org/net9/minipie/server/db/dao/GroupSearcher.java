/**
 * GroupSearcher.java
 *     in package: * org.net9.minipie.server.db.dao
 * by Mini-Pie Project
 */
package org.net9.minipie.server.db.dao;

import org.hibernate.criterion.Restrictions;
import org.net9.minipie.server.data.storage.Query;

/**
 * @author Riversand
 *
 */
public class GroupSearcher extends BaseSearcher {
	
	/**
	 * Constructor
	 */
	public GroupSearcher(Query query) {
		super(query);
		parseQueryToCriterion();
		doSearch();
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.BaseSearcher#doSearch()
	 */
	@Override
	protected void doSearch() {
		GroupDAOHibernate gdh = new GroupDAOHibernate();
		groups = gdh.findByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see org.net9.minipie.server.db.dao.BaseSearcher#parseQueryToCriterion()
	 */
	@Override
	protected void parseQueryToCriterion() {
		String field = query.getField().toString();
		if(field.equals("groupName")){
			criterion = Restrictions.eq("groupName", query.getValue());
		}else if(field.equals("creatorName")){
			criterion = Restrictions.eq("creatorName", query.getValue());
		}else if(field.equals("creatorId")){
			criterion = Restrictions.eq("creatorId", Long.valueOf(query.getValue()));
		}
	}

}
