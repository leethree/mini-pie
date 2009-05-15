/**
 * Relationships.java
 *     in package: * org.net9.minipie.server.data.field
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

import java.util.HashSet;
import java.util.Set;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author LeeThree
 * 
 */
public class Relationships {
	private Set<Relationship> rels;

	/**
	 * Constructor
	 * 
	 * @param relationships
	 *            a relationship string
	 */
	public Relationships(String relationships) {
		rels = new HashSet<Relationship>(10);
		for (String rel : relationships.split("[\\s,:;]+")) {
			try {
				rels.add(Relationship.value(rel));
			} catch (DataFormatException e) {
				// just ignore
			}
		}
		validate();
	}

	/**
	 * check if the relationship is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return rels.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = new String();
		for (Relationship rel : rels) {
			str += rel.toString() + " ";
		}
		return str.trim();
	}

	/**
	 * check if the relationship is valid
	 */
	private void validate() {
		if (rels.contains(Relationship.ME)) {
			rels.clear();
			rels.add(Relationship.ME);
		}
		if (rels.contains(Relationship.FRIEND)) {
			rels.remove(Relationship.ACQUAINTANCE);
			rels.remove(Relationship.CONTACT);
		} else if (rels.contains(Relationship.ACQUAINTANCE)) {
			rels.remove(Relationship.CONTACT);
		}
		if (rels.contains(Relationship.CO_RESIDENT)) {
			rels.remove(Relationship.NEIGHBOR);
		}
		if (rels.contains(Relationship.CHILD)) {
			rels.remove(Relationship.PARENT);
			rels.remove(Relationship.SIBLING);
			rels.remove(Relationship.SPOUSE);
			rels.remove(Relationship.KIN);
		} else if (rels.contains(Relationship.PARENT)) {
			rels.remove(Relationship.SIBLING);
			rels.remove(Relationship.SPOUSE);
			rels.remove(Relationship.KIN);
		} else if (rels.contains(Relationship.SIBLING)) {
			rels.remove(Relationship.SPOUSE);
			rels.remove(Relationship.KIN);
		} else if (rels.contains(Relationship.SPOUSE)) {
			rels.remove(Relationship.KIN);
		}
	}

	private enum Relationship {
		ME("me"), FRIEND("friend"), ACQUAINTANCE("acquaintance"), CONTACT(
				"contact"), MET("met"), CO_WORKER("co-worker"), COLLEAGUE(
				"colleague"), CO_RESIDENT("co-resident"), NEIGHBOR("neighbor"), CHILD(
				"child"), PARENT("parent"), SIBLING("sibling"), SPOUSE("spouse"), KIN(
				"kin"), MUSE("muse"), CRUSH("crush"), DATE("date"), SWEETHEART(
				"sweetheart");
		private String rel;

		private Relationship(String rel) {
			this.rel = rel;
		}

		@Override
		public String toString() {
			return rel;
		}

		public static Relationship value(String field)
				throws DataFormatException {
			try {
				return valueOf(field.toUpperCase().replace('-', '_'));
			} catch (IllegalArgumentException e) {
				throw new DataFormatException("Invalid relationship: " + field
						+ ".");
			}
		}
	}
}
