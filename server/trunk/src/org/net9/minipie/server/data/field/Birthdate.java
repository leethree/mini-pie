/**
 * Birthdate.java
 *     in package: * org.net9.minipie.server.data.entity
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author LeeThree
 * 
 */
public class Birthdate {
	private Date date;
	private static final String DATE_PATTERN = "yyyy-M-d";
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
			DATE_PATTERN);

	/**
	 * Constructor
	 * 
	 * @throws DataFormatException 
	 */
	public Birthdate(String dateStr) throws DataFormatException {
		try {
			date = DATE_FORMATTER.parse(dateStr);
		} catch (ParseException e) {
			throw new DataFormatException("Invalid date: " + dateStr
					+ ", pattern: " + DATE_PATTERN);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return DATE_FORMATTER.format(date);
	}
	public Birthdate toSimpleDate(){
		//TODO:return the date and month format
		return this;
	}
}
