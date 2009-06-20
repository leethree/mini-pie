/**
 * SignUp.java
 *     in package: * org.net9.minipie.server.logic.operation
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 * 
 */
public class SignUp extends Command<Long> {
	private String name;
	private String pwd;
	private String email;
	private String displayName;
	
	public SignUp(String name,String displayNmae ,String email, String password) {
		setName(name);
		setEmail(email);
		setPwd(password);
		setDisplayName(displayName);
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		if (email == null)
			throw new InvalidRequestException(
					"The registerd email should not be null.");
		try {
			this.email = Formatter.formatEmail(email);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null)
			throw new InvalidRequestException("The name should not be null.");
		this.name = Formatter.compact(name);
	}

	public void setDisplayName(String disName) {
		if (displayName == null)
			throw new InvalidRequestException("The displayName should not be null.");
		this.displayName = Formatter.compact(disName);
	}

	
	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		if (pwd == null)
			throw new InvalidRequestException(
					"The password should not be null.");
		try {
			this.pwd = Formatter.formatPassword(pwd);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#excute()
	 */
	public Long execute() {
		UserStorage executor = getStorageFactory().getUserStorage();
		return executor.add(name,displayName, pwd, email);
	}

}
