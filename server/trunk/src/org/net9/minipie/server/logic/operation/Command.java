/**
 * Command.java
 *     in package: * org.net9.minipie.server.logic
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation;

import java.util.ResourceBundle;

import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.storage.StorageFactory;

/**
 * @author Seastar, LeeThree
 * 
 */
public abstract class Command<R> {
	private volatile static StorageFactory factory;

	private static final String BASE_NAME = "minipie";
	private static final String STORAGE_FACTORY_CLASS_KEY = "StorageFactoryClass";
	private static final String STORAGE_FACTORY_CLASS_NAME = ResourceBundle
			.getBundle(BASE_NAME).getString(STORAGE_FACTORY_CLASS_KEY);

	/**
	 * get current storage factory specified by the configuration file
	 * 
	 * @return storage factory
	 */
	protected StorageFactory getStorageFactory() {
		if (factory == null) {
			initializeStorageFactory();
		}
		return factory;
	}

	private static synchronized void initializeStorageFactory() {
		if (factory == null) {
			try {
				factory = (StorageFactory) Class.forName(
						STORAGE_FACTORY_CLASS_NAME).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new ServerErrorException(
						"FATAL ERROR: Storage Factory instantiation failed.");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new ServerErrorException(
						"FATAL ERROR: Storage Factory access denied.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new ServerErrorException(
						"FATAL ERROR: Storage Factory class not found.");
			} catch (ClassCastException e){
				e.printStackTrace();
				throw new ServerErrorException(
						"FATAL ERROR: Storage Factory class incompatible.");
			}
		}
	}

	public abstract R execute();
}
