/**
 * Renlifang.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import java.util.List;

import org.net9.minipie.server.logic.operation.util.entitycube.RelationGraph;
import org.net9.minipie.server.logic.operation.util.entitycube.Relationship;

/**
 * @author Riversand
 *
 */
public class Renlifang {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RelationGraph graph = new RelationGraph();
		graph.buildGraph();
//		List<Relationship> results = graph.doEntityCubeSearch(20, 11);
		List<Relationship> results = graph.doPersonalSearch(10);
		for (Relationship relationship : results) {
			System.out.println(relationship.getPerson1().getId()+" "+relationship.getPerson2().getId());
		}
		
	}

}
