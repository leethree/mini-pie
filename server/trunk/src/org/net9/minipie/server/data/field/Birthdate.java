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
	private static final String SIMPLE_DATE_PATTERN = "M-d";
	private static final SimpleDateFormat SIMPLE_DATE_FORMATTER = new SimpleDateFormat(
			SIMPLE_DATE_PATTERN);
	private boolean showyear;
	
	/**
	 * Constructor
	 */
	private Birthdate() {
	}
	
	/**
	 * Constructor
	 * 
	 * @throws DataFormatException 
	 */
	public Birthdate(String dateStr) throws DataFormatException {
		try {
			date = DATE_FORMATTER.parse(dateStr);
			showyear = true;
		} catch (ParseException e) {
			try {
				date = SIMPLE_DATE_FORMATTER.parse(dateStr);
				showyear = false;
			} catch (ParseException e2) {
				throw new DataFormatException("Invalid date: " + dateStr
						+ ", pattern: " + DATE_PATTERN);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (showyear)
			return DATE_FORMATTER.format(date);
		else
			return SIMPLE_DATE_FORMATTER.format(date);
	}
	public Birthdate toSimpleDate(){
		Birthdate bd = new Birthdate();
		bd.date = this.date;
		bd.showyear = false;
		return bd;
	}
}
