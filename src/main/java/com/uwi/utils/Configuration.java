package com.uwi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.uwi.enums.ResultType;

// TODO: Auto-generated Javadoc
/**
 * The Class Configuration.
 */
public class Configuration {

	/**
	 * Load properties.
	 *
	 * @param propertyFile
	 *            the property file
	 * @return the properties
	 */
	private static Properties loadProperties(String propertyFile) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("confs/" + propertyFile);
			prop.load(input);
		} catch (IOException e) {
			System.err.println("Failed to load " + propertyFile);
		}
		return prop;
	}

	/** The default lang. */
	public static String DEFAULT_LANG = "EN";

	/** The default type. */
	public static ResultType DEFAULT_TYPE = ResultType.XML;

	/** The properties. */
	private Properties properties;

	/**
	 * Instantiates a new configuration.
	 */
	public Configuration() {
		loadConfiguration(loadProperties("conf.properties"));
	}

	/**
	 * I18n.
	 *
	 * @param propertyName
	 *            the property name
	 * @return the string
	 */
	public String i18n(String propertyName) {
		return properties.getProperty(propertyName);
	}

	public String i18n(String propertyName, Object... args) {
		return String.format(i18n(propertyName), args);
	}

	/**
	 * Load configuration.
	 *
	 * @return the configuration
	 */
	public Configuration loadConfiguration() {
		loadConfiguration(loadProperties("conf.properties"));
		return this;
	}

	/**
	 * Load configuration.
	 *
	 * @param conf
	 *            the conf
	 * @return the configuration
	 */
	public Configuration loadConfiguration(Properties conf) {
		if (conf != null) {
			DEFAULT_LANG = conf.getProperty("language");
			DEFAULT_TYPE = ResultType.parse(conf.getProperty("type"),
					ResultType.XML);
			properties = loadProperties(String.format("%s.properties",
					DEFAULT_LANG));
		}
		return this;
	}

}
