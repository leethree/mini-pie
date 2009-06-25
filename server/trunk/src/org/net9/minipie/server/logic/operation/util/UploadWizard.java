/**
 * UploadWizard.java
 *     in package: * org.net9.minipie.server.logic.operation.util
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.ContactEntity;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.data.field.Birthdate;
import org.net9.minipie.server.data.field.Gender;
import org.net9.minipie.server.data.field.InfoType;
import org.net9.minipie.server.exception.DataFormatException;

/**
 * @author Riversand
 * 
 */
public class UploadWizard {

	private static class LineProperty {
		int lineNumber;
		boolean isMyProfile;
		int contactId;
		InfoType infoType;

		/**
		 * Constructor
		 */
		public LineProperty(int ln, boolean b, int contactId, InfoType type) {
			this.lineNumber = ln;
			this.infoType = type;
			this.contactId = contactId;
			this.isMyProfile = b;
		}

		/**
		 * @return the infoType
		 */
		public InfoType getInfoType() {
			return infoType;
		}

		/**
		 * @return the contactId
		 */
		public int getContactId() {
			return contactId;
		}

		/**
		 * @return the isMyProfile
		 */
		public boolean isMyProfile() {
			return isMyProfile;
		}

		/**
		 * @return the lineNumber
		 */
		public int getLineNumber() {
			return lineNumber;
		}
	}

	private Scanner scanner;
	private Map<Integer, ContactEntity> importedContacts = new HashMap<Integer, ContactEntity>();
	private UserEntity myProfile = new UserEntity();

	/**
	 * Constructor
	 */
	public UploadWizard() {
	}

