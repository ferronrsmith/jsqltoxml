package com.uwi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.uwi.enums.ResultType;

/**
 * The <code>Configuration</code> class manages global configurations for
 * <b>jsqltoxml</b>. By default the <b>conf.properties</b> file is loaded; i.e
 * This file should only contain global configuration such as language, output
 * or engine settings. The default lang is <b>'en'</b> & the default Engine
 * output format is XML. The language specific file is loaded using the prefix
 * specified in the <b>conf.properties</b> file. e.g. [en].properties, where
 * <b>en</b> is the prefix.
 */
public class Configuration {

	/**
	 * Load properties file within the 'confs' folder.
	 *
	 * @param propertyFile
	 *            the property file
	 * @return the properties
	 * @internal
	 */
	private static Properties loadProperties(String propertyFile) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("confs/" + propertyFile);
			prop.load(input);
		} catch (IOException e) {
			System.err.println("Failed to load " + propertyFile);
			System.err.println("System cannot start with configs");
			System.exit(-1);
		}
		return prop;
	}

	/** The default lang. */
	public static String DEFAULT_LANG = "EN";

	/** The default type. */
	public static ResultType DEFAULT_TYPE = ResultType.XML;

	/** The properties. */
	private Properties langProperties;

	/** default configuration properties */
	private Properties confProperties;

	/**
	 * should not be changed, but if it should remember to update
	 * <b>conf.properties</b> prefixes
	 */
	public final String DEF_CONST = "c_";

	private final String CONF_FILE = "core.properties";

	/**
	 * Instantiates a new configuration.
	 */
	public Configuration() {
		loadConfigurations();
	}

	/**
	 * i18n <K,V> property retrieval
	 *
	 * @param propertyName
	 *            the property name
	 * @return the string
	 */
	public String i18n(String propertyName) {
		if (propertyName.startsWith(DEF_CONST)) {
			return confProperties.getProperty(propertyName);
		} else {
			return langProperties.getProperty(propertyName);
		}
	}

	/**
	 * i18n retreival with {@link String#format(String, Object...)} support
	 *
	 * @param propertyName
	 * @param args
	 *            - string arguments list
	 * @return
	 */
	public String i18n(String propertyName, Object... args) {
		return String.format(i18n(propertyName), args);
	}

	/**
	 * Load core and language configuration.
	 *
	 * @return the configuration
	 */
	public Configuration loadConfigurations() {
		loadCoreConfigurations();
		langProperties = loadProperties(String.format("%s.properties",
				DEFAULT_LANG));
		return this;
	}

	/**
	 * Load language configuration.
	 *
	 * @return the configuration
	 */
	public Configuration loadCoreConfigurations() {
		if (confProperties == null) {
			confProperties = loadProperties(CONF_FILE);
		}

		DEFAULT_LANG = confProperties.getProperty("language", "en");
		// loads the default language, if a language cannot be loaded
		// use the default ResultType.XML
		DEFAULT_TYPE = ResultType.parse(
				confProperties.getProperty("type", "xml"), ResultType.XML);
		return this;
	}
}
