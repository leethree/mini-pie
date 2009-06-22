/**
 * ShareContact.java
 *     in package: * org.net9.minipie.server.logic.operation.contact
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import org.net9.minipie.server.data.Formatter;
import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.field.Permission;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 *
 */
public class ShareContact extends Command<Void> {
	
	private long userId;
	private long targetId;
	private Permission perm;
	
	public ShareContact(long userId,long targetId,String perm){
		this.userId=userId;
		try {
			this.targetId=Formatter.checkId(targetId);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
		try {
			this.perm=Permission.value(perm);
		} catch (DataFormatException e) {
			throw new InvalidRequestException(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public Void execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		try{
			if(executor.selectBasicInfo(targetId).getEntity().getOwnerId()!=userId){
				throw new PermissionDeniedException("not your contact");
			}else{
				executor.editBasicInfo(targetId, InfoField.PERMISSION, perm.toString());
			}
		}catch(NotFoundException e){
			throw new InvalidRequestException("no such contact");
		}
		return null;
	}

}