	/**
	 * Constructor
	 */
	public UploadWizard(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * @return the scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/**
	 * @return the importedContacts
	 */
	public Collection<ContactEntity> getImportedContacts() {
		Set<ContactEntity> result = new HashSet<ContactEntity>();
		for (Map.Entry<Integer, ContactEntity> contactEntity : importedContacts
				.entrySet()) {
			result.add(contactEntity.getValue());
		}
		return result;
	}

	/**
	 * @return the myProfile
	 */
	public UserEntity getMyProfile() {
		return myProfile;
	}

	public void upload(File f) {
		Map<LineProperty, String> lines = new HashMap<LineProperty, String>();
		LineProperty lineProperty = null;
		String text = null;
		boolean isMyProfile = true;
		int currentContactId = 0;
		try {
			scanner = new Scanner(f).useDelimiter("\n");
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				lineNum++;
				String line = scanner.nextLine().trim();
				line = line.replace("\\s+", " ");
				// System.out.println(line);
				if (line.startsWith("%%"))
					continue;
				else if (line.startsWith("profile=")) {
					isMyProfile = true;
					scanner.nextLine();
					lineNum++;
					String temp = scanner.nextLine().trim();
					// System.out.println(temp);
					lineNum++;
					lines.put(new LineProperty(lineNum, isMyProfile, 0,
							InfoType.BASIC), temp);
				} else if (line.startsWith("contactid=")) {
					isMyProfile = false;
					currentContactId = Integer.parseInt(line.substring(line
							.indexOf('=') + 1), 10);
					scanner.nextLine();
					lineNum++;
					String temp = scanner.nextLine().trim();
					// System.out.println(temp);
					lineNum++;
					lines.put(new LineProperty(lineNum, isMyProfile,
							currentContactId, InfoType.BASIC), temp);
				} else if (line.startsWith("addr=")) {
					int num = Integer.parseInt(line
							.substring(line.indexOf('=') + 1), 10);
					int k = 0;
					scanner.nextLine();
					lineNum++;
					while (scanner.hasNextLine() && k < num) {
						String temp = scanner.nextLine().trim();
						// System.out.println(temp);
						lineNum++;
						lines.put(new LineProperty(lineNum, isMyProfile,
								currentContactId, InfoType.ADDRESS), temp);
						k++;
					}
				} else if (line.startsWith("email=")) {
					int num = Integer.parseInt(line
							.substring(line.indexOf('=') + 1), 10);
					int k = 0;
					scanner.nextLine();
					lineNum++;
					while (scanner.hasNextLine() && k < num) {
						String temp = scanner.nextLine().trim();
						// System.out.println(temp);
						lineNum++;
						lines.put(new LineProperty(lineNum, isMyProfile,
								currentContactId, InfoType.EMAIL), temp);
						k++;
					}
				} else if (line.startsWith("im=")) {
					int num = Integer.parseInt(line
							.substring(line.indexOf('=') + 1), 10);
					int k = 0;
					scanner.nextLine();
					lineNum++;
					while (scanner.hasNextLine() && k < num) {
						String temp = scanner.nextLine().trim();
						// System.out.println(temp);
						lineNum++;
						lines.put(new LineProperty(lineNum, isMyProfile,
								currentContactId, InfoType.IM), temp);
						k++;
					}
				} else if (line.startsWith("tel=")) {
					int num = Integer.parseInt(line
							.substring(line.indexOf('=') + 1), 10);
					int k = 0;
					scanner.nextLine();
					lineNum++;
					while (scanner.hasNextLine() && k < num) {
						String temp = scanner.nextLine().trim();
						// System.out.println(temp);
						lineNum++;
						lines.put(new LineProperty(lineNum, isMyProfile,
								currentContactId, InfoType.PHONE), temp);
						k++;
					}
				} else if (line.startsWith("url=")) {
					int num = Integer.parseInt(line
							.substring(line.indexOf('=') + 1), 10);
					int k = 0;
					scanner.nextLine();
					lineNum++;
					while (scanner.hasNextLine() && k < num) {
						String temp = scanner.nextLine().trim();
						// System.out.println(temp);
						lineNum++;
						lines.put(new LineProperty(lineNum, isMyProfile,
								currentContactId, InfoType.URL), temp);
						k++;
					}
				}
			}
			for (Map.Entry<LineProperty, String> entry : lines.entrySet()) {
				Scanner subScanner = null;
				lineProperty = entry.getKey();
				text = entry.getValue();
				// System.out.println(entry.getKey().getLineNumber()+"
				// "+entry.getValue());
				if (lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.BASIC) {
					parseMyProfileInfo(entry, subScanner);
				} else if (lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.ADDRESS) {
					parseMyAddress(entry, subScanner);
				} else if (lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.EMAIL) {
					parseMyEmail(entry, subScanner);
				} else if (lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.IM) {
					parseMyIM(entry, subScanner);
				} else if (lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.PHONE) {
					parseMyPhone(entry, subScanner);
				} else if (lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.URL) {
					parseMyURL(entry, subScanner);
				} else if (!lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.BASIC) {
					parseContactBasic(entry, subScanner);
				} else if (!lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.ADDRESS) {
					parseContactAddress(entry, subScanner);
				} else if (!lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.EMAIL) {
					parseContactEmail(entry, subScanner);
				} else if (!lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.IM) {
					parseContactIM(entry, subScanner);
				} else if (!lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.PHONE) {
					parseContactPhone(entry, subScanner);
				} else if (!lineProperty.isMyProfile
						&& lineProperty.infoType == InfoType.URL) {
					parseContactURL(entry, subScanner);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("there is no such file.");
		} catch (DataFormatException e) {
			System.out.println("there is a data format error in line "
					+ lineProperty.getLineNumber());
			System.out.println(text);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseContactURL(Entry<LineProperty, String> entry,
			Scanner scanner) throws DataFormatException {
		URLData url = new URLData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			url.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				url.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				url.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			url.setValue(field);
		}
		int contactId = entry.getKey().getContactId();
		ContactEntity contact = importedContacts.get(new Integer(contactId));
		if (contact != null) {
			contact.getUrls().add(url);
		} else {
			contact = new ContactEntity();
			contact.getUrls().add(url);
			importedContacts.put(new Integer(entry.getKey().getContactId()),
					contact);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseContactPhone(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		PhoneNoData tel = new PhoneNoData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			tel.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				tel.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				tel.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			tel.setValue(field);
		}
		int contactId = entry.getKey().getContactId();
		ContactEntity contact = importedContacts.get(new Integer(contactId));
		if (contact != null) {
			contact.getTels().add(tel);
		} else {
			contact = new ContactEntity();
			contact.getTels().add(tel);
			importedContacts.put(new Integer(entry.getKey().getContactId()),
					contact);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseContactIM(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		IMData im = new IMData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			im.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				im.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				im.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			im.setValue(field);
		}
		int contactId = entry.getKey().getContactId();
		ContactEntity contact = importedContacts.get(new Integer(contactId));
		if (contact != null) {
			contact.getIms().add(im);
		} else {
			contact = new ContactEntity();
			contact.getIms().add(im);
			importedContacts.put(new Integer(entry.getKey().getContactId()),
					contact);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseContactEmail(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		EmailData email = new EmailData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			email.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				email.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				email.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			email.setValue(field);
		}
		int contactId = entry.getKey().getContactId();
		ContactEntity contact = importedContacts.get(new Integer(contactId));
		if (contact != null) {
			contact.getEmails().add(email);
		} else {
			contact = new ContactEntity();
			contact.getEmails().add(email);
			importedContacts.put(new Integer(entry.getKey().getContactId()),
					contact);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseContactAddress(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		AddressData addr = new AddressData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			addr.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				addr.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				addr.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			addr.setValue(field);
		}
		int contactId = entry.getKey().getContactId();
		ContactEntity contact = importedContacts.get(new Integer(contactId));
		if (contact != null) {
			contact.getAddrs().add(addr);
		} else {
			contact = new ContactEntity();
			contact.getAddrs().add(addr);
			importedContacts.put(new Integer(entry.getKey().getContactId()),
					contact);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseContactBasic(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		int contactId = entry.getKey().getContactId();
		ContactEntity contact = importedContacts.get(new Integer(contactId));
		if (contact == null) {
			contact = new ContactEntity();
			importedContacts.put(new Integer(entry.getKey().getContactId()),
					contact);
		}
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			field = field.replace("\"", "").trim();
			contact.setName(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			field = field.replace("\"", "").trim();
			contact.setNickName(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("male"))
				contact.setGender(Gender.MALE);
			else if (field.equalsIgnoreCase("female"))
				contact.setGender(Gender.FEMALE);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			field = field.replace("\"", "").trim();
			contact.setBirthday(new Birthdate(field));
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			field = field.replace("\"", "").trim();
			contact.setNotes(field);
		}
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseMyURL(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		URLData url = new URLData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			url.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				url.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				url.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			url.setValue(field);
		}
		myProfile.getUrls().add(url);
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseMyPhone(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		PhoneNoData tel = new PhoneNoData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			tel.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				tel.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				tel.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			tel.setValue(field);
		}
		myProfile.getTels().add(tel);
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseMyIM(Entry<LineProperty, String> entry, Scanner subScanner)
			throws DataFormatException {
		IMData im = new IMData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			im.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				im.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				im.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			im.setValue(field);
		}
		myProfile.getIms().add(im);
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseMyEmail(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		EmailData email = new EmailData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			email.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				email.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				email.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			email.setValue(field);
		}
		myProfile.getEmails().add(email);
	}

	/**
	 * @param entry
	 * @param subScanner
	 * @throws DataFormatException
	 */
	private void parseMyAddress(Entry<LineProperty, String> entry,
			Scanner subScanner) throws DataFormatException {
		AddressData addr = new AddressData();
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// type
			field = field.replace("\"", "").trim();
			addr.setType(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// prime
			field = field.replace("\"", "").trim();
			if (field.equalsIgnoreCase("true"))
				addr.setPrimary(true);
			else if (field.equalsIgnoreCase("false"))
				addr.setPrimary(false);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			field = field.replace("\"", "").trim();
			addr.setZipcode(field);
		}
		field = scanner.next();
		if (field.matches("\\s*\".*\"\\s*")) {
			// value
			field = field.replace("\"", "").trim();
			addr.setValue(field);
		}
		myProfile.getAddrs().add(addr);

	}

	void parseMyProfileInfo(Map.Entry<LineProperty, String> entry,
			Scanner scanner) throws DataFormatException {
		scanner = new Scanner(entry.getValue()).useDelimiter("::");
		String item = scanner.next();
		if (item.matches("\".*\".*")) {
			item = item.replace("\"", "").trim();
			myProfile.setName(item);
		}
		item = scanner.next();
		if (item.matches("\\s*\".*\"\\s*")) {
			item = item.replace("\"", "").trim();
			myProfile.setDisplayname(item);
		}
		item = scanner.next();
		if (item.matches("\\s*\".*\"\\s*")) {
			item = item.replace("\"", "").trim();
			myProfile.setNickName(item);
		}
		item = scanner.next();
		if (item.matches("\\s*\".*\"\\s*")) {
			item = item.replace("\"", "").trim();
			if (item.equalsIgnoreCase("male")) {
				myProfile.setGender(Gender.MALE);
			} else if (item.equalsIgnoreCase("female")) {
				myProfile.setGender(Gender.FEMALE);
			}
		}
		item = scanner.next();
		if (item.matches("\\s*\".*\"\\s*")) {
			item = item.replace("\"", "").trim();
			myProfile.setBirthday(new Birthdate(item));
		}
		item = scanner.next();
		if (item.matches("\\s*\".*\"\\s*")) {
			item = item.replace("\"", "").trim();
			myProfile.setRegisteredEmail(item);
		}
		item = scanner.next();
		if (item.matches("\\s*\".*\"\\s*")) {
			item = item.replace("\"", "").trim();
			myProfile.setNotes(item);
		}
	}

}
