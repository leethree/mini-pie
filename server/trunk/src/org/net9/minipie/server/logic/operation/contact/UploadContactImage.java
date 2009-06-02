/**
 * UploadImage.java
 *     in package: * org.net9.minipie.server.logic.operation.contact
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.contact;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.net9.minipie.server.data.field.InfoField;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.exception.InvalidRequestException;
import org.net9.minipie.server.exception.NotFoundException;
import org.net9.minipie.server.exception.PermissionDeniedException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.ContactStorage;

/**
 * @author Seastar
 * 
 */
public class UploadContactImage extends Command<String> {
	private Long contactId;
	private Long userId;
	private InputStream in;
	private String filePath;
	private URI urlPath;
	private String fileName;
	private String exName;

	public UploadContactImage(Long userId, Long contactId, InputStream in,
			String filePath, URI urlPath, String exName) {
		super();
		setContactId(contactId);
		setUserId(userId);
		this.in = in;
		this.filePath = filePath;
		this.urlPath = urlPath;
		this.exName = exName;
		// this.setFileName(fileName);
	}

	/**
	 * @param contactid
	 *            the contactId to set
	 */
	public void setContactId(Long contactId) {
		if (contactId < 0) {
			throw new InvalidRequestException("id is illegal");
		}
		this.contactId = contactId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setFileName(String fileName) {
		fileName = fileName.toLowerCase();
		int i = fileName.indexOf(".");
		if (i == -1)
			throw new InvalidRequestException("image format illegal");
		exName = fileName.substring(i);
		if (!exName.equals(".jpg") && !exName.equals(".png")
				&& !exName.equals("gif"))
			throw new InvalidRequestException("image format illegal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		ContactStorage executor = getStorageFactory().getContactStorage();
		ContactEntity contact = executor.selectBasicInfo(contactId).getEntity();
		if (contact.getOwnerId() != userId) {
			throw new PermissionDeniedException(
					"This contact doesn't belong to the user");
		} else if (contact.getShadowOf() != 0) {
			throw new NotFoundException("No contact found");
		}
		try {
			fileName = userId + "_" + contactId + "." + exName;
			File tempFile = new File(filePath, fileName);
			FileOutputStream ou = new FileOutputStream(tempFile);
			byte[] buffer = new byte[2048];
			int byteread;
			while ((byteread = in.read(buffer)) != -1) {
				ou.write(buffer, 0, byteread);
			}
			ou.close();
			in.close();
			executor.editBasicInfo(contactId, InfoField.IMAGE, urlPath.resolve(
					fileName).toASCIIString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerErrorException("can't write the file");
		}
		return fileName;
	}

}
