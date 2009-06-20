/**
 * RelationGraph.java
 *     in package: * org.net9.minipie.server.logic.operation.util.entitycube
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util.entitycube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.User_UserStorage;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Hypergraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * @author Riversand
 * 
 */
public class RelationGraph extends Command<Void> {

	private Collection<Person> people = new TreeSet<Person>();
	private Collection<Relationship> relations = new TreeSet<Relationship>();
	private Hypergraph<Person, Relationship> graph = new DirectedSparseGraph<Person, Relationship>();
	public DijkstraShortestPath<Person, Relationship> renlifang;

	/**
	 * Constructor
	 */
	public RelationGraph() {
	}

	/**
	 * @return the people
	 */
	public Collection<Person> getPeople() {
		return people;
	}

	/**
	 * @param people
	 *            the people to set
	 */
	public void setPeople(Collection<Person> people) {
		this.people = people;
	}

	/**
	 * @return the relations
	 */
	public Collection<Relationship> getRelations() {
		return relations;
	}

	/**
	 * @param relations
	 *            the relations to set
	 */
	public void setRelations(Collection<Relationship> relations) {
		this.relations = relations;
	}

	/**
	 * @return the graph
	 */
	public Hypergraph<Person, Relationship> getGraph() {
		return graph;
	}

	/**
	 * @param graph
	 *            the graph to set
	 */
	public void setGraph(Hypergraph<Person, Relationship> graph) {
		this.graph = graph;
	}

	public void buildGraph() {
		User_UserStorage uust = getStorageFactory().getUser_UserStorage();
		for (Map.Entry<Long, Long> bind : uust.getRelations().entrySet()) {
			addRelationship(bind.getKey(), bind.getValue());
			addRelationship(bind.getValue(), bind.getKey());
			Person person1 = new Person(bind.getKey());
			Person person2 = new Person(bind.getValue());
			if (!graph.getVertices().contains(person1)) {
				graph.addVertex(person1);
			}
			if (!graph.containsVertex(person2)) {
				graph.addVertex(person2);
			}
			System.out.println(graph.getVertices());
			System.out.println(person1.getId() + " " + person2.getId());
			Relationship relation1 = new Relationship(person1, person2);
			graph.addEdge(relation1, new Pair<Person>(relation1.getPerson1(),
					relation1.getPerson2()), EdgeType.DIRECTED);

			Relationship relation2 = new Relationship(person2, person1);
			graph.addEdge(relation2, new Pair<Person>(relation2.getPerson2(),
					relation2.getPerson1()), EdgeType.DIRECTED);
		}
		renlifang = new DijkstraShortestPath<Person, Relationship>(
				(Graph<Person, Relationship>) graph);
	}

	public void addRelationship(long id1, long id2) {
		relations.add(new Relationship(new Person(id1), new Person(id2)));
	}

	public List<Relationship> doEntityCubeSearch(long id1, long id2) {
		Collection<Person> people = graph.getVertices();
		Person person1 = null, person2 = null;
		for (Person person : people) {
			System.out.println(person.getId());
			if (person.getId() == id1)
				person1 = person;
			else if (person.getId() == id2)
				person2 = person;
		}
		return renlifang.getPath(person1, person2);
	}

	public List<Relationship> doPersonalSearch(long id) {
		Collection<Relationship> outRelations = graph.getOutEdges(new Person(id));
		List<Relationship> results = new ArrayList<Relationship>();
		for (Relationship relationship : outRelations) {
			results.add(relationship);
		}
		Collection<Relationship> inRelations = graph.getInEdges(new Person(id));
		for (Relationship relationship : inRelations) {
			results.add(relationship);
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		buildGraph();
		return null;
	}

}
