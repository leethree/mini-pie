/**
 * Person.java
 *     in package: * org.net9.minipie.server.logic.operation.util.entitycube
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util.entitycube;

/**
 * @author Riversand
 * 
 */
public class Person implements Comparable<Person> {

	private long id;

	/**
	 * Constructor
	 */
	public Person() {
	}

	public Person(long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Person person) {
		return (int) (this.id - person.id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o instanceof Person)
			return ((Person) o).id == this.id;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + id;
	}
}
