/**
 * Relationship.java
 *     in package: * org.net9.minipie.server.logic.operation.util.entitycube
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util.entitycube;

/**
 * @author Riversand
 * 
 */
public class Relationship implements Comparable<Relationship> {
	private Person person1;
	private Person person2;

	/**
	 * Constructor
	 */
	public Relationship() {
		person1 = new Person();
		person2 = new Person();
	}

	public Relationship(Person p1, Person p2) {
		this.person1 = p1;
		this.person2 = p2;
	}

	/**
	 * @return the person1
	 */
	public Person getPerson1() {
		return person1;
	}

	/**
	 * @return the person2
	 */
	public Person getPerson2() {
		return person2;
	}

	/**
	 * @param person1
	 *            the person1 to set
	 */
	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	/**
	 * @param person2
	 *            the person2 to set
	 */
	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Relationship rel) {
		if (person1.compareTo(rel.person1) == 0)
			return person2.compareTo(rel.person2);
		else
			return person1.compareTo(rel.person1);
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		return super.hashCode();
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		else if (o instanceof Relationship) {
			Relationship rel = (Relationship) o;
			return (this.person1.equals(rel.person1) && this.person2
					.equals(rel.person2));
		} else
			return false;
	}
}
