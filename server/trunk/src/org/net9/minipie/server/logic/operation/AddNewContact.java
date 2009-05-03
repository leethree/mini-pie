/**
 * AddNewContact.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;


/**
 * @author Seastar
 *
 */
public class AddNewContact extends Command<Long>{
	private String name;
	private Long userId;
	/**
	 * Constructor
	 */
	public AddNewContact() {
		// TODO Auto-generated constructor stub
	}
	public AddNewContact(String name,Long id){
		setName(name);
		setUserId(id);
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		if(userId<0){
			//TODO:Exception
		}
		this.userId = userId;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(name==null){
			return;
		}
		name = name.trim();
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.Command#excute()
	 */
	@Override
	public void excute() {
		// TODO Auto-generated method stub
		System.out.print(name+userId);
		returnValue=0L;
	}

}
