package com.uwi.config;

import com.uwi.enums.ResultType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <code>
 * The **Configuration** class manages global configurations for
 * *jsqltoxml*. By default the *conf.properties* file is loaded; i.e
 * This file should only contain global configuration such as language, output
 * or engine settings. The default language is *'en'* & the default Engine
 * output format is XML. The language specific file is loaded using the prefix
 * specified in the *conf.properties* file. e.g. [en].properties, where
 * *en* is the prefix.
 * </code>
 */
public class Configuration {

    /**
     * The default language.
     */
    public static String DEFAULT_LANG = "EN";
    /**
     * The default log enabled value.
     */
    public static boolean LOG_ENABLED = true;
    /**
     * The default type.
     */
    public static ResultType DEFAULT_TYPE = ResultType.XML;
    /**
     * should not be changed, but if it should remember to update
     * *conf.properties* prefixes
     */
    public final String DEF_CONST = "c_";
    /**
     * The properties.
     */
    private Properties langProperties;
    /**
     * default configuration properties
     */
    private Properties confProperties;

    /**
     * <code>
     * Instantiates a new configuration.
     * At creation time the `core.properties` & `en.properties` (language specific property) file
     * is loaded
     * </code>
     */
    public Configuration() {
        loadConfigurations();
    }

    /**
     * Load properties file within the *'confs'* folder.
     *
     * @param propertyFile
     *         the property file
     *
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

    /**
     * @internal
     * @param input - String input
     */
    public void log (String input) {
        if(LOG_ENABLED) {
            System.out.println(input);
        }
    }

    /**
     * @internal
     * @param input - String input
     */
    public void logError (String input) {
        if(LOG_ENABLED) {
            System.out.println(input);
        }
    }

    /**
     * <code>
     * Retrieves i18n value from a property file.
     * Property names prefixed with `c_` are core properties and will be search
     * for in the `core.properties`. if the property does not contain the prefix
     * the `en.properties` file is searched. The language prefix `en`, on
     * language specified in the configuration file.
     * Support can be added for any language.
     * </code>
     *
     * @param propertyName
     *         the property name
     *
     * @return property value
     */
    public String i18n(String propertyName) {
        if (propertyName.startsWith(DEF_CONST)) {
            return confProperties.getProperty(propertyName);
        } else {
            return langProperties.getProperty(propertyName);
        }
    }

    /**
     *
     * <code>
     *     Retrieves i18n value from a property file. If the returned property
     *     value supports formatting, then the second param will be used to
     *     return formatted string.
     * </code>
     *
     * @param propertyName the property name
     * @param args
     *         - string arguments list
     *
     * @return formatted i18n string
     */
    public String i18n(String propertyName, Object... args) {
        return String.format(i18n(propertyName), args);
    }

    /**
     * Load core and language configuration property files
     *
     * @return the configuration
     */
    private Configuration loadConfigurations() {
        loadCoreConfigurations();
        langProperties = loadProperties(
                String.format(
                        "%s.properties", DEFAULT_LANG));
        return this;
    }

    /**
     * Load language configuration property file
     * @return the configuration
     */
    private Configuration loadCoreConfigurations() {
        if (confProperties == null) {
            String CONF_FILE = "core.properties";
            confProperties = loadProperties(CONF_FILE);
        }

        DEFAULT_LANG = confProperties.getProperty("language", "en");

        LOG_ENABLED = Boolean.getBoolean(confProperties.getProperty("log_enabled", "false"));

        // loads the default language, if a language cannot be loaded
        // use the default ResultType.XML
        DEFAULT_TYPE = ResultType.parse(
                confProperties.getProperty("type", "xml"), ResultType.XML);
        return this;
    }
}
