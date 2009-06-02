/**
 * Formatter.java
 *     in package: * org.net9.minipie.server.data
 * by Mini-Pie Project
 */
package org.net9.minipie.server.data;

import java.net.URI;
import java.net.URISyntaxException;

import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author LeeThree
 * 
 */
public final class Formatter {
	// only lower-case extension .jpg, .gif & .png are allowed
	private static final String IMAGE_REGEX = "([^\\s]+(?=\\.(jpg|gif|png))\\.\\2)";

	// letters, digits, underlines, dots before @
	private static final String EMAIL_REGEX = "(\\w[\\w\\.]+@[0-9a-zA-Z]+?(\\.[0-9a-zA-Z]{2,10})+)";

	// any character except \n, length between 6, 20
	private static final String PASSWORD_REGEX = "(.{6,20})";

	// start with + or letter or digit, allow hyphens
	private static final String PHONE_REGEX = "(\\+?[0-9A-Z]+(-[0-9A-Z]+)*)";

	/**
	 * trim and kill new-lines
	 * 
	 * @param s
	 * @return
	 */
	public static String compact(String s) {
		return s.trim().replaceAll("\\s+", " ");
	}

	/**
	 * remove whitespaces and to lower case
	 * 
	 * @param s
	 * @return
	 */
	public static String removeSpace(String s) {
		return s.trim().replaceAll("\\s", "").toLowerCase();
	}

	/**
	 * check if an ID is valid (0 is not allowed)
	 * 
	 * @param id
	 * @return
	 * @throws DataFormatException
	 */
	public static long checkId(long id) throws DataFormatException {
		if (id <= 0) {
			throw new DataFormatException("Invalid ID: " + id + ".");
		}
		return id;
	}
	
	/**
	 * check if an ID is valid (0 is allowed)
	 * 
	 * @param id
	 * @return
	 * @throws DataFormatException
	 */
	public static long checkNullableId(long id) throws DataFormatException {
		if (id < 0) {
			throw new DataFormatException("Invalid ID: " + id + ".");
		}
		return id;
	}

	/**
	 * check if a URI is valid
	 * 
	 * @param uri
	 * @return
	 * @throws DataFormatException
	 */
	public static String formatImage(String image) throws DataFormatException {
		URI uri = formatUri(image.trim());
		if (!uri.getPath().matches(IMAGE_REGEX)) {
			throw new DataFormatException("Invalid image URI: " + image + ".");
		}
		return uri.toASCIIString();
	}

	/**
	 * check if a Email is valid
	 * 
	 * @param email
	 * @return
	 * @throws DataFormatException
	 */
	public static String formatEmail(String email) throws DataFormatException {
		if (!email.trim().matches(EMAIL_REGEX)) {
			throw new DataFormatException("Invalid e-mail: " + email + ".");
		}
		return email.trim().toLowerCase();
	}

	/**
	 * check if a Email is valid
	 * 
	 * @param email
	 * @return
	 * @throws DataFormatException
	 */
	public static String formatPassword(String password)
			throws DataFormatException {
		if (!password.matches(PASSWORD_REGEX)) {
			throw new DataFormatException("Invalid password: " + password + ".");
		}
		return password;
	}

	/**
	 * check if a Email is valid
	 * 
	 * @param email
	 * @return
	 * @throws DataFormatException
	 */
	public static String formatPhone(String phone) throws DataFormatException {
		String formatted = phone.trim().toUpperCase();
		boolean plus = formatted.startsWith("+");
		formatted = formatted.replaceAll("[^0-9A-Z]+", " ").trim().replace(' ', '-');
		if (plus)
			formatted = "+" + formatted;
		if (!formatted.matches(PHONE_REGEX)) {
			throw new DataFormatException("Invalid phone number: " + phone
					+ ".");
		}
		return formatted;
	}

	/**
	 * make URI from a string
	 * 
	 * @param uriStr
	 * @return
	 * @throws DataFormatException
	 */
	public static URI formatUri(String uriStr) throws DataFormatException {
		try {
			URI uri = new URI(uriStr.trim());
			return uri.normalize();
		} catch (URISyntaxException e) {
			throw new DataFormatException("Invalid URI: " + uriStr + ".");
		}
	}
}
